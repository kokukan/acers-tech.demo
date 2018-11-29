package jp.acerstech.demo.domain.repository;

import jp.acerstech.demo.infrastructure.entity.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<Users,String> {

     List<Users>  findByDepartment(String department);
     List<Users>  findAll(Specification specification);
}
