package jp.acerstech.demo.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import jp.acerstech.demo.domain.entity.Users;
import jp.acerstech.demo.domain.repository.UserRepository;
import jp.acerstech.demo.dto.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

public class DemoServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    DemoServiceImpl target;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll() {

        List<Users> userList= new ArrayList<>();
        Users user1 = new Users();
        user1.setUserId("user001");
        user1.setAge(20);
        Users user2 = new Users();
        user2.setUserId("user002");
        user2.setAge(25);
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        List<UserInfo> users = target.getAll();
        assertThat(users.size(),is(2));
    }
}