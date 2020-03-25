package fis.com.vn.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import fis.com.vn.entities.OCRField;
import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUserType;

@Controller
public class RegisterTypeController {
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	
	@GetMapping(value = "/api/active")
	@ResponseBody
	public String active(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		try {
			
			MUserType mUserType = mUserTypeRepository.findByUserOidAndTypeCode(allParams.get("user_oid"), 
					allParams.get("type_code"));
			if(mUserType == null) {
				mUserType = new MUserType();
				mUserType.setOid(UUID.randomUUID().toString());
			} else {
				
			}
			mUserType.setUserOid(allParams.get("user_oid"));
			mUserType.setTypeCode(allParams.get("type_code"));
			mUserType.setInfo(allParams.get("info"));
			
			mUserTypeRepository.save(mUserType);
			
			resp.setData("true");
			resp.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			resp.setData(new OCRField());
			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new Gson().toJson(resp);
	}
	
	@GetMapping(value = "/api/reactive")
	@ResponseBody
	public String reactive(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		try {
			
			mUserTypeRepository.deleteByTypeCodeAndUserOid(allParams.get("type_code"), 
					allParams.get("user_oid"));
			
			resp.setData("true");
			resp.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			resp.setData(new OCRField());
			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return new Gson().toJson(resp);
	}
}
