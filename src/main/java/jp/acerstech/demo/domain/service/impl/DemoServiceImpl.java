package jp.acerstech.demo.domain.service.impl;

import jp.acerstech.demo.dto.SearchConditonDto;
import jp.acerstech.demo.domain.repository.UserRepository;
import jp.acerstech.demo.domain.entity.Users;
import jp.acerstech.demo.domain.service.DemoService;
import jp.acerstech.demo.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserInfo> getUserInfoList(SearchConditonDto dto) {


        List<Users> users = userRepository.findAll(Specification.where(((root, query, criteriaBuilder) ->
                StringUtils.isEmpty(dto.getDepartment()) ? null : criteriaBuilder.like(root.get("department"), dto.getDepartment() + "%")))
        );
        return users.stream().map(this::convertToUserInfo)
                .collect(Collectors.toList());

    }

    public List<UserInfo> getAll() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(this::convertToUserInfo)
                .collect(Collectors.toList());

    }

    private UserInfo convertToUserInfo(Users users) {
        return UserInfo.builder()
                .age(users.getAge())
                .userId(users.getUserId())
                .name(users.getName())
                .department(users.getDepartment())
                .build();

    }
}
