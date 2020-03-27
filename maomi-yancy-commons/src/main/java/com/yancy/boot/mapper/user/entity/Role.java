package com.yancy.boot.mapper.user.entity;

import javax.persistence.*;

@Table(name = "role")
public class Role {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return rolename - 角色名称
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名称
     *
     * @param rolename 角色名称
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}