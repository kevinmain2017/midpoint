package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

import entities.ResponeAcs;

public class AnhVaThongTin {
	static String url = "https://anonymousdetect.icomm.vn/anonymous/anonymousdetect/";
	static String folderPath = "D:\\CHINH\\anh\\anhcmtreal";
	static String folderPathFake = "D:\\CHINH\\anh\\anhcmtfake";
	final static File folder = new File(folderPath);
	final static File folderFake = new File(folderPath);
	
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> listTrue = new ArrayList<String>();
		ArrayList<String> listFalse = new ArrayList<String>();
		
		ArrayList<NoiDungOCR> noiDungOCRs = getListInfo();
		int i=1;
		int count = 0;
		int countTrue = 0;
		int countFalse = 0;
		for (NoiDungOCR noiDungOCR : noiDungOCRs) {
			if(noiDungOCR.getPhone() != null && !noiDungOCR.getPhone().equals("") && !noiDungOCR.getPhone().equals("null")) {
				System.out.println(new Gson().toJson(noiDungOCR));
				ResponeAcs responeAcs = checkFixed(noiDungOCRs.get(0), folderPath, i, noiDungOCR);
				if(responeAcs.getResult().getAnonymous() == 0) {
					countTrue ++;
				} else {
					countFalse ++;
				}
				i++;
				count ++;
			}
		}
		System.out.println("-----------------------------------------");
		System.out.println("Tổng số ảnh detect: "+count);
		System.out.println("Số ảnh detect đúng: "+countTrue);
		System.out.println("Số ảnh detect sai: "+countFalse);
		System.out.println("-----------------------------------------");
	}
	
	
	
	public static ResponeAcs check(NoiDungOCR noiDungOCR, String path, int i) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(url);
			
			String[] arr = noiDungOCR.getTenFile().split("_");
			String type_card = arr[1].equals("cmt")?"CMTND":"CCCD";
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("type_card", type_card, ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("card_number", noiDungOCR.getSoCmt(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("display_name", noiDungOCR.getHoVaTen(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("other_name", noiDungOCR.getHoVaTen(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("date_of_birth", noiDungOCR.getNamSinh(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("gender", noiDungOCR.getGioiTinh(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("hometown", noiDungOCR.getQueQuan(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("location", noiDungOCR.getNoiTru(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
//			builder.addTextBody("expire_date", "15/11/2035", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
//			builder.addTextBody("register_date", "19/07/2016", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("phone", noiDungOCR.getPhone(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("model_device", "SAMSUNG-"+i, ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("email", noiDungOCR.getEmail(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			
			// This attaches the file to the POST:
			builder.addBinaryBody(
			    "file",
			    new File(path+"\\"+noiDungOCR.getTenFile()),
			    ContentType.MULTIPART_FORM_DATA,
			    "abc.jpg"
			);
			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);
			HttpEntity responseEntity = response.getEntity();
			String text = IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8.name());
			
			ResponeAcs responeAcs = new Gson().fromJson(text, ResponeAcs.class);
			return responeAcs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResponeAcs checkFixed(NoiDungOCR noiDungOCR, String path, int i, NoiDungOCR noiDungOCRChange) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(url);
			
			String[] arr = noiDungOCR.getTenFile().split("_");
			String type_card = arr[1].equals("cmt")?"CMTND":"CCCD";
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("type_card", type_card, ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("card_number", noiDungOCR.getSoCmt(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("display_name", noiDungOCR.getHoVaTen(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("other_name", noiDungOCR.getHoVaTen(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("date_of_birth", noiDungOCR.getNamSinh(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("gender", noiDungOCR.getGioiTinh(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("hometown", noiDungOCR.getQueQuan(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("location", noiDungOCR.getNoiTru(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
//			builder.addTextBody("expire_date", "15/11/2035", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
//			builder.addTextBody("register_date", "19/07/2016", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("phone", noiDungOCR.getPhone(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("model_device", "SAMSUNG-"+i, ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("email", noiDungOCR.getEmail(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			
			// This attaches the file to the POST:
			builder.addBinaryBody(
			    "file",
			    new File(path+"\\"+noiDungOCRChange.getTenFile()),
			    ContentType.MULTIPART_FORM_DATA,
			    "abc.jpg"
			);
			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);
			HttpEntity responseEntity = response.getEntity();
			String text = IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8.name());
			
			ResponeAcs responeAcs = new Gson().fromJson(text, ResponeAcs.class);
			return responeAcs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<NoiDungOCR> getListInfo() throws FileNotFoundException {
		ArrayList<String> arr = new ArrayList<String>();

		File myObj = new File("info.txt");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			arr.add(data);
		}
		ArrayList<NoiDungOCR> noiDungOCRArr = new ArrayList<NoiDungOCR>();
		for (String str : arr) {
			String[] arr1 = str.split("\\|");
			NoiDungOCR noiDungOCR = new NoiDungOCR();
			noiDungOCR.setDanToc(arr1[5]);
			noiDungOCR.setGioiTinh(arr1[6].equals("NAM")?"1":"0");
			noiDungOCR.setHoVaTen(arr1[7]);
			noiDungOCR.setNamSinh(arr1[8]);
			noiDungOCR.setNgayCap(arr1[9]);
			noiDungOCR.setNoiCap(arr1[10]);
			noiDungOCR.setNoiTru(arr1[11]);
			noiDungOCR.setQueQuan(arr1[12]);
			noiDungOCR.setSoCmt(arr1[13]);
			noiDungOCR.setPhone(arr1[3]);
			noiDungOCR.setEmail(arr1[2]);
			noiDungOCR.setTenFile(arr1[0]);
			
			noiDungOCRArr.add(noiDungOCR);
		}
		
		return noiDungOCRArr;
	}
}
