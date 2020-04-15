package fis.com.vn.api.publics;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fis.faceid.FaceID;
import com.fis.ocr.OCRParser;
import com.google.gson.Gson;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.entities.OCRField;
import fis.com.vn.resp.RespApi;
import fis.com.vn.response.NoiDungOCR;

@RestController
public class DocNoiDungOcrApi extends BaseApi{
	@PostMapping(value = {"/public/doc-noi-dung-ocr", "/private/doc-noi-dung-ocr"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String docNoiDungOCr(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		RespApi respApi = new RespApi();
		try {
			Resource resource = new ClassPathResource("lbpcascade_frontalface_improved.xml");
			
			FaceID.init(resource.getFile().getAbsolutePath());
			
			OCRParser parser = new OCRParser();
	        String jsonOcr = parser.parsing(allParams.get("anhMatTruoc"), allParams.get("anhMatSau"));
	        
	        OCRField ocrField = new Gson().fromJson(jsonOcr, OCRField.class);
	        
	        NoiDungOCR noiDungOCR = new NoiDungOCR();
	        noiDungOCR.convert(ocrField);
	        
	        respApi.setStatus(HttpStatusApi.THANH_CONG);
	        respApi.setData(noiDungOCR);
	        
		} catch (Exception e) {
			respApi.setStatus(HttpStatusApi.THAT_BAI);
			respApi.setMessage("Đọc thông tin thất bại");
		}
		return new Gson().toJson(respApi);
	}
}
