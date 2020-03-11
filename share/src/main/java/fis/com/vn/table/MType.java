package fis.com.vn.table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_type")
public class MType {
	@Id
	String oid;
	String name;
	String code;
}
