package test;

import lombok.Data;

@Data
public class NoiDungOCR {
	String hoVaTen;
	String soCmt;
	String namSinh;
	String gioiTinh;
	String danToc;
	String queQuan;
	String noiTru;
	String tonGiao;
	String noiCap;
	String ngayCap;
	String anhChanDung;
	String phone;
	String email;
	String tenFile;
	
	public void convert(OCRField ocrField) {
		hoVaTen = ocrField.getName();
		soCmt = ocrField.getId();
		namSinh = ocrField.getDob();
		gioiTinh = ocrField.getSex();
		danToc = ocrField.getEthnicity();
		queQuan = ocrField.getHome();
		noiTru = ocrField.getAddress();
		tonGiao = ocrField.getReligion();
		noiCap = ocrField.getIssue_location();
		ngayCap = ocrField.getIssue_date();
		anhChanDung = ocrField.getFace();
	}
}
