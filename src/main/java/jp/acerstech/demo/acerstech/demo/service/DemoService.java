package jp.acerstech.demo.acerstech.demo.service;

import jp.acerstech.demo.acerstech.demo.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DemoService {

    List<UserInfo>  getUserInfoList(String departmentNo);
}
