package fis.com.vn.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUserType;

@Controller
public class FaceIDController extends BaseController{
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	
	@PostMapping(value = "/api/valid-face-id")
	@ResponseBody
	public String validOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams, @RequestParam("file") byte[] file) {
		Resp resp = new Resp();
		
		if(checkFaceID(file)) {
			MUserType mUserTypeDb = mUserTypeRepository.findByUserOidAndTypeCode(allParams.get("user_oid"), allParams.get("type_code"));
			if(mUserTypeDb == null) {
				mUserTypeDb = new MUserType();
				mUserTypeDb.setOid(UUID.randomUUID().toString());
			} else {
				
			}
			mUserTypeDb.setUserOid(allParams.get("user_oid"));
			mUserTypeDb.setTypeCode(allParams.get("type_code"));
			mUserTypeDb.setInfo("");
			
			mUserTypeRepository.save(mUserTypeDb);
			
			resp.setData("true");
			resp.setStatusCode(HttpStatus.OK.value());
		} else {
			resp.setData("false");
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		return new Gson().toJson(resp);
	}
	public Boolean checkFaceID(byte[] file) {
		return true;
	}
}
