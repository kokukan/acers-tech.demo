package jp.acerstech.demo.acerstech.demo.service.impl;

import jp.acerstech.demo.acerstech.demo.repository.UserRepository;
import jp.acerstech.demo.acerstech.demo.repository.entity.Users;
import jp.acerstech.demo.acerstech.demo.service.DemoService;
import jp.acerstech.demo.acerstech.demo.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserInfo> getUserInfoList(String department) {


        List<Users>   users = userRepository.findByDepartment(department);
       return  users.stream().map(element -> UserInfo.builder()
                                                .age(element.getAge())
                                                .userId(element.getUserId())
                                                .name(element.getName())
                                                .department(element.getDepartment())
                                                .build())
               .collect(Collectors.toList());

    }
}
