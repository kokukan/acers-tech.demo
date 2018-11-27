package jp.acerstech.demo.acerstech.demo.service;

import jp.acerstech.demo.acerstech.demo.dto.SearchConditonDto;
import jp.acerstech.demo.acerstech.demo.dto.UserInfo;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;


public interface DemoService {

    List<UserInfo>  getAll();
    List<UserInfo>  getUserInfoList(SearchConditonDto dto);
}
