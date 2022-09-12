<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
<%--    删除功能的JS代码--%>
    <script>
        function deleteUser(id){
            //用户删除安全提示
            if (confirm("真的要删除该用户吗")){
                //跳转路径
                location.href="${pageContext.request.contextPath}/delectServlate?id="+id;
            }

        }
    //    页面加载完后再加载删除选中成员这个方法
        window.onload=function (){
            //给删除选中按钮添加事件
            document.getElementById("delSelected").onclick=function (){
                if (confirm("您确定要删除选中成员吗")){
                    //确定有无选中成员
                    var has_check=false;
                    var cbs=document.getElementsByName("uid");
                    for (var i=0;i<cbs.length;i++){
                        if (cbs[i].checked){has_check=true;}
                    }
                    //有选中成员，提交表单
                    if (has_check){
                        document.getElementById("form_del").submit();
                    }else {
                        alert("请先选中想要删除的成员")
                    }
                }
            }
            //全选操作
            document.getElementById("firstCb").onclick=function (){
                //1.获取列表所有的Cb
                var cbs=document.getElementsByName("uid");
                //2.遍历并且更改属性
                for (var i=0;i<cbs.length;i++){
                    cbs[i].checked=this.checked;
                }
            }
        }



    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
<%--使用bootstrap网站前端框架，选中心仪的表单代码直接复制粘贴即可--%>
<div style="float: left">
    <form class="form-inline" action="${pageContext.request.contextPath}/findUserBypageServlet" method="post">
        <div class="form-group">
            <label for="exampleInputName2">姓名</label>
            <input type="text" class="form-control" id="exampleInputName2" >
        </div>
        <div class="form-group">
            <label for="exampleInputage">年龄</label>
            <input type="text" class="form-control" id="exampleInputage">
        </div>
        <button type="submit" class="btn btn-default">查询</button>
    </form>
</div>
<%--按钮--%>
    <div style="float: right;margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加用户成员</a>
<%-- 给删除选中成员设置JS事件--%>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中用户成员</a>
    </div>

<%--删除选中用户--%>
    <form id="form_del" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="cl" varStatus="s">
<%-- 参数：服务器返回数据键值对的键，遍历对象即cl，表示第s次遍历--%>
            <tr>
<%--给复选框加value属性是为了让他除了有选中与否的功能以外还可以提交信息--%>
                <th><input type="checkbox" name="uid" value="${cl.id}"></th>
                <td>${s.count}</td>
                <td>${cl.name}</td>
                <td>${cl.gender}</td>
                <td>${cl.getAge()}</td>
                <td>${cl.QQ}</td>
                <td>${cl.email}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/FindUserServlet?id=${cl.getId()}">修改</a>&nbsp;
                        <%--跳转，并且将用户Id传到后端去,这里用JS实现--%>
                    <a class="btn btn-default btn-sm" href="javascript:deleteUser(${cl.id})">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    </form>
    <%--使用bootstrap网站前端框架，选择控件，分页找到中心仪的分页前端代码直接复制粘贴即可--%>
<div>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
<c:forEach begin="1" end="${pb.total_page}" var="i">
    <li><a href="${pageContext.request.contextPath}/findUserBypageServlet?currentPage=${i}&rows=5">${i}</a></li>
</c:forEach>

            <li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</div>

</div>
</body>
</html>
