package com.record.recordmvc.controller;

import com.record.recordmvc.result.Result;
import com.record.recordmvc.service.UserService;
import com.record.recordmvc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login (@RequestBody UserVO requestUser){
        String username=requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        UserVO userVO =userService.get(username,requestUser.getPassword());
        if(userVO!=null){
            return new Result(200);
        }else{
            return new Result(400);
        }
/*       无参验证方法
        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }*/

    }

}
