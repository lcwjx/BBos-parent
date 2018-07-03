package com.lee.web.action;

import com.lee.domain.User;
import com.lee.service.IUserService;
import com.lee.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {


    //属性驱动，接收页面输入的验证码
    private String checkcode;
    public void setCheckcode(String checkCode) {
        this.checkcode = checkCode;
    }



    @Autowired
    private IUserService userService;

    /**
     * 用户登录
     */
    public String login(){
        //从Session中获取生成的验证码
        String validatecode = (String) ActionContext.getContext().getSession().get("key");
        //校验验证码是否输入正确
        if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
            //输入的验证码正确
            User user = userService.login(model);
            if(user != null){
                //登录成功,将user对象放入session，跳转到首页
                ActionContext.getContext().getSession().put("loginUser", user);
                return HOME;
            }else{
                //登录失败，,设置提示信息，跳转到登录页面
                //输入的验证码错误,设置提示信息，跳转到登录页面
                this.addActionError("用户名或者密码输入错误！");
                return LOGIN;
            }
        }else{
            //输入的验证码错误,设置提示信息，跳转到登录页面
            this.addActionError("输入的验证码错误！");
            return LOGIN;
        }
    }

    /**
     * 用户注销
     */
    public String logout(){
        ActionContext.getContext().getSession().clear();
        return LOGIN;
    }
}
