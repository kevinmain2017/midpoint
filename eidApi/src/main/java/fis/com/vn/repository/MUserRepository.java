package fis.com.vn.repository;

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

}
