package com.blog.controller.admin;


import com.blog.pojo.User;
import com.blog.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request, Model model){
        if (username == null || password == null){
            return "admin/login";
        }
        User user = userService.checkUser(username);
        if (user != null && BCrypt.checkpw(password,user.getPassword())){
            model.addAttribute("message","登陆成功！");
            user.setPassword(null);
            request.getSession(true).setAttribute("user",user);
            return "admin/index";
        }
        model.addAttribute("message","密码错误！");
        return "admin/login";
    }

    /**
     * 退出登陆
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "re:admin/login";
    }
}
