package fis.com.vn.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Test {
	public static void main(String[] args) {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost uploadFile = new HttpPost("https://anonymousdetect.icomm.vn/anonymous/anonymousdetect");
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addTextBody("type_card", "CCCD", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("card_number", "031075002909", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("display_name", "Phan Thanh Toàn", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("date_of_birth", "15/11/1975", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("gender", "Nam", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("hometown", "Đức thọ, hà tĩnh", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("location", "SN 29 lô n07a, KĐTMDV dịch vọng, cầu giấy, hà nội", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("expire_date", "15/11/2035", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			builder.addTextBody("register_date", "19/07/2016", ContentType.TEXT_PLAIN.withCharset(Charset.forName("utf-8")));
			
			String imfBase64Encode = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAxADEDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwCg52javFUpmvUBZJRgdqn1CGcp+5YLnvXPPBqn2jHnnZ7158Vc63obdtqc7MFdfxq5cXjxx7guapWFs5VfOHJ64q5fDZbbUGcUrlooDUL4t8iDFa1jcSyL+9GD6VyUuq3VpKNsBYE4roNOvZLoANA0b9TnoarVInrY2d9FR4PrRU3GR5Ei4Iqs8CKdxpRKFpkgaRSwPSgdiePntxSuocYboawnutQRyWhwgOAV61PFqF0ZBFJAdhH36Y7FlrEF8gZFX4IggAA6VBDIcYarKvQKxLk+tFRbjRQFjMY4zUDanFCMSMBin3DGNytUpYkI3GMMfpUrfUa1RYGuWp+Xn61Mmo20pADAH0NYclzGjbTbD/vmrUMMFwvzR7fpwat2BG0s8b/dPIqxG+4dayYI1twQpJHua0IG4qdweha/GineW3pRV8jI50ZF9/r2+tQr900UVJUSpJ9/8asw0UUPYolq3bffWiiiO5M9jbooorc5T//Z";
			
			
			// This attaches the file to the POST:
			builder.addBinaryBody(
			    "file",
			    Base64.getDecoder().decode(imfBase64Encode),
			    ContentType.MULTIPART_FORM_DATA,
			    "abc"
			);
			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);
			HttpEntity responseEntity = response.getEntity();
			String text = IOUtils.toString(responseEntity.getContent(), StandardCharsets.UTF_8.name());
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
