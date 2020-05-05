package fis.com.vn.api.privates;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fis.com.vn.api.publics.BaseApi;
import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.entities.ThongTinNguoiHuong;
import fis.com.vn.repository.MUserRepository;
import fis.com.vn.repository.MucXacThucRepository;
import fis.com.vn.repository.TaiKhoanRepository;
import fis.com.vn.request.ParamsCa;
import fis.com.vn.request.ParamsChuyenTien;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MNotify;
import fis.com.vn.table.MUser;
import fis.com.vn.table.MucXacThuc;
import fis.com.vn.table.TaiKhoan;

@RestController
public class DemoController extends BaseApi{
	@Autowired MUserRepository mUserRepository;
	@Autowired MucXacThucRepository mucXacThucRepository;
	@Autowired
	TaiKhoanRepository taiKhoanRepository;
	
	@GetMapping(value = "/private/ds-cai-dat-phuong-tien-xac-thuc")
	public String ds(HttpServletRequest req, Authentication authentication) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			
			List<MucXacThuc> mucXacThucs = mucXacThucRepository.findByUserOid(mUser.getOid());
			
			resp.setData(mucXacThucs);
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	}
	
	@GetMapping(value = "/private/tra-cuu-so-du-tai-khoan")
	public String get(HttpServletRequest req, Authentication authentication, @RequestParam Map<String, String> allParams) {
		RespApi resp = new RespApi();
		try {
			System.out.println(allParams.get("soTaiKhoan"));
			TaiKhoan taiKhoan = taiKhoanRepository.findBySoTaiKhoan(allParams.get("soTaiKhoan"));
			
			resp.setData(taiKhoan);
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	}
	
	@GetMapping(value = "/private/thong-tin-nguoi-huong")
	public String tt(HttpServletRequest req, Authentication authentication, @RequestParam Map<String, String> allParams) {
		RespApi resp = new RespApi();
		try {
			TaiKhoan taiKhoan = taiKhoanRepository.findBySoTaiKhoan(allParams.get("soTaiKhoan"));
			
			MUser mUser = mUserRepository.findByOid(taiKhoan.getUserOid());
			
			ThongTinNguoiHuong thongTinNguoiHuong = new ThongTinNguoiHuong();
			thongTinNguoiHuong.setHoVaTen(mUser.getHoVaTen());
			
			resp.setData(thongTinNguoiHuong);
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	}
	
	@PostMapping(value = "/private/cai-dat-phuong-tien-xac-thuc")
	public String cd(HttpServletRequest req, Authentication authentication, @RequestParam Map<String, String> allParams) {
		RespApi resp = new RespApi();
		try {
			String username = authentication.getName();
			MUser mUser = mUserRepository.findByTenDangNhap(username);
			
			MucXacThuc mucXacThuc = mucXacThucRepository.findByMucDoAndUserOid(allParams.get("mucDo"), mUser.getOid());
			if(mucXacThuc == null) {
				mucXacThuc = new MucXacThuc();
			} 
			
			mucXacThuc.setMaLoaiHinhXt(allParams.get("maLoaiHinhXt"));
			mucXacThuc.setMucDo(allParams.get("mucDo"));
			mucXacThuc.setUserOid(mUser.getOid());
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lưu thất bại");
		}
		
		return new Gson().toJson(resp);
	}
	
	@PostMapping(value = "/private/chuyen-tien")
	public String ct(HttpServletRequest req, Authentication authentication, @RequestParam Map<String, String> allParams) {
		RespApi resp = new RespApi();
		try {
			String text = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8.name());
			Gson gson = new GsonBuilder()
					   .setDateFormat("dd/MM/yyyy").create();
			
			ParamsChuyenTien paramChuyenTien = gson.fromJson(text.trim(), ParamsChuyenTien.class);
			
			thayDoiTien(paramChuyenTien);
			
			resp.setStatus(HttpStatusApi.THANH_CONG);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage(e.getMessage());
		}
		
		return new Gson().toJson(resp);
	}
	
	@Transactional
	public synchronized void thayDoiTien(ParamsChuyenTien paramChuyenTien) throws Exception {
		
		TaiKhoan taiKhoanChuyen = taiKhoanRepository.findBySoTaiKhoan(paramChuyenTien.getTaiKhoanNguoiChuyen());
		TaiKhoan taiKhoanNhan = taiKhoanRepository.findBySoTaiKhoan(paramChuyenTien.getTaiKhoanNguoiNhan());
	    if(convertStringToLong(taiKhoanChuyen.getSoTien()) < (convertStringToLong(paramChuyenTien.getSoTienChuyen())+convertStringToLong(paramChuyenTien.getSoTienPhiNguoiChuyen())) ) {
	    	throw new Exception("Tài khoản không đủ tiền chuyển");
	    }
	    long stChuyenCl = convertStringToLong(taiKhoanChuyen.getSoTien()) - (convertStringToLong(paramChuyenTien.getSoTienChuyen())+convertStringToLong(paramChuyenTien.getSoTienPhiNguoiChuyen()));
	    taiKhoanChuyen.setSoTien(String.valueOf(stChuyenCl));
	    long stNhanCl = convertStringToLong(taiKhoanNhan.getSoTien()) + convertStringToLong(paramChuyenTien.getSoTienChuyen()) -  convertStringToLong(paramChuyenTien.getSoTienPhiNguoiNhan());
	    taiKhoanNhan.setSoTien(String.valueOf(stNhanCl));
	    taiKhoanRepository.save(taiKhoanChuyen);
	    taiKhoanRepository.save(taiKhoanNhan);
	}
	public long convertStringToLong(String strVal) {
		return Long.valueOf(strVal);
	}
}
