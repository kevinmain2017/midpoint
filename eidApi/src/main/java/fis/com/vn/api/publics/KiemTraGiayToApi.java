package fis.com.vn.api.publics;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.resp.RespApi;

@RestController
public class KiemTraGiayToApi extends BaseApi{
	
	@PostMapping(value = "/public/kiem-tra-giay-to", produces = MediaType.APPLICATION_JSON_VALUE)
	public String giayToXacThuc(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("Kiểm tra giấy tờ thành công");
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Kiểm tra giấy tờ thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
