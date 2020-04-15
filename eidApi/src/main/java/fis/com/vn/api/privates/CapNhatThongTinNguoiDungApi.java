package fis.com.vn.api.privates;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MUser;

@RestController
public class CapNhatThongTinNguoiDungApi extends BaseApi{
	@Autowired MUserRepository mUserRepository;
	
	@PostMapping(value = "/private/cap-nhat-thong-tin-nguoi-dung", produces = MediaType.APPLICATION_JSON_VALUE)
	public String thongTinNguoiDung(HttpServletRequest req, Authentication authentication) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setData(mUser);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Cập nhật thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
