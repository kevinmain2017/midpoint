package fis.com.vn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.table.MUser;

@Controller
public class OTPController extends BaseController{
	@GetMapping(value = "/dang-ky-xac-thuc/gui-ma-otp")
	public String guiMaOTP(Model model, HttpServletRequest req) {
		
		try {
			String url = origin + Contains.URL_LAY_SO_DT_OTP+"?user_oid="+getOid(req)+"&type_code="+Contains.OTP;
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			String json = Request.get(builder, this.getAuthorizationToken(req));
			
			if(Request.getStatus(json) == 200) {
				MUser mUser = Request.getList(json, MUser.class, "data");
				model.addAttribute("phone", mUser.getPhone());
			}
		} catch (Exception e) {
		} 
		
		return "dangkyxacthuc/otp/guiMaOtp";
	}
	
	@GetMapping(value = "/xac-thuc/gui-ma-otp")
	public String guiMaOTP2(Model model, HttpServletRequest req) {
		
		try {
			String url = origin + Contains.URL_LAY_SO_DT_OTP+"?user_oid="+getOid(req)+"&type_code="+Contains.OTP;
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			String json = Request.get(builder, this.getAuthorizationToken(req));
			
			if(Request.getStatus(json) == 200) {
				String phone = Request.getAttrFromJson(json, "data");
				model.addAttribute("phone", phone);
			}
		} catch (Exception e) {
		} 
		
		return "xacthuc/otp/guiMaOtp";
	}
	
	@GetMapping(value = "/dang-ky-xac-thuc/xac-thuc-otp")
	public String xacThucOTP1(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
		if(getOTP(req, allParams)) {
			return "dangkyxacthuc/otp/xacThuc";
		} else {
			redirectAttributes.addFlashAttribute("error", "Lỗi gửi mã OTP");
			return "redirect:/dang-ky-xac-thuc/gui-ma-otp";
		}
	}
	@GetMapping(value = "/xac-thuc/xac-thuc-otp")
	public String xacThucOTP2(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
		if(getOTP(req, allParams)) {
			return "xacthuc/otp/xacThuc";
		} else {
			redirectAttributes.addFlashAttribute("error", "Lỗi gửi mã OTP");
			return "redirect:/xac-thuc/gui-ma-otp";
		}
		
	}
	public boolean getOTP(HttpServletRequest req, Map<String, String> allParams) {
		String url = origin + Contains.URL_LAY_MA_OTP+"?user_oid="+getOid(req)+"&type_code="+Contains.OTP+"&phone="+ allParams.get("phone");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		String json = Request.get(builder, this.getAuthorizationToken(req));
		
		if(Request.getStatus(json) == 200) {
			int code = Request.getIntFromJson(json, "data");
			req.getSession().setAttribute("code", code);
			return true;
		}
		return false;
	}
	@PostMapping(value = "/dang-ky-xac-thuc/xac-thuc-otp")
	public String postXacThucOTP(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
		boolean check = xacThucOTP(model, req, allParams);
		
		if(check) {
			return "dangkyxacthuc/otp/thanhCong";
		} else {
			return "dangkyxacthuc/otp/xacThuc";
		}
	}
	@PostMapping(value = "/xac-thuc/xac-thuc-otp")
	public String postXacThucOTP2(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
		boolean check = xacThucOTP(model, req, allParams);
		
		if(check) {
			redirectAttributes.addFlashAttribute("success", "Xác thực OTP thành công");
			return "redirect:/xac-thuc/dieu-huong";
		} else {
			return "xacthuc/otp/xacThuc";
		}
	}
	public boolean xacThucOTP (Model model, HttpServletRequest req, Map<String, String> allParams) {
		String url = origin + Contains.URL_DOI_CHIEU_THONG_TIN_OTP+"?"
				+ "user_oid="+getOid(req)+"&type_code="+Contains.OTP+"&code="+allParams.get("code")+"&compare_code="+req.getSession().getAttribute("code");
		String json = Request.post("", this.getAuthorizationToken(req), url);
		
		if(Request.getStatus(json) == 200) {
			req.getSession().removeAttribute("code");
			return true;
		} else {
			model.addAttribute("error", "Mã OTP không đúng");
			return false;
		}
	}
}
