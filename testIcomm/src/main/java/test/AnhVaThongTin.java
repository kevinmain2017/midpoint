package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

import entities.ResponeAcs;

public class AnhVaThongTin {
	static String url = "https://anonymousdetect.icomm.vn/anonymous/anonymousdetect/";
	static String urlFake = "https://anonymousdetect.icomm.vn/anonymous/fakeimagedetect/";
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
		
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "Tên file", "username", "email", "số đt", "Dân tộc", "Giới tính", "họ và tên",
				"Năm sinh", "Ngày cấp", "Nơi cấp", "Nơi trú", "Quê quán", "Số giấy tờ", "Tôn giáo", "Api 1", "Api 2", "Kết quả", "Response api1", "Response api2"});
		
		for (NoiDungOCR noiDungOCR : noiDungOCRs) {
			if(noiDungOCR.getPhone() != null && !noiDungOCR.getPhone().equals("") && !noiDungOCR.getPhone().equals("null")) {
				String checkApi1 = "false";
				String checkApi2 = "false";
				String checkApi = "false";
				
				ResponeAcs responeAcFake = checkFake(folderPath+"\\"+noiDungOCR.getTenFile());
				if(responeAcFake.getResult().getRs().equals("REAL")) {
					checkApi1 = "true";
				} else {
					checkApi1 = "false";
				}
				
				System.out.println(new Gson().toJson(noiDungOCR));
				ResponeAcs responeAcs = check(noiDungOCR, folderPath, i);
				
				if(responeAcs.getResult().getAnonymous() == 0) {
					checkApi2 = "true";
					countTrue ++;
				} else {
					checkApi2 = "false";
					countFalse ++;
				}
				i++;
				count ++;
				
				if(checkApi1.equals("true") && checkApi2.equals("true")) checkApi = "true";
				
				String desFake = responeAcFake.getDescription();
				for (String str : responeAcFake.getResult().getDetail().getDescriptions()) {
					desFake += ", "+str;
				}
				
				data.put(i + "", new Object[] { noiDungOCR.getTenFile(), "", noiDungOCR.getEmail(), noiDungOCR.getPhone().replaceAll("[^0-9]+", ""), noiDungOCR.getDanToc(), noiDungOCR.getGioiTinh()
						, noiDungOCR.getHoVaTen(), noiDungOCR.getNamSinh(),
						noiDungOCR.getNgayCap(), noiDungOCR.getNoiCap(), noiDungOCR.getNoiTru(), noiDungOCR.getQueQuan(), noiDungOCR.getSoCmt(), noiDungOCR.getTonGiao(), 
						checkApi1,checkApi2, checkApi, desFake, responeAcs.getDescription_anonymous()  });
			}
		}
		
		exportXslt(data);
		System.out.println("-----------------------------------------");
		System.out.println("Tổng số ảnh detect: "+count);
		System.out.println("Số ảnh detect đúng: "+countTrue);
		System.out.println("Số ảnh detect sai: "+countFalse);
		System.out.println("-----------------------------------------");
	}
	
	public static ResponeAcs checkFake(String file) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(urlFake);
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			
			// This attaches the file to the POST:
			builder.addBinaryBody(
			    "file",
			    new File(file),
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
	
	public static ResponeAcs check(NoiDungOCR noiDungOCR, String path, int i) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(url);
			
			String[] arr = noiDungOCR.getTenFile().split("_");
			String type_card = arr[1].equals("cmt")?"CMTND":"CCCD";
//			System.out.println(noiDungOCR.getPhone().replaceAll("[^0-9]+", ""));
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("type_card", type_card, ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("card_number", noiDungOCR.getSoCmt(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("display_name", noiDungOCR.getHoVaTen(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("other_name", noiDungOCR.getHoVaTen(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("date_of_birth", noiDungOCR.getNamSinh(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("gender", noiDungOCR.getGioiTinh(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("hometown", noiDungOCR.getQueQuan(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("location", noiDungOCR.getNoiTru(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("expire_date", "", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("register_date", noiDungOCR.getNgayCap(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("phone", noiDungOCR.getPhone().replaceAll("[^0-9]+", ""), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
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
			builder.addTextBody("expire_date", "", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("register_date", noiDungOCR.getNgayCap(), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("phone", noiDungOCR.getPhone().replaceAll("[^0-9]+", ""), ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
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
	
	public static void exportXslt(Map<String, Object[]> data) {
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("bao_cao.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
