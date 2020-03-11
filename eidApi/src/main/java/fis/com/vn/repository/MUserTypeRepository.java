package fis.com.vn.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.MUserType;

@Repository
public interface MUserTypeRepository extends CrudRepository<MUserType, String>{

	MUserType findByUserOidAndTypeCode(String userOid, String typeCode);

	void deleteByOidAndUserOid(String oid, String userOid);

	@Modifying
	@Transactional
	void deleteByTypeCodeAndUserOid(String type_code, String user_oid);

}
