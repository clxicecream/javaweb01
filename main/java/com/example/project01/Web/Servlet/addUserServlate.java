package com.example.project01.Web.Servlet;

import com.example.project01.Object.client;
import com.example.project01.databaseLink.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlate")
public class addUserServlate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置字符集编码
        req.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String ,String[]> map=req.getParameterMap();//键就是前端name属性值
        //3.封装对象（这里采用简单的方法，你也可以一个一个的获得前端数据，然后一个一个封装成对象）
        //注意：该封装方法的风险——对象属性名必须和前端name属性值一样
        client user=new client();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用数据库处理类对象
        UserDao server=new UserDao();
        server.adduser(user);
        //5.跳转到UselistServlat，通过后端跳转到前端列表
        resp.sendRedirect(req.getContextPath()+"/findUserBypageServlet");


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
