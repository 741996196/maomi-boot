package com.yancy.boot.mapper.user.entity;

import javax.persistence.*;

@Table(name = "permission")
public class Permission {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 权限名
     */
    private String permissionname;

    /**
     * 外键关联role
     */
    @Column(name = "role_id")
    private Integer roleId;

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
     * 获取权限名
     *
     * @return permissionname - 权限名
     */
    public String getPermissionname() {
        return permissionname;
    }

    /**
     * 设置权限名
     *
     * @param permissionname 权限名
     */
    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    /**
     * 获取外键关联role
     *
     * @return role_id - 外键关联role
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置外键关联role
     *
     * @param roleId 外键关联role
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}