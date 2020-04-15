package fis.com.vn.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_notify")
@Data
public class MNotify {
	@Id
	String oid;
	
	@Column(name = "content")
	String noiDung;
	
	@Column(name = "type")
	String loaiThongBao;
}
