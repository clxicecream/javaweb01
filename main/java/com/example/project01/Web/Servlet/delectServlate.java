package com.example.project01.Web.Servlet;

import com.example.project01.databaseLink.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delectServlate")
public class delectServlate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");//获得前端数据
        //调用数据库操作对象删除
        UserDao server=new UserDao();
        server.delect(id);
        //跳转页面
        resp.sendRedirect(req.getContextPath()+"/findUserBypageServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
