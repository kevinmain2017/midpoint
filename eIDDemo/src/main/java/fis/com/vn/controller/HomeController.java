package fis.com.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController{
	@GetMapping(value = "/")
	public String home() {
		return "home";
	}
}
