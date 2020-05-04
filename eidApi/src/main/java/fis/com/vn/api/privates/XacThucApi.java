package fis.com.vn.api.privates;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.resp.RespApi;

@RestController
public class XacThucApi extends BaseApi{
	
	@PostMapping(value = "/private/xac-thuc-faceid", produces = MediaType.APPLICATION_JSON_VALUE)
	public String faceid(HttpServletRequest req) {
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
	@PostMapping(value = "/private/xac-thuc-ca", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ca(HttpServletRequest req) {
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
	@PostMapping(value = "/private/xac-thuc-ocr", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ocr(HttpServletRequest req) {
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
