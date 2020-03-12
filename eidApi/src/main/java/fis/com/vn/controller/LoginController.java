package fis.com.vn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;

import fis.com.vn.common.Common;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUser;

@Controller
public class LoginController extends BaseController{
	@Autowired
	MUserRepository mUserRepository;
	
	@GetMapping(value = "/api/login",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String login(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		resp.setMsg(allParams.get("oid"));
		
		MUser mUser = mUserRepository.findByNameNormAndPassword(allParams.get("userName"), Common.getMD5(allParams.get("password")));
		if(mUser != null) {
			resp.setData(mUser);
			resp.setStatusCode(HttpStatus.OK.value());
		} else {
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		
		return new Gson().toJson(resp);
	}
}
