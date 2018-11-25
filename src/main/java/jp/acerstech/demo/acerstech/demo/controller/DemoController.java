package jp.acerstech.demo.acerstech.demo.controller;

import jp.acerstech.demo.acerstech.demo.service.DemoService;
import jp.acerstech.demo.acerstech.demo.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/getUserList/{departmentNo}")
    public String  getUserList(@PathVariable("departmentNo") String departmentNo, Model model) {

       List<UserInfo> users = demoService.getUserInfoList(departmentNo);
       model.addAttribute("userList",users);
        return "userlist";
    }
}
