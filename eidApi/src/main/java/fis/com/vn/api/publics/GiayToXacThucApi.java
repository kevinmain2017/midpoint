package fis.com.vn.api.publics;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.common.HttpStatusApi;
import fis.com.vn.repository.MTypePaperRepository;
import fis.com.vn.resp.RespApi;
import fis.com.vn.table.MTypePaper;

@RestController
public class GiayToXacThucApi extends BaseApi{
	@Autowired MTypePaperRepository mTypePaperRepository;
	
	@GetMapping(value = {"/public/giay-to-xac-thuc", "/private/giay-to-xac-thuc"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String giayToXacThuc(HttpServletRequest req) {
		RespApi resp = new RespApi();
		try {
			Iterable<MTypePaper> mTypePapers = mTypePaperRepository.findAll();
			resp.setStatus(HttpStatusApi.THANH_CONG);
			resp.setData(mTypePapers);
		} catch (Exception e) {
			resp.setStatus(HttpStatusApi.THAT_BAI);
			resp.setMessage("Lấy thông tin thất bại");
		}
		
		return new Gson().toJson(resp);
	} 
}
