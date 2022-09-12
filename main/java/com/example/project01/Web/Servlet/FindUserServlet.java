package com.example.project01.Web.Servlet;

import com.example.project01.Object.client;
import com.example.project01.databaseLink.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1.获取Id
String id=req.getParameter("id");
//2.在数据库中找到该Id对应的对象
        UserDao server=new UserDao();
        client user=server.FindUser_byid(id);
        //3.将该对象放入request里，分享给其他
        req.setAttribute("user",user);
        //4.转换界面(分享给前端)
        req.getRequestDispatcher("/update.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
