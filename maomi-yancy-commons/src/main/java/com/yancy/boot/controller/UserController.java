package com.yancy.boot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yancy.boot.mapper.user.entity.User;
import com.yancy.boot.mapper.user.entity.Users;
import com.yancy.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/user")
@Api(value = "用户controller",tags = "用户接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 身份认证测试接口
     * @param request
     * @return
     */
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        return "success";
    }

    /**
     * 角色认证测试接口
     * @param request
     * @return
     */
    @RequestMapping("/student")
    public String student(HttpServletRequest request) {
        return "success";
    }

    /**
     * 权限认证测试接口
     * @param request
     * @return
     */
    @RequestMapping("/teacher")
    public String teacher(HttpServletRequest request) {
        return "success";
    }


    /**
     * 用户登录接口
     * @param
     * @param request request
     * @return string
     */
    @ApiOperation(value = "用户登录接口")
    @RequestMapping("/login")
    public String login(@ApiParam(name = "username",value = "用户名称") String username,@ApiParam(name = "password",value = "用户密码") String password,@ApiParam(name = "rememberMe",value = "记住我，boolean类型") Boolean rememberMe, HttpServletRequest request) {
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
//            request.getSession().setAttribute("user", user);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.toString());
//            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }


    @GetMapping("/userAll")
    @ResponseBody
    @ApiOperation(value = "查询所有用户")
    public List<User> userAll(@RequestBody User user){
        int q=9/0;
        return userService.userAll();
    }



    @GetMapping("/usersAll")
    @ResponseBody
    @ApiOperation(value = "分页查询用户")
    public PageInfo usersAll(@RequestParam(defaultValue = "1") @ApiParam(value = "当前页数",name = "pageNum") int pageNum,
                                @RequestParam(defaultValue = "10") @ApiParam(value = "每页行数",name = "pageSize") int pageSize){
        //初始化放入当前页数和每页行数
        PageHelper.startPage(pageNum,pageSize);
        //放入查询到是所有数据
        PageInfo pageInfo=new PageInfo(userService.usersAll());
        return pageInfo;
    }





}
