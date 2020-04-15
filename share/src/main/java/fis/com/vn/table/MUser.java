package fis.com.vn.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name = "phone")
	String dienThoai;
	
	@Column(name = "employeeNumber")
	String soCmt;
	
	@Transient
	String namSinh;
	@Transient
	String gioiTinh;
	@Transient
	String danToc;
	@Transient
	String queQuan;
	@Transient
	String noiTru;
	@Transient
	String tonGiao;
	@Transient
	String ngayCap;
	@Transient
	String noiCap;
	@Transient
	String email;
	@Transient
	String diaChi;
	@Transient
	String soHoChieu;
	@Transient
	String soGiayPhepLaiXe;
	@Transient
	String mstCaNhan;
	@Transient
	String maSoBHXH;
}
