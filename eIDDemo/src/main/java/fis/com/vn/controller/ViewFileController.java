package fis.com.vn.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewFileController {
	@RequestMapping(value = { "/viewPdf" }, method = RequestMethod.GET)
	@ResponseBody
	public void loadFilePdf(HttpServletRequest req,HttpServletResponse resp) {

	    resp.setHeader("Expires", "0");
	    resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	    resp.setHeader("Pragma", "public");
	    resp.setHeader("Content-Type", "application/pdf");
	    resp.setHeader("Content-Disposition","inline;filename=abc.pdf");
	    try {
	    	OutputStream out = null;
	    	String strSignEncode = (String)req.getSession().getAttribute("viewPdf");
	    	req.getSession().removeAttribute("viewPdf");
	        out = resp.getOutputStream();
            byte[] b = Base64.getDecoder().decode(strSignEncode.getBytes());
            out.write(b,0,b.length);
            out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping(value = { "/view" }, method = RequestMethod.GET)
	@ResponseBody
	public void load(HttpServletRequest req,HttpServletResponse resp) {

	    resp.setHeader("Expires", "0");
	    resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	    resp.setHeader("Pragma", "public");
	    resp.setHeader("Content-Disposition","inline");
	    try {
	    	OutputStream out = null;
	    	String strSignEncode = (String)req.getSession().getAttribute("infoImage");
	        out = resp.getOutputStream();
            byte[] b = Base64.getDecoder().decode(strSignEncode.getBytes());
            out.write(b,0,b.length);
            out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping(value = { "/viewXml" }, method = RequestMethod.GET)
	@ResponseBody
	public void loadFile(HttpServletRequest req,HttpServletResponse resp) {

	    resp.setHeader("Expires", "0");
	    resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
	    resp.setHeader("Pragma", "public");
	    resp.setHeader("Content-Type", "application/xml");
	    resp.setHeader("Content-Disposition","inline;filename=abc.xml");
	    try {
	    	OutputStream out = null;
	    	String strSignEncode = (String)req.getSession().getAttribute("viewXml");
	    	req.getSession().removeAttribute("viewXml");
	        out = resp.getOutputStream();
            byte[] b = Base64.getDecoder().decode(strSignEncode.getBytes());
            out.write(b,0,b.length);
            out.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
