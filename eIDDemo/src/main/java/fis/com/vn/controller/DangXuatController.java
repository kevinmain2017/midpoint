package fis.com.vn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangXuatController {
	@GetMapping(value = "/logout")
	public String logOut(HttpServletRequest req) {
		req.getSession().removeAttribute("userName");
		req.getSession().removeAttribute("token");
		req.getSession().removeAttribute("oid");
		
		return "redirect:/login";
	}
}
