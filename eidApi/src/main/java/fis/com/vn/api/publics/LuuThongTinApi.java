package fis.com.vn.api.publics;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.common.Midpoint;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.ParamsUser;
import fis.com.vn.resp.RespApi;

@RestController
public class LuuThongTinApi extends BaseApi{
	@Autowired Midpoint midpoint;
	
	@PostMapping(value = "/public/luu-thong-tin", produces = MediaType.APPLICATION_JSON_VALUE)
	public String giayToXacThuc(HttpServletRequest req, @RequestParam Map<String, String> allParams) {
		RespApi resp = new RespApi();
		try {
			ParamsUser paramsUser = new ParamsUser();
			updateMapToObject(allParams, paramsUser, ParamsUser.class);
			
			JsonUser jsonUser = midpoint.createUserInsert(paramsUser);
			
			int check = midpoint.insertToApiMidPoint(new Gson().toJson(jsonUser), "users");
			if(check == 201) {
				
				resp.setStatus(HttpStatusApi.THANH_CONG);
			} else {
				resp.setStatus(HttpStatusApi.THAT_BAI);
				resp.setData("Lưu thông tin thất bại");
			}
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("");
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpStatusApi.LOI_HE_THONG);
			resp.setMessage("Lưu thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
