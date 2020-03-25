package fis.com.vn.table;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "m_user_image")
public class MUserImage {
	@Id
	String oid;
	String userOid;
	
	@Lob
	byte[] image;
}
