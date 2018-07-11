package com.lee.web.interceptor;

import com.lee.domain.User;
import com.lee.utils.CommonUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user = CommonUtils.getSessionUser();
        if (user == null) {
            return "login";
        }
        return actionInvocation.invoke();
    }
}
