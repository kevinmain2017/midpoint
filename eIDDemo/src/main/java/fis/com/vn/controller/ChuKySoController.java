package fis.com.vn.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;

@Controller
public class ChuKySoController extends BaseController{
	@GetMapping(value = "/dang-ky-xac-thuc/xac-thuc-ca")
	public String xacThucOcr(Model model, HttpServletRequest req) {
		
		
		return "dangkyxacthuc/chukyso/xacThuc";
	}
	
	@PostMapping(value = "/dang-ky-xac-thuc/xac-thuc-ca")
	public String postXacThucOcr(Model model, HttpServletRequest req, RedirectAttributes redirectAttributes, @RequestParam Map<String , String> allParams) {
		String url = origin + Contains.URL_DANG_KY_KY_SO+"?user_oid="+getOid(req)+"&type_code="+Contains.CHU_KY_SO+"&serial="+allParams.get("serial");
		String json = Request.post("", this.getAuthorizationToken(req), url);
		
		if(Request.getStatus(json) == 200) {
			return "dangkyxacthuc/chukyso/thanhCong";
		} else {
			redirectAttributes.addFlashAttribute("error", "Xác thực thất bại");
		}
		
		return "redirect:/dang-ky-xac-thuc/xac-thuc-ca";
	}
	@GetMapping(value = "/xac-thuc/xac-thuc-ca")
	public String xacThucOcr2(Model model, HttpServletRequest req) {
		
		
		return "xacthuc/chukyso/xacThuc";
	}
	
	@PostMapping(value = "/xac-thuc/xac-thuc-ca")
	public String postXacThucOcr2(Model model, HttpServletRequest req, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String typeFile = FilenameUtils.getExtension(file.getOriginalFilename());
		
		String url = origin + Contains.URL_KY_SO+"?user_oid="+getOid(req)+"&type_code="+Contains.CHU_KY_SO+"&type_file="+typeFile;
		
		String json = null;
		if(typeFile.equals("pdf")) {
			json = Request.postFileEncode("", this.getAuthorizationToken(req), url, file);
			String strSignEncode = Request.getAttrFromJson(json, "data");
			req.getSession().setAttribute("viewPdf", strSignEncode);
			System.out.println(strSignEncode);
		} else if( typeFile.equals("xml")){
			String strSignEncode = Request.getAttrFromJson(json, "data");
			req.getSession().setAttribute("viewXml", strSignEncode);
			System.out.println(strSignEncode);
			json = Request.postFile("", this.getAuthorizationToken(req), url, file);
		}
		
		if(Request.getStatus(json) == 200) {
			if(typeFile.equals("pdf")) {
				String strSignEncode = Request.getAttrFromJson(json, "data");
				req.getSession().setAttribute("viewPdf", strSignEncode);
			} else if( typeFile.equals("xml")){
				String strSignEncode = Request.getAttrFromJson(json, "data");
				byte[] b = Base64.getDecoder().decode(strSignEncode.getBytes());
				model.addAttribute("xml", new String(b));
			}
			model.addAttribute("typeFile", typeFile);
			model.addAttribute("success", "Xác thực chữ ký số thành công");
			return "xacthuc/chukyso/viewFileKy";
//			return "redirect:/xac-thuc/dieu-huong";
		} else {
			req.getSession().removeAttribute("viewPdf");
			req.getSession().removeAttribute("viewxml");
			redirectAttributes.addFlashAttribute("error", "Xác thực thất bại");
		}
		
		return "redirect:/xac-thuc/xac-thuc-ca";
	}
}
