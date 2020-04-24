package com.yancy.boot.service;

import com.yancy.boot.mapper.user.entity.Permission;
import com.yancy.boot.mapper.user.entity.Role;
import com.yancy.boot.mapper.user.entity.User;
import com.yancy.boot.mapper.user.entity.Users;
import com.yancy.boot.mapper.user.mapper.PermissionMapper;
import com.yancy.boot.mapper.user.mapper.RoleMapper;
import com.yancy.boot.mapper.user.mapper.UserMapper;
import com.yancy.boot.mapper.user.mapper.UsersMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    public User getUser(String username){
        User user=new User();
        user.setUsername(username);
        user=userMapper.selectOne(user);
        return user;
    }

    public Set<String> getRoles(String username){
        User user=getUser(username);
        Role role=new Role();
        role.setId(user.getRoleId());
        List<Role> roles=roleMapper.select(role);
        Set<String> s=new HashSet<>();
        for (Role role1 : roles){
            s.add(role1.getRolename());
        }
        return s;
    }

    public Set<String> getPermissions(String username){
        User user=getUser(username);
        Role role=new Role();
        role.setId(user.getRoleId());
        List<Role> roles=roleMapper.select(role);
        Set<String> s=new HashSet<>();
        Permission permission=new Permission();
        for(Role r :roles){
            permission.setRoleId(r.getId());
            List<Permission> permissions=permissionMapper.select(permission);
            for(Permission p:permissions){
                s.add(p.getPermissionname());
            }
        }
        return s;

    }

    public List<User> userAll(){
        return userMapper.userAll();
    }

    public List<Users> usersAll(){
        return usersMapper.selectAll();
    }

}
