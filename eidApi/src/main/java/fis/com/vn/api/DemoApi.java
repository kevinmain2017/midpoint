package fis.com.vn.api;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import fis.com.vn.controller.BaseController;
import fis.com.vn.resp.Resp;
import fis.com.vn.resp.RespApi;
import fis.com.vn.response.NoiDungOCR;
import fis.com.vn.table.MType;

@RestController
public class DemoApi extends BaseController{
	
	@GetMapping(value = "/public/demo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String get() {
		RespApi resp = new RespApi();
//		NoiDungOCR dungOCR = new NoiDungOCR();
//		dungOCR.setHoVaTen("abc");
//		dungOCR.setDanToc("KINH");
//		dungOCR.setGioiTinh("nam");
//		dungOCR.setNamSinh("27/01/1990");
//		dungOCR.setNoiTru("THÁI HỒNG, HUYỆN THÁI THỤY, THÁI BÌNH");
//		dungOCR.setQueQuan("XÃ THÁI HỒNG, HUYỆN THÁI THỤY, THÁI BÌNH");
//		dungOCR.setSoCmt("1234567788");
//		dungOCR.setTonGiao("Không");
//		
		MType mType = new MType();
		mType.setOid("a70c67c0-551d-48bc-b7e7-6ef0347c6ad6");
		mType.setName("Face ID");
		mType.setCode("faceid");
		
		ArrayList<MType> arrayList = new ArrayList<MType>();
		arrayList.add(mType);
		
		resp.setData(arrayList);
		resp.setStatus(200);
		resp.setMessage("");
		
		return new Gson().toJson(resp);
	}
}
