package jp.acerstech.demo.application;

import java.util.List;
import jp.acerstech.demo.domain.model.UserInfo;
import jp.acerstech.demo.domain.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    @GetMapping("/")
    public String getAllUsers(Model model,@ModelAttribute DemoForm form) {

        List<UserInfo> users = demoService.getAll();
        form.setUserList(users);
        model.addAttribute("demoForm", form);
        return "userlist";
    }

    @PostMapping("/getUserList")
    public String getUserList(Model model, @ModelAttribute DemoForm form) {

        List<UserInfo> users = demoService.getUserInfoList(form.getSearchConditionDto());
        form.setUserList(users);
        model.addAttribute("demoForm", form);
        return "userlist";

    }
}
