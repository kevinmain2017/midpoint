package fis.com.vn.common;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

public class Request {
	public static String get(UriComponentsBuilder builder, String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> response;

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

		return response.getBody();
	}

	public static String postFile(String json, String authorization, String url, MultipartFile file) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", new ByteArrayResource(file.getBytes()));
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static String postFileEncode(String json, String authorization, String url, MultipartFile file) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", Base64.getEncoder().encode(file.getBytes()));
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static String postFileEncode(String json, String authorization, String url, String file) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file", file.getBytes());
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static String postFile(String json, String authorization, String url, MultipartFile fileMattruoc, MultipartFile fileMatSau) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("fileMattruoc", Base64.getEncoder().encodeToString(fileMattruoc.getBytes()));
			body.add("fileMatSau", Base64.getEncoder().encodeToString(fileMatSau.getBytes()));
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static String postFile(String json, String authorization, String url, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("file1", Base64.getEncoder().encode(file1.getBytes()));
			body.add("file2", Base64.getEncoder().encode(file2.getBytes()));
			body.add("file3", Base64.getEncoder().encode(file3.getBytes()));
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static String postFileMultiple(String json, String authorization, String url, MultipartFile... files) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

			for (int i = 0; i < files.length; i++) {
				body.add("file"+(i=1), Base64.getEncoder().encode(files[i].getBytes()));
			}
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static String postFile(String json, String authorization, String url, MultipartFile[] file) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.add("Authorization", authorization);

			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			for (int i = 0; i < file.length; i++) {
				body.add("file"+(i+1), Base64.getEncoder().encode(file[i].getBytes()));
			}
			
			body.add("json", json);

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static String post(String json, String authorization, String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.add("Authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return response.getBody();
	}
	
	public static String postMultipartFile(String json, String authorization, String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("Authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return response.getBody();
	}

	public static String get(UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> response;

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

		return response.getBody();
	}

	public static String post(Map<String, Object> map, String authorization, String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<Object> entity = new HttpEntity<Object>(map, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return response.getBody();
	}

	public static String put(Map<String, Object> map, String authorization, String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<Object> entity = new HttpEntity<Object>(map, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		return response.getBody();
	}

	public static String put(String json, String authorization, String url) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", authorization);

		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

		return response.getBody();
	}

	public static String delete(UriComponentsBuilder builder, String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> response;

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);

		return response.getBody();
	}

	public static String put(UriComponentsBuilder builder, String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> response;

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class);

		return response.getBody();
	}

	public static <T> T getList(String json, Type type, String... attr) {
		String str = "";
		try {
			for (int i = 0; i < attr.length; i++) {
				JSONObject jsonObject = new JSONObject(json);
				json = jsonObject.get(attr[i]).toString();

				if (i == (attr.length - 1)) {
					str = jsonObject.get(attr[i]).toString();
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new Gson().fromJson(str, type);
	}

	public static String getAttrFromJson(String json, String... attr) {
		try {
			for (int i = 0; i < attr.length; i++) {
				JSONObject jsonObject = new JSONObject(json);
				json = jsonObject.get(attr[i]).toString();

				if (i == (attr.length - 1)) {
					return jsonObject.get(attr[i]).toString();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return json;
	}

	public static int getIntFromJson(String json, String... attr) {
		return Integer.valueOf(getAttrFromJson(json, attr));
	}

	public static long getLongFromJson(String json, String... attr) {
		return Long.valueOf(getAttrFromJson(json, attr));
	}

	public static String getContent(String json) {
		return getAttrFromJson(json, "data", "content");
	}

	public static int getTotal(String json) {
		return getIntFromJson(json, "data", "total");
	}

	public static int getStatus(String json) {
		return getIntFromJson(json, "statusCode");
	}
	
	public static String getMessage(String json) {
		return getAttrFromJson(json, "msg");
	}

	public static String post(UriComponentsBuilder builder, String authorization) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

		ResponseEntity<String> response;

		response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);

		return response.getBody();
	}
}
