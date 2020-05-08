package fis.com.vn.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tai_khoan")
public class TaiKhoan {
	@Id
	@Column(name = "so_tai_khoan")
	String soTaiKhoan;
	
	@Column(name = "so_tien")
	String soTien;
	
	@Column(name = "user_oid")
	String userOid;
}
