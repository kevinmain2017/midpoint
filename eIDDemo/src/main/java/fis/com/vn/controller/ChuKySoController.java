package fis.com.vn.controller;

import javax.servlet.http.HttpServletRequest;

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
	public String postXacThucOcr(Model model, HttpServletRequest req, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String url = origin + Contains.URL_KY_SO+"?user_oid="+getOid(req)+"&type_code="+Contains.CHU_KY_SO;
		String json = Request.postFile("", this.getAuthorizationToken(req), url, file);
		
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
		String url = origin + Contains.URL_KY_SO+"?user_oid="+getOid(req)+"&type_code="+Contains.CHU_KY_SO;
		String json = Request.postFile("", this.getAuthorizationToken(req), url, file);
		
		if(Request.getStatus(json) == 200) {
			redirectAttributes.addFlashAttribute("success", "Xác thực chữ ký số thành công");
			return "redirect:/xac-thuc/dieu-huong";
		} else {
			redirectAttributes.addFlashAttribute("error", "Xác thực thất bại");
		}
		
		return "redirect:/xac-thuc/xac-thuc-ca";
	}
}
