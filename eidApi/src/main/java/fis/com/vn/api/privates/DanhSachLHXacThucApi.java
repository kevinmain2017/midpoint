package fis.com.vn.api.privates;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.Contains;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.common.Midpoint;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.JsonUserType;
import fis.com.vn.repository.MTypeRepository;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.repository.MUserTypeRepository;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MType;
import fis.com.vn.table.MUser;
import fis.com.vn.table.MUserType;

@RestController
public class DanhSachLHXacThucApi extends BaseApi{
	@Autowired
	MTypeRepository mTypeRepository;
	@Autowired MUserRepository mUserRepository;
	@Autowired
	MUserTypeRepository mUserTypeRepository;
	@Autowired Midpoint midpoint;
	
	@GetMapping(value = "/private/loai-hinh-xac-thuc", produces = MediaType.APPLICATION_JSON_VALUE)
	public String xt(HttpServletRequest req, Authentication authentication) {
		RespApi resp = new RespApi();
		try {
			Iterable<MType> mTypes = mTypeRepository.findAll();
			
			resp.setData(mTypes);
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
	
	@GetMapping(value = "/private/loai-hinh-xac-thuc-dang-ky", produces = MediaType.APPLICATION_JSON_VALUE)
	public String xtdk(HttpServletRequest req, Authentication authentication) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			
			Iterable<MType> mTypes = mTypeRepository.findByUserOid(mUser.getOid());
			
			resp.setData(mTypes);
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
	
	@GetMapping(value = "/private/dang-ky-xac-thuc", produces = MediaType.APPLICATION_JSON_VALUE)
	public String dk(HttpServletRequest req, Authentication authentication, @RequestParam Map<String, String> allParams) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			
			MUserType mUserType = mUserTypeRepository.findByUserOidAndTypeCode(mUser.getOid(), allParams.get("code"));
			if(mUserType == null) {
				JsonUserType jsonUserType = midpoint.createUserTypeInsert(mUser.getOid(), allParams.get("thongTinBoXung"), allParams.get("code"));
				
				int check = midpoint.insertToApiMidPoint(new Gson().toJson(jsonUserType), Contains.LINK_INSERT_USER_TYPE);
				if(check == 201) {
					
					resp.setStatus(HttpStatusApi.THANH_CONG);
				} else {
					resp.setStatus(HttpStatusApi.THAT_BAI);
					resp.setData("Lưu thông tin thất bại");
				}
			} else {
				resp.setStatus(HttpStatusApi.THAT_BAI);
				resp.setMessage("Phương thức xác thực đã được đăng ký");
			}

			
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Đăng ký thất bại");
		}
		
		return new Gson().toJson(resp);
	}
}
