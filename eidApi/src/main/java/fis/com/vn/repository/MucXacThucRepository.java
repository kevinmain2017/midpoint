package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.MucXacThuc;

@Repository
public interface MucXacThucRepository extends CrudRepository<MucXacThuc, String>{


	List<MucXacThuc> findByUserOid(String oid);

	MucXacThuc findByMucDoAndUserOid(String string, String oid);

}
