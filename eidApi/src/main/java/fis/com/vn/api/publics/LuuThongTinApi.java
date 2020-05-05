package fis.com.vn.api.publics;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.common.Midpoint;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.ParamsUser;
import fis.com.vn.repository.TaiKhoanRepository;
import fis.com.vn.request.Params;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.TaiKhoan;

@RestController
public class LuuThongTinApi extends BaseApi{
	@Autowired Midpoint midpoint;
	@Autowired TaiKhoanRepository taiKhoanRepository;
	
	@PostMapping(value = "/public/luu-thong-tin", produces = MediaType.APPLICATION_JSON_VALUE)
	public String giayToXacThuc(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			JsonUser jsonUser = midpoint.createUserInsert(params);
			
			int check = midpoint.insertToApiMidPoint(new Gson().toJson(jsonUser), "users");
			if(check == 201) {
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setSoTaiKhoan(jsonUser.getUser().getName());
				taiKhoan.setSoTien("200000000");
				taiKhoan.setUserOid(jsonUser.getUser().getOid());
				
				taiKhoanRepository.save(taiKhoan);
				
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
