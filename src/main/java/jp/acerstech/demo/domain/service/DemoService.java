package jp.acerstech.demo.domain.service;

import jp.acerstech.demo.dto.SearchConditonDto;
import jp.acerstech.demo.dto.UserInfo;

import java.util.List;


public interface DemoService {

    List<UserInfo>  getAll();
    List<UserInfo>  getUserInfoList(SearchConditonDto dto);
}
