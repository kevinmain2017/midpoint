package fis.com.vn.api.privates;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MUser;

@RestController
public class LayBhxhApi extends BaseApi{
	@Autowired MUserRepository mUserRepository;
	
	@GetMapping(value = "/private/lay-thong-tin-bhxh", produces = MediaType.APPLICATION_JSON_VALUE)
	public String mst(HttpServletRequest req, Authentication authentication) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			
			MUser mUserGet = new MUser();
			mUserGet.setHoVaTen(mUser.getHoVaTen());
			mUserGet.setSoCmt(mUser.getSoCmt());
			mUserGet.setNamSinh(mUser.getNamSinh());
			mUserGet.setMaSoBHXH(mUser.getMaSoBHXH());
			mUserGet.setGioiTinh(mUser.getGioiTinh());
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setData(mUserGet);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Cập nhật thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
