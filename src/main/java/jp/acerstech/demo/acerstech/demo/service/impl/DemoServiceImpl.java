package jp.acerstech.demo.acerstech.demo.service.impl;

import jp.acerstech.demo.acerstech.demo.repository.UserRepository;
import jp.acerstech.demo.acerstech.demo.service.DemoService;
import jp.acerstech.demo.acerstech.demo.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserInfo> getUserInfoList(String department) {

        return userRepository.findByDepartment(department);
    }
}
