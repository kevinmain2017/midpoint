package fis.com.vn.api.publics;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.common.SendSMS;
import fis.com.vn.request.Params;
import fis.com.vn.resp.RespApi;

@RestController
public class GuiMaOtp extends BaseApi{
	private static Random generator = new Random();
	
	@PostMapping(value = {"/public/gui-ma-otp", "/private/gui-ma-otp"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String otp(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			String phone = params.getDienThoai();
			int code = randomNumber(1000, 9999);
			
			SendSMS.smsFpt(phone, "Mã xác thực "+code);
			
			resp.setData(code);
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setMessage("");
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Gửi thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
	public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}
