package fis.com.vn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.MTypePaper;

@Repository
public interface MTypePaperRepository extends CrudRepository<MTypePaper, String>{

}
