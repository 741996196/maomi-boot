package com.yancy.boot.mapper.user.entity;

import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * 用户主键
     */
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 外键关联role表
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 获取用户主键
     *
     * @return id - 用户主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户主键
     *
     * @param id 用户主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取外键关联role表
     *
     * @return role_id - 外键关联role表
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置外键关联role表
     *
     * @param roleId 外键关联role表
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}