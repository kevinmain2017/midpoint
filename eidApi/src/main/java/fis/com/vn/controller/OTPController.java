package fis.com.vn.controller;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import fis.com.vn.repository.MUserRepository;
import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUser;
import fis.com.vn.table.MUserType;

@Controller
public class OTPController extends BaseController{
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	@Autowired
	MUserRepository mUserRepository;
	
	private static Random generator = new Random();
	
	
	@GetMapping(value = "/api/get-phone-user")
	@ResponseBody
	public String getPhoneUser(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		try {
			MUser mUser = mUserRepository.findByOid(allParams.get("oid"));
			
			resp.setData(mUser);
			resp.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			resp.setMsg("Lỗi api");
			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}

		return new Gson().toJson(resp);
	}
	
	@GetMapping(value = "/api/get-code-otp")
	@ResponseBody
	public String getCodeOTP(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		try {
			String phone = allParams.get("phone");
			int code = randomNumber(1000, 9999);
			
			resp.setData(code);
			resp.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			resp.setMsg("Lỗi lấy mã OTP");
			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}

		return new Gson().toJson(resp);
	}
	public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
	@PostMapping(value = "/api/valid-otp")
	@ResponseBody
	public String validOTP(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		if(checkOTP(allParams)) {
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

	private boolean checkOTP(Map<String, String> allParams) {
		if(allParams.get("code").equals(allParams.get("compare_code"))) {
			return true;
		}
		return false;
	}

}
