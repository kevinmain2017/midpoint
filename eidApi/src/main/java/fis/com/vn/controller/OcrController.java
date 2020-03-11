package fis.com.vn.controller;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fis.ocr.OCRParser;
import com.google.gson.Gson;

import fis.com.vn.entities.OCRField;
import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUserType;

@Controller
public class OcrController {
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	
	
	@PostMapping(value = "/api/get-info-ocr")
	@ResponseBody
	public String getInfoOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams, @RequestParam("file1") byte[] file, @RequestParam("file2") byte[] file2) {
		Resp resp = new Resp();
		
		OCRParser parser = new OCRParser();
        System.err.println(parser.parsingToJson(Base64.getDecoder().decode(file), Base64.getDecoder().decode(file2)));
		
		resp.setData(new OCRField().createExample());
		resp.setStatusCode(HttpStatus.OK.value());
		return new Gson().toJson(resp);
	}
	
	@PostMapping(value = "/api/valid-ocr")
	@ResponseBody
	public String validOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams, @RequestBody OCRField ocrField) {
		Resp resp = new Resp();
		
		if(new OCRField().createExample().compare(ocrField)) {
			MUserType mUserType = mUserTypeRepository.findByUserOidAndTypeCode(ocrField.getUserOid(), ocrField.getTypeCode());
			if(mUserType == null) {
				mUserType = new MUserType();
				mUserType.setOid(UUID.randomUUID().toString());
			} else {
				
			}
			mUserType.setUserOid(ocrField.getUserOid());
			mUserType.setTypeCode(ocrField.getTypeCode());
			mUserType.setInfo(new Gson().toJson(ocrField));
			
			mUserTypeRepository.save(mUserType);
			
			resp.setData("true");
			resp.setStatusCode(HttpStatus.OK.value());
		} else {
			resp.setData("false");
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		return new Gson().toJson(resp);
	}
}
