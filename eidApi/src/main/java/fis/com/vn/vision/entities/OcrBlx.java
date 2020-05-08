package fis.com.vn.vision.entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OcrBlx {
	//Số GPLX
	@SerializedName(value = "soGiayPhepLaiXe", alternate = "id")
	String soGiayPhepLaiXe;
	
	//Tên
	@SerializedName(value = "hoVaTen", alternate = "name")
	String hoVaTen;
	
	//Ngày sinh
	@SerializedName(value = "namSinh", alternate = "dob")
	String namSinh;
	
	//Quốc tịch
	@SerializedName(value = "quocTich", alternate = "nation")
	String quocTich;
	
	//Nơi cư trú
	@SerializedName(value = "noiTru", alternate = "address")
	String noiTru;
	
	//Nơi cấp
	@SerializedName(value = "noiCap", alternate = "place_issue")
	String noiCap;
	
	//Ngày cấp
	@SerializedName(value = "ngayCap", alternate = "date")
	String ngayCap;
	
	//Ngày hết hạn
	@SerializedName(value = "ngayHetHan", alternate = "doe")
	String ngayHetHan;
	
	//Mã số
	@SerializedName(value = "maSo", alternate = "code")
	String maSo;
	
	//hang
	@SerializedName(value = "hangGiayPhepLaiXe", alternate = "class")
	String hangGiayPhepLaiXe;
	
	//loại
	@SerializedName(value = "loaiGiayPhepLaiXe", alternate = "type")
	String loaiGiayPhepLaiXe;
}
