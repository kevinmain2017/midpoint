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

import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;

@Controller
public class MUserTypeController extends BaseController{
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	
	@GetMapping(value = "/api/delete-user-type",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String deleteType(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		mUserTypeRepository.deleteByTypeCodeAndUserOid(allParams.get("type_code"), allParams.get("user_oid"));
		
		resp.setData("true");
		resp.setStatusCode(HttpStatus.OK.value());
		
		return new Gson().toJson(resp);
	}
}
