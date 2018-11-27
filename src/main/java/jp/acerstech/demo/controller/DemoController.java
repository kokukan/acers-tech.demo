package jp.acerstech.demo.controller;

import jp.acerstech.demo.dto.SearchConditonDto;
import jp.acerstech.demo.domain.service.DemoService;
import jp.acerstech.demo.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/")
    public String getAllUsers(Model model) {

        List<UserInfo> users = demoService.getAll();
        model.addAttribute("userList", users);
        return "userlist";
    }

    @PostMapping("/getUserList")
    public String getUserList(Model model, @ModelAttribute SearchConditonDto dto) {

        List<UserInfo> users = demoService.getUserInfoList(dto);
        model.addAttribute("userList", users);
        return "userlist";

    }
}
