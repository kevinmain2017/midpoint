package fis.com.vn.Test;

import java.nio.charset.Charset;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Test2 {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic IGFkbWluaXN0cmF0b3I6NWVjcjN0");
		String json = "{ \"user\" : { \"oid\" : \"3ad70e14-c14b-4ff5-9a65-d9cc1466ee11\", \"name\" : \"administrator7\", \"assignment\" : { \"targetRef\" : { \"oid\" : \"00000000-0000-0000-0000-000000000004\" } }, \"activation\" : { \"administrativeStatus\" : \"enabled\" }, \"fullName\" : \"midPoint Administrator1\", \"givenName\" : \"midPoint1\", \"familyName\" : \"Administrato1r\", \"title\" : \"title\", \"credentials\" : { \"password\" : { \"value\" : { \"clearValue\" : \"5ecr3t\" } } } } }";
		
		String url = "http://localhost:8080/midpoint/ws/rest/users";
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println(response.getStatusCodeValue());
	}
}
