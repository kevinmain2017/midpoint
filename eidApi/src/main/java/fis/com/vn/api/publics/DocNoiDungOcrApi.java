package fis.com.vn.api.publics;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.entities.OCRField;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.request.Params;
import fis.com.vn.resp.RespApi;
import fis.com.vn.response.NoiDungOCR;
import fis.com.vn.table.MUser;
import fis.com.vn.vision.ContainsVision;
import fis.com.vn.vision.OCRParser;
import fis.com.vn.vision.entities.Ocr;

@RestController
public class DocNoiDungOcrApi extends BaseApi{
	@Autowired MUserRepository mUserRepository;
	
	@PostMapping(value = {"/public/doc-noi-dung-ocr", "/private/doc-noi-dung-ocr"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String docNoiDungOCr(HttpServletRequest req) {
		RespApi respApi = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);

			
			OCRParser parser = new OCRParser();
	        Ocr ocr = parser.parsing(params.getAnhMatTruoc(), params.getAnhMatSau(), params.getMaGiayTo());
	        
	        if(req.getRequestURI().equals("/public/doc-noi-dung-ocr")) {
	        	MUser mUser = null;
	        	if(params.getMaGiayTo().equals(ContainsVision.CHUNG_MINH_THU) || params.getMaGiayTo().equals(ContainsVision.CAN_CUOC_CONG_DAN)) {
	        		mUser = mUserRepository.findByTenDangNhap(ocr.getSoCmt());
	        	} else if (params.getMaGiayTo().equals(ContainsVision.GIAY_PHEP_LAI_XE)) {
	        		mUser = mUserRepository.findByTenDangNhap(ocr.getSoGiayPhepLaiXe());
				} else if (params.getMaGiayTo().equals(ContainsVision.HO_CHIEU)) {
					mUser = mUserRepository.findByTenDangNhap(ocr.getSoHoChieu());
				}
	        	if(mUser != null) {
	        		respApi.setStatus(HttpStatusApi.NGHI_VAN_XAC_THUC);
	    			respApi.setMessage("Số giấy tờ đã tồn tại");
	    			return new Gson().toJson(respApi);
	        	}
	        	
	        }
	        		
	        respApi.setStatus(HttpStatusApi.THANH_CONG);
	        respApi.setData(ocr);
	        
		} catch (Exception e) {
			e.printStackTrace();
			respApi.setStatus(HttpStatusApi.THAT_BAI);
			respApi.setMessage("Đọc thông tin thất bại");
		}
		return new Gson().toJson(respApi);
	}
}
