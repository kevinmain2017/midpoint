package fis.com.vn.api.privates;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.request.Params;
import fis.com.vn.request.ParamsCa;
import fis.com.vn.resp.RespApi;

@RestController
public class XacThucApi extends BaseApi{
	
	@PostMapping(value = "/private/xac-thuc-faceid", produces = MediaType.APPLICATION_JSON_VALUE)
	public String faceid(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("Xác thực thành công");
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Xác thực thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
	@PostMapping(value = "/private/xac-thuc-ca", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ca(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			ParamsCa paramsCa = gson.fromJson(text.trim(), ParamsCa.class);
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("Xác thực thành công");
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Xác thực thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
	@PostMapping(value = "/private/xac-thuc-ocr", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ocr(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("Xác thực thành công");
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Xác thực thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
	@PostMapping(value = "/private/xac-thuc-mat-khau", produces = MediaType.APPLICATION_JSON_VALUE)
	public String pw(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("Xác thực thành công");
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Xác thực thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
