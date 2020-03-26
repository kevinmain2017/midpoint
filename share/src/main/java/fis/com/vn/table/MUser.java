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
	@Column(name = "name_norm")
	String nameNorm;
	String password;
	String phone;
	@Column(name = "employeeNumber")
	String employeeNumber;
}
