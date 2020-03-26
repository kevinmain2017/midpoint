package fis.com.vn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.entities.OCRField;

@Controller
public class OCRController extends BaseController{
	@GetMapping(value = "/dang-ky-xac-thuc/xac-thuc-ocr")
	public String xacThucOcr(Model model, HttpServletRequest req) {
		return "dangkyxacthuc/ocr/xacThucOcr";
	}
	@GetMapping(value = "/xac-thuc/xac-thuc-ocr")
	public String xacThucOcr2(Model model, HttpServletRequest req) {
		return "xacthuc/ocr/xacThucOcr";
	}
	@PostMapping(value = "/dang-ky-xac-thuc/xac-thuc-ocr")
	public String postXacThucOcr(Model model, HttpServletRequest req, @RequestParam("fileMattruoc") MultipartFile fileMattruoc , @RequestParam("fileMatSau") MultipartFile fileMatSau) {		
		boolean check = xacThucOcr(model, req, fileMattruoc, fileMatSau);
		if(check) {
			return "dangkyxacthuc/ocr/kiemTraThongTinOcr";
		}
		
		return "dangkyxacthuc/ocr/xacThucOcr";
	}
	@PostMapping(value = "/xac-thuc/xac-thuc-ocr")
	public String postXacThucOcr2(Model model, HttpServletRequest req, @RequestParam("fileMattruoc") MultipartFile fileMattruoc , @RequestParam("fileMatSau") MultipartFile fileMatSau) {		
		boolean check = xacThucOcr(model, req, fileMattruoc, fileMatSau);
		if(check) {
			return "xacthuc/ocr/kiemTraThongTinOcr";
		}
		
		return "xacthuc/ocr/xacThucOcr";
	}
	public boolean xacThucOcr(Model model, HttpServletRequest req, MultipartFile fileMattruoc, MultipartFile fileMatSau) {
		String json = Request.postFile("json", this.getAuthorizationToken(req), origin + Contains.URL_LY_THONG_TIN_OCR, fileMattruoc, fileMatSau);
		
		if(Request.getStatus(json) == 200) {
			OCRField oCRField = Request.getList(json, OCRField.class, "data");
			
			if(oCRField == null || oCRField != null && oCRField.getName()==null) {
				model.addAttribute("error", "Lỗi đọc dữ liệu từ ảnh, hãy đảm bảo chất lượng và đúng vị trí ảnh.");
				return false;
			}
			
			model.addAttribute("oCRField", oCRField);
			
			return true;
		} else {
			model.addAttribute("error", Request.getMessage(json));
		}
		return false;
	}
	
	@PostMapping(value = "/dang-ky-xac-thuc/doi-chieu-ocr")
	public String postDoiChieuOcr(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes attributes) {
		boolean check = doiChieuOcr(model, req, allParams, attributes);
		if(check) {
			return "dangkyxacthuc/ocr/thanhCongOcr";
		}
		
		return "redirect:/dang-ky-xac-thuc/xac-thuc-ocr";
	}
	@PostMapping(value = "/xac-thuc/doi-chieu-ocr")
	public String postDoiChieuOcr2(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes attributes) {
		boolean check = doiChieuOcr(model, req, allParams, attributes);
		if(check) {
			attributes.addFlashAttribute("success", "Xác thực OCR thành công");
			return "redirect:/xac-thuc/dieu-huong";
		}
		
		return "redirect:/xac-thuc/xac-thuc-ocr";
	}
	public boolean doiChieuOcr(Model model, HttpServletRequest req, Map<String, String> allParams, RedirectAttributes attributes) {
		try {
			OCRField ocrField = new OCRField();
			updateMapToObject(allParams, ocrField, OCRField.class);
			ocrField.setUserOid(getOid(req));
			ocrField.setTypeCode(Contains.OCR);
			
			String json = Request.post(new Gson().toJson(ocrField), this.getAuthorizationToken(req), origin + Contains.URL_DOI_CHIEU_THONG_TIN_OCR+"?user_oid="+getOid(req));
			
			if(Request.getStatus(json) == 200) {
				return true;
			} else {
				attributes.addFlashAttribute("error", "Lỗi xác thực");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", "Lỗi xác thực");
		}
		
		return false;
	}
}
