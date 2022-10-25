package com.nk.demoreset.pojo;

import com.nk.demoreset.mapper.LoginLogMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//实现了HttpSessionBindingListener接口的 JavaBean 对象可以感知自己被绑定到Session中和从Session 中删除的事件
public class User implements HttpSessionBindingListener {
    private Integer userId;
    private String name;
    private String username;
    private String password;
    private int departmentId;
    private String createTime;
    private String email;
    private String images;
    private int status;
    private String sessionId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*当对象被绑定到 HttpSession 对象中时，web 服务器调用该对象的 void valueBound方法
    当对象从 HttpSession 对象中解除绑定时，web 服务器调用该对象的 void valueUnbound(HttpSessionBindingEvent event)方法*/
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        LoginLogMapper loginLogMapper = Export.logMapper;
        HttpSession session = event.getSession();
        sessionId = session.getId();
        Integer userId = (Integer) session.getAttribute("userId");
        loginLogMapper.insertLoginLog(sessionId, getCurrDatetimeStr(), userId);
    }
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        LoginLogMapper loginLogMapper = Export.logMapper;
        loginLogMapper.updateLoginLog(sessionId, getCurrDatetimeStr());
    }

    private static String getCurrDatetimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }


}
