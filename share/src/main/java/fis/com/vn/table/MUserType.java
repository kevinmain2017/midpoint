package fis.com.vn.table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_user_type")
public class MUserType {
	@Id
	String oid;
	String typeCode;
	String userOid;
	String info;
}
