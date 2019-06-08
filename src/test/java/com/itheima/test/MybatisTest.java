package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MybatisTest
 * @Description TODO
 * @Author chenjinhua
 * @Date 2019-05-20 15:56
 * @Version 1.0
 **/
public class MybatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成字节输入流
         in = Resources.getResourceAsStream("SqlMapConfig.xml");
         //2,获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取sqlsession对象
         sqlSession = factory.openSession();
         //4.获取dao的代理对象
         userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destory() throws IOException {
        //5.提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试添加user
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("tony老师");
        user.setAddress("北京");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println("保存操作之前:"+user);

        userDao.saveUser(user);
        System.out.println("保存操作之后:"+user);
    }

    /**
     * 测试更新user
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(49);
        user.setUsername("tony");
        user.setAddress("北京东城");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    /**
     * 测试根据id删除user
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(49);
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testFindUserByCondition(){
        User user = new User();
        user.setUsername("老王");
        user.setSex("男");

        List<User> users = userDao.findUserByCondition(user);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
