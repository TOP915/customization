package com.thinkgem.jeesite.modules.cus.filter;

import com.thinkgem.jeesite.modules.cus.common.ConstantsWeb;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: SessionFilter
 * @Description: session过滤器，验证用户是否登录
 * @author: dengyn
 * @date: 2018/8/14 13:33
 */
public class SessionFilter implements Filter {
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String loginUrl = "/cususer/cusUserSessionOut.jsp";
        Object obSysUser = request.getSession().getAttribute(ConstantsWeb.SESSION_USER_INFO);
        if(obSysUser != null){
            chain.doFilter(request,response);
        }else{
            boolean isAjaxRequest = isAjaxRequest(request);
            if (isAjaxRequest) {
                response.setCharacterEncoding("UTF-8");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                return;
            }
            request.getRequestDispatcher(loginUrl).forward(request,response);
            return;
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
    /**
     * @Title: isAjaxRequest
     * @Description: 判断是否为Ajax请求
     * @throws Exception
     * @return: String
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }

}