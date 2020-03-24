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
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.ParamsUser;
import fis.com.vn.midpoint.User;

@Controller
public class DangKyController extends BaseController{
	@GetMapping(value = "/register") 
	public String login(Model model, HttpServletRequest req) {
		
		
		return "register";
	}
	
	@PostMapping(value = "/register") 
	public String postLogin(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,
			RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {
		try {
			ParamsUser paramsUser = new ParamsUser();
			paramsUser.setName(allParams.get("name"));
			paramsUser.setFullName(allParams.get("fullName"));
			paramsUser.setPassword(allParams.get("password"));
			paramsUser.setPhone(allParams.get("phone"));
			
			String json = Request.postFile(new Gson().toJson(paramsUser), this.getAuthorizationToken(req), origin + Contains.URL_DANG_KY, file, file1, file2);
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
