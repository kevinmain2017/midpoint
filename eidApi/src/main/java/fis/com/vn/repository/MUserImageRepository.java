package fis.com.vn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fis.com.vn.table.MUserImage;

public interface MUserImageRepository extends CrudRepository<MUserImage, String>{

	List<MUserImage> findByUserOid(String userOid);

}
