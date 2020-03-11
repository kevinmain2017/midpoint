package fis.com.vn.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.MType;

@Repository
public interface MTypeRepository extends CrudRepository<MType, String> {

	@Query(value = "SELECT mt.* FROM m_type mt INNER JOIN m_user_type mut ON mut.type_code=mt.code WHERE mut.user_oid=:userOid", nativeQuery = true)
	Iterable<MType> findByUserOid(@Param("userOid") String userOid);


}
