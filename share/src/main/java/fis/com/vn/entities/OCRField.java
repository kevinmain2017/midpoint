package fis.com.vn.entities;

import lombok.Data;

@Data
public class OCRField {
	String hoTen;
	String soCMT;
	String ngayThang;
	String gioiTinh;
	String danToc;
	String queQuan;
	String noiTru;
	String userOid;
	String typeCode;
	
	public OCRField createExample() {
		OCRField ocrField = new OCRField();
		ocrField.setDanToc("Kinh");
		ocrField.setGioiTinh("Nam");
		ocrField.setHoTen("Nguyễn Văn A");
		ocrField.setNgayThang("12/6/1980");
		ocrField.setNoiTru("Cầu giấy, Hà nội");
		ocrField.setQueQuan("Hà Nội");
		ocrField.setSoCMT("0589444777555");
		
		return ocrField;
	}
	
	public boolean compare(OCRField oCRField) {
		if(oCRField.getDanToc().equals(danToc)
				&& oCRField.getGioiTinh().equals(gioiTinh)
				&& oCRField.getHoTen().equals(hoTen)
				&& oCRField.getNgayThang().equals(ngayThang)
				&& oCRField.getNoiTru().equals(noiTru)
				&& oCRField.getQueQuan().equals(queQuan)
				&& oCRField.getSoCMT().equals(soCMT)
				) {
			return true;
		}
		
		return false;
	}
}
