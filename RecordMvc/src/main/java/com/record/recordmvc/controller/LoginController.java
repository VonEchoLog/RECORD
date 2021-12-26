package com.record.recordmvc.controller;

import com.record.recordmvc.result.Result;
import com.record.recordmvc.vo.userVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class LoginController {


    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login (@RequestBody userVO userVo){
        String username=userVo.getUsername();
        username = HtmlUtils.htmlEscape(username);
        if (!Objects.equals("admin", username) || !Objects.equals("123456", userVo.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }

    }

}
