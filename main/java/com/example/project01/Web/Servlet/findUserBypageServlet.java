package com.example.project01.Web.Servlet;

import com.example.project01.Object.client;
import com.example.project01.Object.pageBean;
import com.example.project01.databaseLink.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserBypageServlet")
public class findUserBypageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数（如果有的话）
        //1.1有参数传进来（即表面了要查看第几页的数据）
        String current_page=req.getParameter("currentPage");
        String rows=req.getParameter("rows");
        //1.2没有参数(则默认是第一页，一页最多5个对象)
        if (current_page==null||current_page==""){
            current_page="1";
            rows="5";
        }
        if (rows==null||rows==""){
            current_page="1";
            rows="5";
        }
        //2.调用数据库操作对象返回书页对象
        UserDao server=new UserDao();
        pageBean<client> page=server.findbypage(current_page,rows);
//3.分享数据并且转换页面到List.jsp
        req.setAttribute("pb",page);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }



}
