package fis.com.vn.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_type_paper")
public class MTypePaper {
	@Id
	String oid;
	
	@Column(name = "name")
	String ten;
	
	@Column(name = "code")
	String ma;
}
