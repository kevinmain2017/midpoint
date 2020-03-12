package fis.com.vn.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.MUser;

@Repository
public interface MUserRepository extends CrudRepository<MUser, String>{

	@Query(value = "SELECT * FROM m_user where oid=:oid", nativeQuery = true)
	MUser selectParams(@Param("oid") String oid);

	MUser findByOid(String string);

	MUser findByNameNorm(String nameNorm);

	@Modifying
	@Transactional
	@Query(value = "UPDATE m_user SET password=?1, phone=?2 where oid=?3", nativeQuery = true)
	void update(String password, String phone, String oid);

	MUser findByNameNormAndPassword(String string, String md5);

}
