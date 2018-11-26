package jp.acerstech.demo.acerstech.demo.repository;

import jp.acerstech.demo.acerstech.demo.repository.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<Users,String> {

     List<Users>  findByDepartment(String department);
}
