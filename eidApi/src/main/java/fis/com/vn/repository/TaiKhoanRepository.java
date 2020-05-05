package fis.com.vn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fis.com.vn.table.TaiKhoan;

@Repository
public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, String>{

	TaiKhoan findBySoTaiKhoan(String tenDangNhap);

}
