package com.example.project01.Web.Servlet;

import com.example.project01.Object.client;
import com.example.project01.databaseLink.UserDao;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //1.设置编码，防止中文乱码问题
        req.setCharacterEncoding("utf-8");
        //2.获得用户数据
        //2.1获得用户输入验证码
        String input_check_code=req.getParameter("verifycode");//获得验证码，参数是input标签的name属性。表示获取该input标签里用户的数据
        //2.2在CheckCodeServlet里获得正确的验证码（通过HttpSession数据共享）
        HttpSession session=req.getSession();
        String check_code=(String)session.getAttribute("CHECKCODE_SERVER");//注意需要强制转换
        session.removeAttribute("CHECKCODE_SERVER");//除去共享数据，保证验证码只能使用一次
        //2.3验证验证码的正确性
        if (!check_code.equalsIgnoreCase(input_check_code)){//如果不正确
    //提示信息
            req.setAttribute("login_massage","验证码错误");
            //跳转页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }

        //3.封装对象
        client client=new client();
    //方式1（注意需要导包）【注意属性名一定=标签name属性值】
        // 获得参数集合
//        Map<String,String[]> map=req.getParameterMap();
//        try {
//            BeanUtils.populate(client,map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        //方式2
        String username=req.getParameter("user");
        String password=req.getParameter("password");
        client.setUsername(username);
        client.setPassword(password);
        //5.判断是否登陆成功
        UserDao server=new UserDao();
        client is_null=server.is_pass(client);
        if (is_null!=null){
            //登陆成功
            //1.共享该用户，存入session中
            session.setAttribute("user",client);
            //跳转页面
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            //登陆失败
            //提示信息
            req.setAttribute("login_massage","用户名/密码错误，请重试");
            //跳转页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
