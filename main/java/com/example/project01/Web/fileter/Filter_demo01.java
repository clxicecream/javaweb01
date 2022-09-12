package com.example.project01.Web.fileter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*",dispatcherTypes = DispatcherType.REQUEST)
public class Filter_demo01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.强制转换为Httpservlet对象，因为ServletRequest不能实现我们的方法。所以我们可以转换成其孙子辈
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        //2.获取请求指向的资源路径
        String uri=request.getRequestURI();
        //3.判断该路径是不是指向登陆界面，如果不是判断是否已经登陆，如果没有该过滤器进行拦截
        //注意要排除css/js/图片/验证码等资源
if (uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/checkCodeServlet")){
    //开锁
    filterChain.doFilter(servletRequest,servletResponse);
}else {
    //不是请求的登陆界面，判断是否已经登陆驳回
    Object user=request.getSession().getAttribute("user");//获取session域里“user”的键值对【如果登陆了的话loginServlet一定会分装并且分享该对象】
    if (user==null){//证明没能登陆成功，请求驳回
    request.setAttribute("login_massage","您还没有登陆，请登陆。登陆后方能直接访问页面");//注意键值对要和jsp对应
    request.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
    }else {//已经登陆过了可以直接访问网址，开锁
        filterChain.doFilter(servletRequest,servletResponse);
    }

}

    }

    @Override
    public void destroy() {

    }
}
