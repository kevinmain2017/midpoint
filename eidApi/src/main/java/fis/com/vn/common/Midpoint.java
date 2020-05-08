package fis.com.vn.common;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import fis.com.vn.midpoint.Activation;
import fis.com.vn.midpoint.Assignment;
import fis.com.vn.midpoint.Credentials;
import fis.com.vn.midpoint.ItemDelta;
import fis.com.vn.midpoint.JsonModify;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.JsonUserType;
import fis.com.vn.midpoint.Modify;
import fis.com.vn.midpoint.ParamsUser;
import fis.com.vn.midpoint.Password;
import fis.com.vn.midpoint.TargetRef;
import fis.com.vn.midpoint.User;
import fis.com.vn.midpoint.UserType;

@Component
public class Midpoint {
	@Value("${spring.api.midpoint}")
	public String apiMidpoint;
	
	@Value("${spring.api.midpoint.token}")
	public String tokenConnectMidpoint;
	
	public JsonModify createModify(Map<String, String> allParams) {
		JsonModify jsonModify = new JsonModify();
		ItemDelta itemDelta = new ItemDelta();
		ArrayList<Modify>  modifies = new ArrayList<Modify>();
		for (Entry<String, String> entry : allParams.entrySet()) {
			if(!StringUtils.isEmpty(entry.getValue())) {
				Modify modify = new Modify();
				modify.setPath(entry.getKey());
				modify.setValue(entry.getValue());
				modifies.add(modify);
			}
		}
		itemDelta.setItemDelta(modifies);
		jsonModify.setObjectModification(itemDelta);
		
		return jsonModify;
	}
	
	public JsonUserType createUserTypeInsert(String userOid, String info, String typeCode) {
		JsonUserType jsonUserType = new JsonUserType();
		UserType userType = new UserType();
		userType.setInfo(info);
		userType.setUserOid(userOid);
		userType.setTypeCode(typeCode);
		
		jsonUserType.setUserTypeType(userType);
		
		return jsonUserType;
	}
	
	public JsonUser createUserInsert(ParamsUser paramsUser) {
		JsonUser jsonUser = new JsonUser();
		User user = new User();
		fis.com.vn.midpoint.Value value = new fis.com.vn.midpoint.Value();
		value.setClearValue("12345");
		Password password = new Password();
		password.setValue(value);
		Credentials credentials = new Credentials();
		credentials.setPassword(password);
		user.setCredentials(credentials);
		Activation activation = new Activation();
		activation.setAdministrativeStatus("enabled");
		user.setActivation(activation);
		TargetRef targetRef = new TargetRef();
		targetRef.setOid("00000000-0000-0000-0000-000000000008");
		Assignment  assignment = new Assignment();
		assignment.setTargetRef(targetRef);
		user.setAssignment(assignment);
		
		user.setOid(UUID.randomUUID().toString());
		user.setName(paramsUser.getSoCmt());
		user.setFullName(paramsUser.getHoVaTen());
		user.setNoiTru(paramsUser.getNoiTru());
		user.setDienThoai(paramsUser.getDienThoai());
		user.setSoCmt(paramsUser.getSoCmt());
		user.setNamSinh(paramsUser.getNamSinh());
		user.setGioiTinh(paramsUser.getGioiTinh());
		user.setDanToc(paramsUser.getDanToc());
		user.setQueQuan(paramsUser.getQueQuan());
		user.setTonGiao(paramsUser.getTonGiao());
		user.setNgayCap(paramsUser.getNgayCap());
		user.setNoiCap(paramsUser.getNoiCap());
		user.setEmail(paramsUser.getEmail());
		user.setDiaChi(paramsUser.getDiaChi());
		user.setSoHoChieu(paramsUser.getSoHoChieu());
		user.setSoGiayPhepLaiXe(paramsUser.getSoGiayPhepLaiXe());
		user.setMstCaNhan(paramsUser.getMstCaNhan());
		user.setMaSoBHXH(paramsUser.getMaSoBHXH());
		
		jsonUser.setUser(user);
		return jsonUser;
	}
	
	public int insertToApiMidPoint(String json, String typeInsert ) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic "+tokenConnectMidpoint);
		
		String url = apiMidpoint+ typeInsert;
//		System.out.println(json);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println(response.getStatusCodeValue());
		
		return response.getStatusCodeValue();
	}
	
	public int modifyToApiMidPoint(String json, String typeInsert, String oid ) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic "+tokenConnectMidpoint);
		
		String url = apiMidpoint+ typeInsert+"/"+oid;
//		System.out.println(json);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		System.out.println(response.getStatusCodeValue());
		
		return response.getStatusCodeValue();
	}
}
