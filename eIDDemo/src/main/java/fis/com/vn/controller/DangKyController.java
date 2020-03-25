package fis.com.vn.controller;

import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.entities.OCRField;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.ParamsUser;
import fis.com.vn.midpoint.User;

@Controller
public class DangKyController extends BaseController{
	@GetMapping(value = "/register") 
	public String login(Model model, HttpServletRequest req) {
		
		
		return "register";
	}
	
	@PostMapping(value = "/register/upload-training") 
	public String registerTraining(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,
			RedirectAttributes redirectAttributes, @RequestParam("file1") MultipartFile file1
			, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3) {
		try {
			
			String fileCmt = (String)req.getSession().getAttribute("infoImage");
			req.getSession().removeAttribute("infoImage");
			
			
//			String url = origin + Contains.URL_DOI_CHIEU_THONG_TIN_FACE_ID+"?user_oid="+getOid(req)+"&type_code="+Contains.FACE_ID;
//			String json = Request.postFileEncode("", this.getAuthorizationToken(req), url, file);
//			
//			if(Request.getStatus(json) == 200) {
//				return "dangkyxacthuc/faceid/thanhCong";
//			} else {
//				redirectAttributes.addFlashAttribute("error", "Ảnh nhận dạng không đúng.");
//			}
			
			return "registerComplete";
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi api");
		}
		
		forwardParams(model, allParams);

		return "register";
	}
	
	@PostMapping(value = "/register/upload") 
	public String registerUpload(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,
			RedirectAttributes redirectAttributes) {
		try {
			OCRField ocrField = new OCRField();
			updateMapToObject(allParams, ocrField, OCRField.class);
			
			req.getSession().setAttribute("ocrField", ocrField);
			return "registerUpload";
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi api");
		}
		
		forwardParams(model, allParams);

		return "register";
	}
	
	@PostMapping(value = "/register/detect") 
	public String registerDetect(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,
			RedirectAttributes redirectAttributes, @RequestParam("fileMattruoc") MultipartFile fileMattruoc, 
			@RequestParam("fileMatSau") MultipartFile fileMatSau) {
		try {
			String json = Request.postFile("json", this.getAuthorizationToken(req), origin + Contains.URL_LY_THONG_TIN_OCR, fileMattruoc, fileMatSau);
			
			if(Request.getStatus(json) == 200) {
				OCRField oCRField = Request.getList(json, OCRField.class, "data");
				
				if(oCRField == null || oCRField != null && oCRField.getName()==null) {
					model.addAttribute("error", "Lỗi đọc dữ liệu từ ảnh, hãy đảm bảo chất lượng và đúng vị trí ảnh.");
					return "register";
				}
				req.getSession().setAttribute("infoImage", Base64.getEncoder().encodeToString(fileMattruoc.getBytes()));
				model.addAttribute("oCRField", oCRField);
				
				return "registerDetectOcr";
			} else {
				model.addAttribute("error", Request.getMessage(json));
			}
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi api");
		}
		
		forwardParams(model, allParams);

		return "register";
	}
	
	@PostMapping(value = "/register") 
	public String postLogin(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,
			RedirectAttributes redirectAttributes) {
		try {
			OCRField ocrField = (OCRField) req.getSession().getAttribute("ocrField");
			
			ParamsUser paramsUser = new ParamsUser();
			paramsUser.setName(allParams.get("name"));
			paramsUser.setFullName(ocrField.getName());
			paramsUser.setPassword(allParams.get("password"));
			paramsUser.setPhone(allParams.get("phone"));
			paramsUser.setId(ocrField.getId());
			paramsUser.setAddress(ocrField.getAddress());
			paramsUser.setDob(ocrField.getDob());
			paramsUser.setEthnicity(ocrField.getEthnicity());
			paramsUser.setHome(ocrField.getHome());
			paramsUser.setReligion(ocrField.getReligion());
			
			System.out.println(new Gson().toJson(ocrField));
			req.getSession().removeAttribute("ocrField");
			
			
			String json = Request.post(new Gson().toJson(paramsUser), this.getAuthorizationToken(req), origin + Contains.URL_DANG_KY);
			if(Request.getStatus(json) == 200) {
				model.addAttribute("success", "Đăng ký thành công");
			} else {
				model.addAttribute("error", "Lỗi đăng ký");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi api");
		}
		
		forwardParams(model, allParams);

		return "register";
	}
}
