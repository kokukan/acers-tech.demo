package jp.acerstech.demo.domain.service;

import jp.acerstech.demo.domain.model.SearchConditionDto;
import jp.acerstech.demo.domain.model.UserInfo;

import java.util.List;


public interface DemoService {

    List<UserInfo>  getAll();
    List<UserInfo>  getUserInfoList(SearchConditionDto dto);
}
