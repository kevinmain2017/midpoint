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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.reflect.TypeToken;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.table.MType;

@Controller
public class XacThucController extends BaseController{
	@GetMapping(value = "/xac-thuc")
	public String dangKyXacThuc(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		req.getSession().removeAttribute("typeSaves");
		try {
			UriComponentsBuilder builderChitieuTemplate = UriComponentsBuilder.fromHttpUrl(origin + Contains.URL_DANH_SACH_XAC_THUC)
					.queryParam("user_oid", getOid(req));
			String json = Request.get(builderChitieuTemplate,this.getAuthorizationToken(req));
			if(Request.getStatus(json) == 200) {
				Type type = new TypeToken<ArrayList<MType>>() {}.getType();
				List<MType> mTypes = Request.getList(json, type, "data");
				
				model.addAttribute("mTypes", mTypes);
			} else {
				model.addAttribute("error", "Lỗi api");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forwardParams(model, allParams);
		return "xacthuc/xacThuc";
	}
	
	
	
	@GetMapping(value = "/xac-thuc/dieu-huong")
	public String dieuHuong(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams,
			@RequestParam(required = false) ArrayList<String> types, RedirectAttributes redirectAttributes) {
		
		if(types != null && types.size() > 0) {
			req.getSession().setAttribute("typeSaves", types);
			return redirect(types.get(0));
		} else {
			ArrayList<String> typeSaves = (ArrayList<String>) req.getSession().getAttribute("typeSaves");
			typeSaves.remove(0);
			if(typeSaves.size() > 0) {
				req.getSession().setAttribute("typeSaves", typeSaves);
				redirectAttributes.addFlashAttribute("success", model.getAttribute("success"));
				return redirect(typeSaves.get(0));
			} else {
				return "xacthuc/xacThucThanhCong";
			}
		}
	}
	
	public String redirect(String type) {
		if(type.equals(Contains.OCR)) {
			return "redirect:/xac-thuc/xac-thuc-ocr";
		} else if(type.equals(Contains.OTP)) {
			return "redirect:/xac-thuc/gui-ma-otp";
		} else if(type.equals(Contains.CHU_KY_SO)) {
			return "redirect:/xac-thuc/xac-thuc-ca";
		} else if(type.equals(Contains.FACE_ID)) {
			return "redirect:/xac-thuc/xac-thuc-face-id";
		} else if(type.equals(Contains.USER_PASS)) {
			return "redirect:/xac-thuc/xac-thuc-up";
		}
		return "xacthuc/xacThuc";
	}
}
