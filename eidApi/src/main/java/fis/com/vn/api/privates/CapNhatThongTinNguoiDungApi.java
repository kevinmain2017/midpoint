package fis.com.vn.api.privates;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.common.Midpoint;
import fis.com.vn.midpoint.JsonModify;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.request.Params;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MUser;

@RestController
public class CapNhatThongTinNguoiDungApi extends BaseApi{
	@Autowired MUserRepository mUserRepository;
	@Autowired Midpoint midpoint;
	
	@PostMapping(value = "/private/cap-nhat-thong-tin-nguoi-dung", produces = MediaType.APPLICATION_JSON_VALUE)
	public String thongTinNguoiDung(HttpServletRequest req, Authentication authentication) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			JsonModify jsonModify = midpoint.createModify(params);
			int check = midpoint.modifyToApiMidPoint(new Gson().toJson(jsonModify), "users", mUser.getOid());
			if(check == 204) {
				
				resp.setStatus(HttpStatusApi.THANH_CONG);
			} else {
				resp.setStatus(HttpStatusApi.THAT_BAI);
				resp.setMessage("Sửa thông tin thất bại");
			}
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Cập nhật thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
