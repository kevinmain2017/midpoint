package test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

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

public class AnhChinhSua {
	static String url = "https://anonymousdetect.icomm.vn/anonymous/fakeimagedetect/";
	static String folderPath = "D:\\CHINH\\anh\\anhcmtreal";
	static String folderPathFake = "D:\\CHINH\\anh\\anhcmtfake";
	final static File folder = new File(folderPath);
	final static File folderFake = new File(folderPath);
	
	public static void main(String[] args) {
		ArrayList<String> listTrue = new ArrayList<String>();
		ArrayList<String> listFalse = new ArrayList<String>();
		
		for (final File fileEntry : folder.listFiles()) {
			ResponeAcs responeAcs = check(folderPath+"\\"+fileEntry.getName());
			if(responeAcs.getResult().getRs().equals("REAL")) {
				listTrue.add(folderPath+"\\"+fileEntry.getName());
			} else {
				listFalse.add(folderPath+"\\"+fileEntry.getName());
			}
		}
		
		System.out.println("Số ảnh detect đúng: "+listTrue.size());
		System.out.println("Số ảnh detect sai: "+listFalse.size());
		System.out.println("-----------------------------------------");
		for (final File fileEntry : folderFake.listFiles()) {
			ResponeAcs responeAcs = check(folderPathFake+"\\"+fileEntry.getName());
			if(responeAcs.getResult().getRs().equals("REAL")) {
				listFalse.add(folderPathFake+"\\"+fileEntry.getName());
			} else {
				listTrue.add(folderPathFake+"\\"+fileEntry.getName());
			}
		}
		
		System.out.println("Số ảnh detect đúng: "+listTrue.size());
		System.out.println("Số ảnh detect sai: "+listFalse.size());
	}
	public static ResponeAcs check(String file) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost(url);
			
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
}
