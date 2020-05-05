package fis.com.vn.api.publics;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.entities.OCRField;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.request.Params;
import fis.com.vn.resp.RespApi;
import fis.com.vn.response.NoiDungOCR;
import fis.com.vn.table.MUser;

@RestController
public class DocNoiDungOcrApi extends BaseApi{
	@Autowired MUserRepository mUserRepository;
	
	@PostMapping(value = {"/public/doc-noi-dung-ocr", "/private/doc-noi-dung-ocr"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String docNoiDungOCr(HttpServletRequest req) {
		RespApi respApi = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			Resource resource = new ClassPathResource("lbpcascade_frontalface_improved.xml");
			
			FaceID.init(resource.getFile().getAbsolutePath());
			
			OCRParser parser = new OCRParser();
	        String jsonOcr = parser.parsing(params.getAnhMatTruoc(), params.getAnhMatSau());
	        
	        OCRField ocrField = new Gson().fromJson(jsonOcr, OCRField.class);
	        
	        NoiDungOCR noiDungOCR = new NoiDungOCR();
	        noiDungOCR.convert(ocrField);
	        
	        if(StringUtils.isEmpty(noiDungOCR.getSoCmt())) {
	        	throw new Exception("");
	        }
	        
	        if(req.getRequestURI().equals("/public/doc-noi-dung-ocr")) {
	        	MUser mUser = mUserRepository.findByTenDangNhap(noiDungOCR.getSoCmt());
	        	if(mUser != null) {
	        		respApi.setStatus(HttpStatusApi.NGHI_VAN_XAC_THUC);
	    			respApi.setMessage("Số chứng minh thư đã tồn tại");
	    			return new Gson().toJson(respApi);
	        	}
	        }
	        		
	        respApi.setStatus(HttpStatusApi.THANH_CONG);
	        respApi.setData(noiDungOCR);
	        
		} catch (Exception e) {
			respApi.setStatus(HttpStatusApi.THAT_BAI);
			respApi.setMessage("Đọc thông tin thất bại");
		}
		return new Gson().toJson(respApi);
	}
}
