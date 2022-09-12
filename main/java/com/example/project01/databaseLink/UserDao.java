package com.example.project01.databaseLink;
import com.example.project01.JDBC_util.JDBCUtils;
import com.example.project01.Object.client;
import com.example.project01.Object.pageBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {
    //1.得到数据库连接池对象
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    public List<client> findAll(){
        //2.通过数据库连接池对象操作数据库
        //2.1定义SQl
        String sql="select * from player";
        //2.2得到一个用户对象数组，每个用户的属性都被数据库赋值了
        List<client> clientList=template.query(sql,new BeanPropertyRowMapper<client>(client.class));
    return clientList;
    }

    public client is_pass(client a){
        try {
            String SQL="select * from player where username= ? and password= ?";
            client user=template.queryForObject(SQL,new BeanPropertyRowMapper<client>(client.class),a.username,a.password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

public void adduser(client user){
        String SQL="insert into player values(null,?,?,?,?,?,null,null)";//编号，用户名和密码先设置为null
        //执行SQL
    template.update(SQL,user.getName(),user.getGender(),user.getAge(),user.getQQ(),user.getEmail());//注意顺序和名字一定要和数据库一致
}

public void delect(String id){
int id_int=Integer.parseInt(id);//先转换成和数据库相同类型的int类型
    String SQL="delete from player where id=?";
    template.update(SQL,id_int);

}

//找到对应于数据库的对象
    public client FindUser_byid(String id) {
    int id_int=Integer.parseInt(id);
    String SQL ="select * from player where id=?";
        //注意，保证client类的属性和数据库属性名对应否则该方法出问题(当然大小写不区分，但是字母至少要一样)
    return template.queryForObject(SQL,new BeanPropertyRowMapper<client>(client.class),id_int);
    }

    public void update(client user) {
String SQL="update player set gender = ?,age = ?,QQ = ?,email = ? where Id = ?";
template.update(SQL,user.getGender(),user.getAge(),user.getQQ(),user.getEmail(),user.getId());


    }
//批量删除用户
    public void delSelectedUser(String[] ids) {
//遍历数组
        for (String id:ids){
            this.delect(id);
        }

    }

    public pageBean<client> findbypage(String cp, String r) {
        int currnetpage=Integer.parseInt(cp);
        int rows=Integer.parseInt(r);
        //1.创造书页对象
        pageBean<client> page=new  pageBean<client>();
        //2.给它赋值
        page.setCurrent_page(currnetpage);
        page.setRows(rows);
        //2.1获取总记录数
        int totalcount=this.findtotalcount();
        page.setTotal_count(totalcount);
        //2.2查询该页对象集合
        int start_index=(currnetpage-1)*rows;//获取该页第一对象的索引/id
        List<client> list=this.find_currentpage_list(start_index,rows);
        page.setList(list);

        //2.3计算总页面
        int totalpage=(totalcount%rows) ==0? totalcount/rows:(totalcount/rows)+1;
        page.setTotal_page(totalpage);

        //3返回该书页对象
    return page;

    }

    private List<client> find_currentpage_list(int start_index, int rows) {
        String SQL="select * from player limit ? , ?";
        return template.query(SQL,new BeanPropertyRowMapper<client>(client.class),start_index,rows);
    }

    private int findtotalcount() {
        String SQL="select count(*) from player";
        return template.queryForObject(SQL,Integer.class);
    }
}
