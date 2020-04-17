package fis.com.vn.midpoint;

import lombok.Data;

@Data
public class User {
	String oid;
	String name;
	Assignment assignment;
	Activation activation;
	String fullName;
	String givenName;
	String familyName;
	String title;
	String employeeNumber;
	Credentials credentials;
	String noiTru;
	String dienThoai;
	String soCmt;
	String namSinh;
	String gioiTinh;
	String danToc;
	String queQuan;
	String tonGiao;
	String ngayCap;
	String noiCap;
	String email;
	String diaChi;
	String soHoChieu;
	String soGiayPhepLaiXe;
	String mstCaNhan;
	String maSoBHXH;
}
