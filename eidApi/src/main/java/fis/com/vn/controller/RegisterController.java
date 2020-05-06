//package fis.com.vn.controller;
//
//import java.nio.charset.Charset;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import com.fis.faceid.FaceID;
//import com.google.gson.Gson;
//
//import fis.com.vn.common.Common;
//import fis.com.vn.entities.FaceId;
//import fis.com.vn.midpoint.Activation;
//import fis.com.vn.midpoint.Assignment;
//import fis.com.vn.midpoint.Credentials;
//import fis.com.vn.midpoint.JsonUser;
//import fis.com.vn.midpoint.ParamsUser;
//import fis.com.vn.midpoint.Password;
//import fis.com.vn.midpoint.TargetRef;
//import fis.com.vn.midpoint.User;
//import fis.com.vn.repository.MUserImageRepository;
//import fis.com.vn.repository.MUserRepository;
//import fis.com.vn.resp.Resp;
//import fis.com.vn.table.MUserImage;
//
//@Controller
//public class RegisterController extends BaseController{
//	@Value("${spring.api.midpoint}")
//	public String apiMidpoint;
//	
//	@Autowired
//	MUserRepository mUserRepository;
//	@Autowired
//	MUserImageRepository mUserImageRepository;
//	
//	@PostMapping(value = "/api/register")
//	@ResponseBody
//	public String register(HttpServletRequest req, @RequestBody ParamsUser paramsUser) {
//		Resp resp = new Resp();
//		
//		JsonUser user = createUserInsert(paramsUser);
//		
//		int check = insertToApiMidPoint(new Gson().toJson(user));
//		if(check == 201) {
////			saveImage(allParams, user.getUser().getOid());
//			
//			resp.setStatusCode(HttpStatus.OK.value());
//			resp.setData(new Gson().toJson(user));
//		} else {
//			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			resp.setData("false");
//		}
//		
//		return new Gson().toJson(resp);
//	}
//	
//	private void saveImage(Map<String, String> allParams, String userOid) {
//		MUserImage mUserImage = new MUserImage();
//		mUserImage.setUserOid(userOid);
//		mUserImage.setOid(UUID.randomUUID().toString());
//		mUserImage.setImage(allParams.get("file1").getBytes());
//		
//		mUserImageRepository.save(mUserImage);
//		
//		mUserImage = new MUserImage();
//		mUserImage.setUserOid(userOid);
//		mUserImage.setOid(UUID.randomUUID().toString());
//		mUserImage.setImage(allParams.get("file2").getBytes());
//		
//		mUserImageRepository.save(mUserImage);
//		
//		mUserImage = new MUserImage();
//		mUserImage.setUserOid(userOid);
//		mUserImage.setOid(UUID.randomUUID().toString());
//		mUserImage.setImage(allParams.get("file3").getBytes());
//		
//		mUserImageRepository.save(mUserImage);
//		
//	}
//
//	public JsonUser createUserInsert(ParamsUser paramsUser) {
//		JsonUser jsonUser = new JsonUser();
////		User user = new User();
////		fis.com.vn.midpoint.Value value = new fis.com.vn.midpoint.Value();
////		value.setClearValue(paramsUser.getPassword());
////		Password password = new Password();
////		password.setValue(value);
////		Credentials credentials = new Credentials();
////		credentials.setPassword(password);
////		user.setCredentials(credentials);
////		Activation activation = new Activation();
////		activation.setAdministrativeStatus("enabled");
////		user.setActivation(activation);
////		TargetRef targetRef = new TargetRef();
////		targetRef.setOid("00000000-0000-0000-0000-000000000004");
////		Assignment  assignment = new Assignment();
////		assignment.setTargetRef(targetRef);
////		user.setAssignment(assignment);
////		
////		user.setOid(UUID.randomUUID().toString());
////		user.setName(paramsUser.getName());
////		user.setFullName(paramsUser.getFullName());
////		user.setGivenName("");
////		user.setFamilyName("");
////		user.setTitle("");
//////		user.setAddress(paramsUser.getAddress());
//////		user.setPhone(paramsUser.getPhone());
////		user.setEmployeeNumber(paramsUser.getId());
////		jsonUser.setUser(user);
//		return jsonUser;
//	}
//	
//	public int insertToApiMidPoint(String json ) {
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.add("Authorization", "Basic IGFkbWluaXN0cmF0b3I6NWVjcjN0");
//		
//		String url = apiMidpoint+ "users";
//		System.out.println(json);
//		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//		System.out.println(response.getStatusCodeValue());
//		
//		return response.getStatusCodeValue();
//	}
//}
