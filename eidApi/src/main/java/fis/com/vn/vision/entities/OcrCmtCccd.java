package fis.com.vn.vision.entities;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OcrCmtCccd {
	//Số cmt
	@SerializedName(value = "soCmt", alternate = "id")
	String soCmt;
	
	//Tên
	@SerializedName(value = "hoVaTen", alternate = "name")
	String hoVaTen;
	
	//Ngày sinh
	@SerializedName(value = "namSinh", alternate = "dob")
	String namSinh;
	
	//Nguyên quán
	@SerializedName(value = "queQuan", alternate = "home")
	String queQuan;
	
	//Địa chỉ
	@SerializedName(value = "noiTru", alternate = "address")
	String noiTru;
	
	//Dân tộc
	@SerializedName(value = "danToc", alternate = "ethnicity")
	String danToc;
	
	//Tôn giáo
	@SerializedName(value = "tonGiao", alternate = "religion")
	String tonGiao;
	
	//Dấu vết riêng và dị hình
	@SerializedName(value = "dacDiemNhanDang", alternate = "features")
	String dacDiemNhanDang;
	
	//Ngày cấp
	@SerializedName(value = "ngayCap", alternate = "issue_date")
	String ngayCap;
	
	//Nơi cấp
	@SerializedName(value = "noiCap", alternate = "issue_loc")
	String noiCap;
	
	//Loại cmt
	@SerializedName(value = "loaiCmt", alternate = "type")
	String loaiCmt;
	
	@SerializedName(value = "loaiCmtKhac", alternate = "type_new")
	String loaiCmtKhac;
	
	//anh chân dung
	@SerializedName(value = "anhChanDung", alternate = "face")
	String anhChanDung;
}
