package com.lee.utils;


import com.lee.domain.User;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;
import java.util.Map;


public class CommonUtils {

    public static User getSessionUser() {
        return (User) getSession().getAttribute("loginUser");
    }

    public static HttpSession getSession() {

        return ServletActionContext.getRequest().getSession();
    }

}
