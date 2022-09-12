package com.example.project01.Web.Servlet;

import com.example.project01.databaseLink.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class deSelectedServlate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端传进来的id
        String[] ids=req.getParameterValues("uid");
        //调用数据库处理对象删除
        UserDao server=new UserDao();
        server.delSelectedUser(ids);
        //跳转到list
        resp.sendRedirect(req.getContextPath()+"/findUserBypageServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
