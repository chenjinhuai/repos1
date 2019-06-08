package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 添加user
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新user
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除user
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);
}
