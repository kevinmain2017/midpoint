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

import fis.com.vn.repository.MTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MType;

@Controller
public class MTypeController extends BaseController{
	@Autowired
	MTypeRepository mTypeRepository;
	
	@GetMapping(value = "/api/type",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String type(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		Iterable<MType> mTypes = mTypeRepository.findAll();
		
		resp.setData(mTypes);
		resp.setStatusCode(HttpStatus.OK.value());
		
		return new Gson().toJson(resp);
	}
	
	@GetMapping(value = "/api/list-type",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String listType(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		Iterable<MType> mTypes = mTypeRepository.findByUserOid(allParams.get("user_oid"));
		
		resp.setData(mTypes);
		resp.setStatusCode(HttpStatus.OK.value());
		
		return new Gson().toJson(resp);
	}
}
