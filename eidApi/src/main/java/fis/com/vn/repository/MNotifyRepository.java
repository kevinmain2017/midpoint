package fis.com.vn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.MNotify;

@Repository
public interface MNotifyRepository extends CrudRepository<MNotify, String>{

	Iterable<MNotify> findByLoaiThongBao(String loaiThongBao);

}
