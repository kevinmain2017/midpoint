//package fis.com.vn.controller;
//
//import java.util.Map;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.fis.ocr.OCRParser;
//import com.google.gson.Gson;
//
//import fis.com.vn.entities.OCRField;
//import fis.com.vn.repository.MUserRepository;
//import fis.com.vn.repository.MUserTypeRepository;
//import fis.com.vn.resp.Resp;
//import fis.com.vn.table.MUser;
//import fis.com.vn.table.MUserType;
//
//@Controller
//public class OcrController {
//	@Autowired
//	MUserTypeRepository mUserTypeRepository;
//	@Autowired
//	MUserRepository mserRepository;
//	
//	
//	@PostMapping(value = "/api/get-info-ocr")
//	@ResponseBody
//	public String getInfoOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
//		Resp resp = new Resp();
//		
//		try {
//			
//			OCRParser parser = new OCRParser();
//	        String jsonOcr = parser.parsingToJson(allParams.get("fileMattruoc"), allParams.get("fileMatSau"));
//			System.out.println("jsonOcr:"+jsonOcr);
//	        OCRField ocrField = new Gson().fromJson(jsonOcr, OCRField.class);
//			resp.setData(ocrField);
//			resp.setStatusCode(HttpStatus.OK.value());
//		} catch (Exception e) {
//			resp.setData(new OCRField());
//			resp.setMsg("Lỗi đọc dữ liệu từ ảnh.");
//			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
//		}
//		return new Gson().toJson(resp);
//	}
//	
//	
//	@PostMapping(value = "/api/valid-ocr")
//	@ResponseBody
//	public String validOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams, @RequestBody OCRField ocrField) {
//		Resp resp = new Resp();
//		
//		MUser mUser = mserRepository.findByOid(allParams.get("user_oid"));
//		
////		if(mUser.getEmployeeNumber().equals(ocrField.getId())) {
////			MUserType mUserType = mUserTypeRepository.findByUserOidAndTypeCode(ocrField.getUserOid(), ocrField.getTypeCode());
////			if(mUserType == null) {
////				mUserType = new MUserType();
////				mUserType.setOid(UUID.randomUUID().toString());
////			} else {
////				
////			}
////			mUserType.setUserOid(ocrField.getUserOid());
////			mUserType.setTypeCode(ocrField.getTypeCode());
////			mUserType.setInfo(new Gson().toJson(ocrField));
////			
////			mUserTypeRepository.save(mUserType);
////			
////			resp.setData("true");
////			resp.setStatusCode(HttpStatus.OK.value());
////		} else {
////			resp.setData("false");
////			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
////		}
//		return new Gson().toJson(resp);
//	}
//}
