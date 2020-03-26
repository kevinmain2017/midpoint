package fis.com.vn.controller;

import java.io.FileInputStream;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUserType;
import vn.com.fis.esigncloud.eSignDemo;

@Controller
public class CaController extends BaseController{
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	
	final public static String agreementUUID = "202003042228"; //
    final public static String pwd = "25585901";
	
	@PostMapping(value = "/api/digital-signatures-register")
	@ResponseBody
	public String saveOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		MUserType mUserTypeDb = mUserTypeRepository.findByUserOidAndTypeCode(allParams.get("user_oid"), allParams.get("type_code"));
		if(mUserTypeDb == null) {
			mUserTypeDb = new MUserType();
			mUserTypeDb.setOid(UUID.randomUUID().toString());
		} else {
			
		}
		mUserTypeDb.setUserOid(allParams.get("user_oid"));
		mUserTypeDb.setTypeCode(allParams.get("type_code"));
		mUserTypeDb.setInfo(allParams.get("serial"));
		
		mUserTypeRepository.save(mUserTypeDb);
		
		resp.setData("true");
		resp.setStatusCode(HttpStatus.OK.value());
		
		return new Gson().toJson(resp);
	}
	
	@PostMapping(value = "/api/digital-signatures")
	@ResponseBody
	public String validOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams, @RequestParam(name="file", required=false) byte[] file) {
		Resp resp = new Resp();
		
		String strSignEncode = checkCa(file, allParams);
		if(strSignEncode != null) {
			
			resp.setData(strSignEncode);
			resp.setStatusCode(HttpStatus.OK.value());
		} else {
			resp.setData("false");
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		return new Gson().toJson(resp);
	}
	public String checkCa(byte[] file, Map<String, String> allParams) {
		try {
//			MUserType mUserTypeDb = mUserTypeRepository.findByUserOidAndTypeCode(allParams.get("user_oid"), allParams.get("type_code"));
			
			System.out.println(allParams.get("type_file"));
			Resource resource = new ClassPathResource("eSignCloud.p12");
			
			System.out.println(resource.getFile().getAbsolutePath());
	        byte[] signed = null;
	        if(allParams.get("type_file").equals("xml")) {
	        	String xmlData = new String(file, "UTF-8");
	        	signed = eSignDemo.signXmlUsingPassCode(agreementUUID, xmlData, pwd, resource.getFile().getAbsolutePath());
	        } else if(allParams.get("type_file").equals("pdf")) {
	        	signed = eSignDemo.signPdfUsingPassCode(agreementUUID, allParams.get("file"), pwd, resource.getFile().getAbsolutePath());
	        }
	        if(signed != null) {
	        	return Base64.getEncoder().encodeToString(signed);
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
}
