package fis.com.vn.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.reflect.TypeToken;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.table.MType;

@Controller
public class DangKyXacThucController extends BaseController{
	@GetMapping(value = "/dang-ky-xac-thuc")
	public String dangKyXacThuc(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		
		try {
			UriComponentsBuilder builderChitieuTemplate = UriComponentsBuilder.fromHttpUrl(origin + Contains.URL_LOAI_XAC_THUC);
			String json = Request.get(builderChitieuTemplate,this.getAuthorizationToken(req));
			if(Request.getStatus(json) == 200) {
				Type type = new TypeToken<ArrayList<MType>>() {}.getType();
				List<MType> mTypes = Request.getList(json, type, "data");
				
				model.addAttribute("mTypes", mTypes);
			} else {
				model.addAttribute("error", "Lỗi api");
			}
			
			builderChitieuTemplate = UriComponentsBuilder.fromHttpUrl(origin + Contains.URL_DANH_SACH_XAC_THUC)
					.queryParam("user_oid", getOid(req));
			json = Request.get(builderChitieuTemplate,this.getAuthorizationToken(req));
			if(Request.getStatus(json) == 200) {
				Type type = new TypeToken<ArrayList<MType>>() {}.getType();
				List<MType> mTypes = Request.getList(json, type, "data");
				
				model.addAttribute("mTypeRegisters", mTypes);
			} else {
				model.addAttribute("error", "Lỗi api");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forwardParams(model, allParams);
		return "dangkyxacthuc/dangKyXacThuc";
	}
	
	
	
	@GetMapping(value = "/dang-ky-xac-thuc/dieu-huong")
	public String dieuHuong(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		String type = allParams.getOrDefault("type", "");
		if(type.equals(Contains.OCR)) {
			return "redirect:/dang-ky-xac-thuc/xac-thuc-ocr";
		} else if(type.equals(Contains.OTP)) {
			return "redirect:/dang-ky-xac-thuc/gui-ma-otp";
		} else if(type.equals(Contains.CHU_KY_SO)) {
			return "redirect:/dang-ky-xac-thuc/xac-thuc-ca";
		} else if(type.equals(Contains.FACE_ID)) {
			return "redirect:/dang-ky-xac-thuc/xac-thuc-face-id";
		} else if(type.equals(Contains.USER_PASS)) {
			return "redirect:/dang-ky-xac-thuc/xac-thuc-up";
		}
		
		return "dangkyxacthuc/dangKyXacThuc";
	}
}
