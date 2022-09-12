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

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1.设置编码
        req.setCharacterEncoding("utf-8");
        //2.获取update前端数据集合，键：name值
        Map<String,String[]> map=req.getParameterMap();
        //3.封装对象（注意属性name要与client的成员变量名字一样。否则错误）
        client user=new client();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//4.调用数据库操作类修改数据库
        System.out.println(user);
        UserDao server=new UserDao();
        server.update(user);
        //5.跳转userListServlet请求数据进而跳转到list.jsp中
        resp.sendRedirect(req.getContextPath()+"/findUserBypageServlet");

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
