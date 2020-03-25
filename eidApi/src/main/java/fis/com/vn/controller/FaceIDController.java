package fis.com.vn.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import com.google.gson.Gson;

import fis.com.vn.entities.FaceId;
import fis.com.vn.repository.MUserImageRepository;
import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MUserImage;
import fis.com.vn.table.MUserType;

@Controller
public class FaceIDController extends BaseController{
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	@Autowired
	MUserImageRepository mUserImageRepository;
	
	@PostMapping(value = "/api/valid-face-id")
	@ResponseBody
	public String validOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		if(checkFaceID(allParams)) {
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
	public Boolean checkFaceID(Map<String, String> allParams) {
		try {
			
			
	        String jsonInfo = FaceID.faceRecognition(allParams.get("file"));
	        FaceId faceId = new Gson().fromJson(jsonInfo, FaceId.class);
	        System.out.println(jsonInfo);
	        if(faceId.getBody() == null) {
	        	return false;
	        }
	        
	        FaceId faceIdDb = null;
	        
	        List<MUserImage> mUserImage = mUserImageRepository.findByUserOid(allParams.get("user_oid")); 
			for (MUserImage mUserImage2 : mUserImage) {
				String jsonInfoDb = FaceID.faceRecognition(new String(mUserImage2.getImage(), "utf-8"));
				System.out.println("jsonInfoDb:"+jsonInfoDb);
				faceIdDb = new Gson().fromJson(jsonInfoDb, FaceId.class);
				if(faceIdDb.getBody() != null) {
					break;
				}
			}
			if(faceIdDb.getBody() ==null) {
				return false;
			} else if(faceIdDb.getBody().get(0).getId().equals(faceId.getBody().get(0).getId())) {
				return true;
			}
	        
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
