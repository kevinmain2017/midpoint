package fis.com.vn.vision.entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OcrHoChieu {
	//Số Hộ Chiếu
	@SerializedName(value = "soHoChieu", alternate = "passport_number")
	String soHoChieu;
	
	//Tên
	@SerializedName(value = "hoVaTen", alternate = "name")
	String hoVaTen;
	
	//Ngày sinh
	@SerializedName(value = "namSinh", alternate = "dob")
	String namSinh;
	
	//Nơi sinh
	@SerializedName(value = "queQuan", alternate = "pob")
	String queQuan;
	
	//Giới tính
	@SerializedName(value = "gioiTinh", alternate = "sex")
	String gioiTinh;
	
	//Sô ID
	@SerializedName(value = "soCmt", alternate = "id_number")
	String soCmt;
	
	//Ngày cấp
	@SerializedName(value = "ngayCap", alternate = "doi")
	String ngayCap;
	
	//Ngày hết hạn
	@SerializedName(value = "ngayHetHan", alternate = "doe")
	String ngayHetHan;
}
