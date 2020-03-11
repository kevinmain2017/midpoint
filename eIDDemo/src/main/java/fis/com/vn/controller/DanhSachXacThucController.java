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
public class DanhSachXacThucController extends BaseController{
	@GetMapping(value = "/danh-sach-xac-thuc")
	public String dangKyXacThuc(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		
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
		return "danhsachxacthuc/danhSachXacThuc";
	}
	
	@GetMapping(value = "/danh-sach-xac-thuc/xoa")
	public String xoaDangKyXacThuc(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
		
		try {
			UriComponentsBuilder builderChitieuTemplate = UriComponentsBuilder.fromHttpUrl(origin + Contains.URL_XOA_XAC_THUC)
					.queryParam("user_oid", getOid(req)).queryParam("type_code", allParams.get("type_code"));
			String json = Request.get(builderChitieuTemplate,this.getAuthorizationToken(req));
			if(Request.getStatus(json) == 200) {
				redirectAttributes.addFlashAttribute("success", "Xóa phương thức xác thực thành công");
			} else {
				redirectAttributes.addFlashAttribute("error", "Lỗi api");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		forwardParams(model, allParams);
		return "redirect:/danh-sach-xac-thuc";
	}
}
