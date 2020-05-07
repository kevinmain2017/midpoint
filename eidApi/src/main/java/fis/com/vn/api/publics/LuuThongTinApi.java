package fis.com.vn.api.publics;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.common.Contains;
import fis.com.vn.common.FileHandling;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.common.Midpoint;
import fis.com.vn.midpoint.JsonUser;
import fis.com.vn.midpoint.JsonUserImage;
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
			Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
			
			Params params = gson.fromJson(text.trim(), Params.class);
			
			JsonUser jsonUser = midpoint.createUserInsert(params);
			
			int check = midpoint.insertToApiMidPoint(new Gson().toJson(jsonUser), Contains.LINK_INSERT_USER);
			
			if(check == 201) {
				xuLyAnhNhanDang(params, jsonUser.getUser().getOid());
				
				taoTaiKhoanNganHang(jsonUser);
				
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

	private void taoTaiKhoanNganHang(JsonUser jsonUser) {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setSoTaiKhoan(jsonUser.getUser().getName());
		taiKhoan.setSoTien("200000000");
		taiKhoan.setUserOid(jsonUser.getUser().getOid());
		
		taiKhoanRepository.save(taiKhoan);
	}

	private boolean xuLyAnhNhanDang(Params params, String oid) {
		if(params.getAnhNhanDang() == null || params.getAnhNhanDang().size() <= 0) {
			return false;
		}
		FileHandling fileHandling = new FileHandling();
		for (String anhNhanDang : params.getAnhNhanDang()) {
			luuAnhNhanDang(anhNhanDang, oid, fileHandling);
		}
		
		return true;
	}

	private void luuAnhNhanDang(String anhNhanDang, String oid, FileHandling fileHandling) {
		try {
			String pathFile = fileHandling.save(anhNhanDang, folder);
			if(!StringUtils.isEmpty(pathFile)) {
				JsonUserImage jsonUserImage = midpoint.createUserImageInsert(oid, pathFile);
				midpoint.insertToApiMidPoint(new Gson().toJson(jsonUserImage), Contains.LINK_INSERT_IMAGE);
			}
		} catch (Exception e) {
		}
	} 
}
