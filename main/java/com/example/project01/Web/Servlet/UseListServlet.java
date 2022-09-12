package com.example.project01.Web.Servlet;

import com.example.project01.databaseLink.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.project01.Object.client;
import java.util.List;

@WebServlet("/userListServlet")
public class UseListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //1.调用数据库接口对象（自定义）
        UserDao dao=new UserDao();
        //2.调用方法得到数据库的数据
        List<client> clients=dao.findAll();
        //3.将数据转发到jsp页面
        req.setAttribute("client",clients);//将数据存入request域里
        req.getRequestDispatcher("/list.jsp").forward(req,resp);//跳转页面，并发送键值对



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
