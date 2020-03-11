package fis.com.vn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fis.ocr.OCRParser;
import com.google.gson.Gson;

import fis.com.vn.common.Contains;
import fis.com.vn.common.Request;
import fis.com.vn.entities.OCRField;

@Controller
public class OCRController extends BaseController{
	@GetMapping(value = "/dang-ky-xac-thuc/xac-thuc-ocr")
	public String xacThucOcr(Model model, HttpServletRequest req) {
		
		
		return "dangkyxacthuc/ocr/xacThucOcr";
	}
	
	@PostMapping(value = "/dang-ky-xac-thuc/xac-thuc-ocr")
	public String postXacThucOcr(Model model, HttpServletRequest req, @RequestParam("file") MultipartFile[] file) {
		
		File file1 = new File("C:\\Users\\vdc\\Pictures\\matsau.jpg");
        File file2 = new File("C:\\Users\\vdc\\Pictures\\mattruoc.jpg");
        byte[] fileContent1 = null;
        byte[] fileContent2 = null;
        try {
            fileContent1 = Files.readAllBytes(file1.toPath());
            fileContent2 = Files.readAllBytes(file2.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long s = System.currentTimeMillis();
        OCRParser parser1 = new OCRParser();
        System.err.println(parser1.parsingToJson(fileContent1, fileContent2));
        System.err.println(System.currentTimeMillis() - s + " ms");
		
		OCRParser parser = new OCRParser();
        try {
            byte[] bytes1 = IOUtils.toByteArray(file[0].getInputStream());
            byte[] bytes2 = IOUtils.toByteArray(file[1].getInputStream());
			System.err.println(parser.parsingToJson(bytes1, bytes2));
			
			System.out.println(Arrays.equals(fileContent1, bytes1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String json = Request.postFile("json", this.getAuthorizationToken(req), origin + Contains.URL_LY_THONG_TIN_OCR, file);
		
		if(Request.getStatus(json) == 200) {
			OCRField oCRField = Request.getList(json, OCRField.class, "data");
			
			model.addAttribute("oCRField", oCRField);
			
			return "dangkyxacthuc/ocr/kiemTraThongTinOcr";
		} else {
			model.addAttribute("error", "Lỗi api");
		}
		
		return "redirect:/dang-ky-xac-thuc/xac-thuc-ocr";
	}
	
	@PostMapping(value = "/dang-ky-xac-thuc/doi-chieu-ocr")
	public String postDoiChieuOcr(Model model, HttpServletRequest req, @RequestParam Map<String, String> allParams, RedirectAttributes attributes) {
		try {
			OCRField ocrField = new OCRField();
			updateMapToObject(allParams, ocrField, OCRField.class);
			ocrField.setUserOid(getOid(req));
			ocrField.setTypeCode(Contains.OCR);
			
			String json = Request.post(new Gson().toJson(ocrField), this.getAuthorizationToken(req), origin + Contains.URL_DOI_CHIEU_THONG_TIN_OCR);
			
			if(Request.getStatus(json) == 200) {
				
				return "dangkyxacthuc/ocr/thanhCongOcr";
			} else {
				attributes.addFlashAttribute("error", "Lỗi xác thực");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", "Lỗi xác thực");
		}
		
		return "redirect:/dang-ky-xac-thuc/xac-thuc-ocr";
	}
}
