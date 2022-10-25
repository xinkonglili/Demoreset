package com.nk.demoreset.controller;
import com.nk.demoreset.pojo.User;
import com.nk.demoreset.service.LoginLogService;
import com.nk.demoreset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yanzhengma.Verify;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    LoginLogService loginLogService;
    public static Map<String,String> online=new HashMap<>();
    @RequestMapping("/login")
    protected String login(HttpServletRequest req, HttpServletResponse resp, String username, String password,String verifyCode ) throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {
            return "index";
        } else {
            //  1、获取请求的参数
            String vCode = (String) req.getSession().getAttribute("vCode");
            if (1==1) {
                // 调用 userService.login()登录处理业务
                User user = userService.login(new User(username, password));
                // 如果等于null,说明登录 失败!
                if (user == null) {
                    // 把错误信息，和回显的表单项信息，保存到Request域中
                    req.setAttribute("msg", "用户或密码错误！");
                    req.setAttribute("username", username);
                    //   跳回登录页面
                    return "login";
                } else {
                    if (user.getStatus() == 0) {
                        req.setAttribute("msg", "账号已被禁用");
                        return "login";
                    } else {
                        int userId = user.getUserId();
                        String oldLoginTime = loginLogService.getPreLoginTimeByUserId(userId);
                        Object o = online.get(req.getSession().getId());
                        if(o==null){
                            // 登录 成功
                            online.put(req.getSession().getId(),"");
                            HttpSession session = req.getSession();
                            session.setAttribute("username", username);
                            session.setAttribute("userId", userId);
                            session.setAttribute("user", user);
                            session.setAttribute("oldLoginTime", oldLoginTime);
                            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(session.getId(), "utf-8"));
                            c.setPath("/");
                            c.setMaxAge(2 * 60 * 60);
                            resp.addCookie(c);
                            return "index";
                        }else{
                            req.setAttribute("msg", "账号已登录！");
                            return "login";
                        }

                    }
                }
            } else {
                req.setAttribute("msg", "验证码错误");
                return "login";
            }
        }
    }

    /**
     * 生成验证码
     * */
    @RequestMapping("/createVerifyCode")
    public void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成一个验证码
        try {
            Verify.getVirify(request,response);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
