package fis.com.vn.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_user")
public class MUser {
	@Id
	String oid;
	@Column(name = "name_orig")
	String tenDangNhap;
	
	@Column(name = "fullName_orig")
	String hoVaTen;
	
	@Column(name = "dienThoai")
	String dienThoai;
	
	@Column(name = "soCmt")
	String soCmt;
	
	@Column(name = "namSinh")
	String namSinh;
	
	@Column(name = "gioiTinh")
	String gioiTinh;
	
	@Column(name = "danToc")
	String danToc;
	
	@Column(name = "queQuan")
	String queQuan;
	
	@Column(name = "noiTru")
	String noiTru;
	
	@Column(name = "tonGiao")
	String tonGiao;
	
	@Column(name = "ngayCap")
	String ngayCap;
	
	@Column(name = "noiCap")
	String noiCap;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "diaChi")
	String diaChi;
	
	@Column(name = "soHoChieu")
	String soHoChieu;
	
	@Column(name = "soGiayPhepLaiXe")
	String soGiayPhepLaiXe;
	
	@Column(name = "mstCaNhan")
	String mstCaNhan;
	
	@Column(name = "maSoBHXH")
	String maSoBHXH;
	
	@Column(name = "trangThai")
	String trangThai;
}
