package fis.com.vn.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "muc_xac_thuc")
public class MucXacThuc {
	@Id 
	String oid;
	@Column(name = "muc_do")
	String mucDo;
	
	@Column(name = "ma_loai_hinh_xt")
	String maLoaiHinhXt;
	
	@Column(name = "user_oid")
	String userOid;
}
