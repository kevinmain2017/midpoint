package fis.com.vn.vision;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fis.faceid.FaceID;
import com.google.gson.Gson;

import fis.com.vn.common.Common;
import fis.com.vn.vision.entities.Ocr;
import fis.com.vn.vision.entities.OcrBlx;
import fis.com.vn.vision.entities.OcrCmtCccd;
import fis.com.vn.vision.entities.OcrHoChieu;
import fis.com.vn.vision.entities.ResponseOcrBlx;
import fis.com.vn.vision.entities.ResponseOcrCmtCccd;
import fis.com.vn.vision.entities.ResponseOcrHoChieu;

public class OCRParser {
	public Ocr parsing (String base64AnhMatTruoc, String base64AnhMatSau, String loaiGiayTo) {
		Ocr ocr = null;
		
		try {
			if(loaiGiayTo.equals(ContainsVision.CHUNG_MINH_THU) || loaiGiayTo.equals(ContainsVision.CAN_CUOC_CONG_DAN)) {
				ocr = callCmtCccd(base64AnhMatTruoc, base64AnhMatSau);
			} else if (loaiGiayTo.equals(ContainsVision.GIAY_PHEP_LAI_XE)) {
				ocr = callGiayPhepLaiXe(base64AnhMatTruoc, base64AnhMatSau);
			} else if (loaiGiayTo.equals(ContainsVision.HO_CHIEU)) {
				ocr = callHoChieu(base64AnhMatTruoc);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return handlingOcr(ocr, base64AnhMatTruoc);
	}
	
	public Ocr handlingOcr(Ocr ocr, String base64AnhMatTruoc) {
		if(ocr != null) {
			ocr.setNamSinh(formatStringDate(ocr.getNamSinh()));
			ocr.setNgayCap(formatStringDate(ocr.getNgayCap()));
			ocr.setNgayHetHan(formatStringDate(ocr.getNgayHetHan()));
			
			try {
				Resource resource = new ClassPathResource("lbpcascade_frontalface_improved.xml");
				FaceID.init(resource.getFile().getAbsolutePath());
				ocr.setAnhChanDung(FaceID.faceCrop(base64AnhMatTruoc));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return ocr;
	}
//	public String getBase64EncodedImage(String imageURL) {
//		System.out.println(imageURL);
//	    try {
//	    	Thread.sleep(1000);
//	    	java.net.URL url = new java.net.URL(imageURL); 
//		    InputStream is = url.openStream();  
//		    byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is); 
//		    return Base64.getEncoder().encodeToString(bytes);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	    return "";
//	}
	private Ocr callHoChieu(String base64AnhMatTruoc) throws JsonMappingException {
		Ocr ocr = new Ocr();
		if(!StringUtils.isEmpty(base64AnhMatTruoc)) {
			String jsonMatTruoc = sendRequest(base64AnhMatTruoc, ContainsVision.VISION_HO_CHIEU, ContainsVision.LAY_KHUON_MAT);
			
			ResponseOcrHoChieu responseOcrHoChieu = new Gson().fromJson(jsonMatTruoc, ResponseOcrHoChieu.class);
			
			OcrHoChieu ocrHoChieu = responseOcrHoChieu.getData().get(0);
			Common.updateObjectToObject(ocr, ocrHoChieu);
		}
		
		return ocr;
	}
	
	private Ocr callGiayPhepLaiXe(String base64AnhMatTruoc, String base64AnhMatSau) throws JsonMappingException {
		Ocr ocr = new Ocr();
		if(!StringUtils.isEmpty(base64AnhMatTruoc)) {
			String jsonMatTruoc = sendRequest(base64AnhMatTruoc, ContainsVision.VISION_BLX, ContainsVision.LAY_KHUON_MAT);
			
			ResponseOcrBlx responseOcrBlx = new Gson().fromJson(jsonMatTruoc, ResponseOcrBlx.class);
			OcrBlx ocrBlx =  responseOcrBlx.getData().get(0);
			Common.updateObjectToObject(ocr, ocrBlx );
		}
		if(!StringUtils.isEmpty(base64AnhMatSau)) {
			String jsonMatSau = sendRequest(base64AnhMatSau, ContainsVision.VISION_CMT_CCCD, ContainsVision.LAY_KHUON_MAT);
			
			ResponseOcrBlx responseOcrBlx = new Gson().fromJson(jsonMatSau, ResponseOcrBlx.class);
			OcrBlx ocrBlx =  responseOcrBlx.getData().get(0);
			Common.updateObjectToObject(ocr, ocrBlx );
		}
		
		return ocr;
	}
	
	private Ocr callCmtCccd(String base64AnhMatTruoc, String base64AnhMatSau) throws JsonMappingException {
		Ocr ocr = new Ocr();
		if(!StringUtils.isEmpty(base64AnhMatTruoc)) {
			String jsonMatTruoc = sendRequest(base64AnhMatTruoc, ContainsVision.VISION_CMT_CCCD, ContainsVision.LAY_KHUON_MAT);
			
			ResponseOcrCmtCccd responseOcrCmtCccd = new Gson().fromJson(jsonMatTruoc, ResponseOcrCmtCccd.class);
			
			OcrCmtCccd ocrCmtCccd = responseOcrCmtCccd.getData().get(0);
			Common.updateObjectToObject(ocr, ocrCmtCccd);
		}
		if(!StringUtils.isEmpty(base64AnhMatSau)) {
			String jsonMatSau = sendRequest(base64AnhMatSau, ContainsVision.VISION_CMT_CCCD, ContainsVision.LAY_KHUON_MAT);
			ResponseOcrCmtCccd responseOcrCmtCccd = new Gson().fromJson(jsonMatSau, ResponseOcrCmtCccd.class);
			
			OcrCmtCccd ocrCmtCccd = responseOcrCmtCccd.getData().get(0);
			Common.updateObjectToObject(ocr, ocrCmtCccd);
		}
		
		return ocr;
	}
	
	private String sendRequest(String base64Image, String url, String face) {
		try {
			byte[] byteImage = Base64.getDecoder().decode(base64Image);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			HttpPost uploadFile = new HttpPost(url);
			uploadFile.addHeader("api_key", ContainsVision.API_KEY);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("face", face, ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			
			// This attaches the file to the POST:
			builder.addBinaryBody("image", byteImage, ContentType.MULTIPART_FORM_DATA, "abc.jpg" );
			
			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);
			HttpEntity responseEntity = response.getEntity();
			String text = IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8.name());
			
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String formatStringDate(String strDate) {
		try {
			if(strDate != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				return dateFormat.format(dateFormat.parse(strDate));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return strDate;
	}
}
