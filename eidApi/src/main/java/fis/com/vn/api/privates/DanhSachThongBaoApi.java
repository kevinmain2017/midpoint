package fis.com.vn.api.privates;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.repository.MNotifyRepository;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MNotify;

@RestController
public class DanhSachThongBaoApi extends BaseApi{
	@Autowired MNotifyRepository mNotifyRepository;
	
	@GetMapping(value = "/private/danh-sach-thong-bao", produces = MediaType.APPLICATION_JSON_VALUE)
	public String xt(HttpServletRequest req, Authentication authentication, @RequestParam Map<String, String> alllparams) {
		RespApi resp = new RespApi();
		try {
			Iterable<MNotify> mNotifys = mNotifyRepository.findByLoaiThongBao(alllparams.get("loaiThongBao"));
			
			resp.setData(mNotifys);
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
