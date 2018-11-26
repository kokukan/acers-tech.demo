package jp.acerstech.demo.acerstech.demo.repository;

import jp.acerstech.demo.acerstech.demo.vo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserInfo,String> {

     List<UserInfo>  findByDepartment(String department);
}
