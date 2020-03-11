package fis.com.vn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.table.MUser;

@Controller
public class DangNhapController extends BaseController{
	@GetMapping(value = "/login") 
	public String login(Model model, HttpServletRequest req) {
		
		
		return "login";
	}
	
	@PostMapping(value = "/login") 
	public String postLogin(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		try {
			UriComponentsBuilder builderChitieuTemplate = UriComponentsBuilder.fromHttpUrl(origin + Contains.URL_DANG_NHAP)
					.queryParam("userName", allParams.get("userName"))
					.queryParam("password", allParams.get("password"));
			
			String json = Request.get(builderChitieuTemplate,this.getAuthorizationToken(req));
			if(Request.getStatus(json) == 200) {
				MUser mUser = Request.getList(json, MUser.class, "data");
				
				req.getSession().setAttribute("token", "ganTamGiaTri");
				req.getSession().setAttribute("userName", mUser.getNameNorm());
				req.getSession().setAttribute("oid", mUser.getOid());
				return "redirect:/";
			} else {
				model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
			}
		} catch (Exception e) {
			model.addAttribute("error", "Lỗi api");
		}
		
		forwardParams(model, allParams);

		return "login";
	}
}
