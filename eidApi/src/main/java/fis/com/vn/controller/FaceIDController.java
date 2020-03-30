package fis.com.vn.controller;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fis.faceid.FaceData;
import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import com.fis.ocr.utils.Iconstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import fis.com.vn.entities.FaceId;
import fis.com.vn.entities.FaceIdInfo;
import fis.com.vn.entities.OCRField;
import fis.com.vn.repository.MUserImageRepository;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.Resp;
import fis.com.vn.table.MType;
import fis.com.vn.table.MUser;
import fis.com.vn.table.MUserImage;
import fis.com.vn.table.MUserType;

@Controller
public class FaceIDController extends BaseController{
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	@Autowired
	MUserImageRepository mUserImageRepository;
	@Autowired
	MUserRepository mserRepository;
	
	@PostMapping(value = "/api/register-valid-face-id")
	@ResponseBody
	public String registervalidOcr(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
        try {
        	String jsonInfoDb = FaceID.faceRecognition(allParams.get("file"));
    		System.out.println("jsonInfoDb:"+jsonInfoDb);
    		Type type = new TypeToken<ArrayList<FaceIdInfo>>() {}.getType();
    		ArrayList<FaceIdInfo> faceId = new Gson().fromJson(jsonInfoDb, type);
    		
    		if(faceId == null || faceId == null && faceId.size() <= 0) {
    			throw new Exception();
    		}
    		if(!faceId.get(0).getPosition().equals(allParams.get("card_id"))) {
    			resp.setData("false");
    			resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
    		}
    		
    		resp.setData("true");
			resp.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			resp.setData("false");
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		return new Gson().toJson(resp);
	}
	
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
	
	@GetMapping(value = "/api/check-traning-face-id")
	@ResponseBody
	public String checktraning(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		
		try {
			if(!FaceID.isTrain()) {
				resp.setData("true");
				resp.setStatusCode(HttpStatus.OK.value());
			} else {
				resp.setData("false");
				resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
			}
		} catch (Exception e) {
			resp.setData("false");
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		return new Gson().toJson(resp);
		
	}
	
	@PostMapping(value = "/api/traning-face-id")
	@ResponseBody
	public String traning(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		Resp resp = new Resp();
		try {
			FaceData faceData = new FaceData();
	        String i1 = allParams.get("file1");
	        String i2 = allParams.get("file2");
	        String i3 = allParams.get("file3");
	        // 3 cái này là Base64 anh nh
	        OCRField ocrField = new Gson().fromJson(allParams.get("json"), OCRField.class);
	      
	        faceData.setName(ocrField.getName());
	        //faceData.setName("Bui Duc Thien");
	        faceData.setIdcard(ocrField.getId());
	        faceData.setImages(Arrays.asList(i1,i2,i3));

	        // add image to db
//	        String datatraining = Iconstants.GSON.toJson(faceData);
//	        System.out.println(datatraining);
	        String json = FaceID.uploadTraning(Iconstants.GSON.toJson(faceData));
	        System.out.println(json);
	        // train

	        FaceID.train();
	        
			resp.setData("true");
			resp.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			resp.setData("false");
			resp.setStatusCode(HttpStatus.NOT_MODIFIED.value());
		}
		return new Gson().toJson(resp);
	}
	
	public Boolean checkFaceID(Map<String, String> allParams) {
		try {
			
			MUser mUser = mserRepository.findByOid(allParams.get("user_oid"));
	        String jsonInfo = FaceID.faceRecognition(allParams.get("file"));
	        
	        Type type = new TypeToken<ArrayList<FaceIdInfo>>() {}.getType();
    		ArrayList<FaceIdInfo> faceId = new Gson().fromJson(jsonInfo, type);
	        
//	        FaceId faceId = new Gson().fromJson(jsonInfo, FaceId.class);
	        System.out.println(jsonInfo);
	        if(faceId == null || (faceId != null && faceId.size() <=0)) {
	        	return false;
	        }
	        
			if(mUser.getEmployeeNumber().equals(faceId.get(0).getPosition())) {
				return true;
			}
	        
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
