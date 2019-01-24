package com.thinkgem.jeesite.modules.cus.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session util
 * 
 * @author dengyn
 *
 */
public class SessionUtil {
	/**
	 * 设置session属性
	 * @param request
	 * @param key
	 * @param obj
	 */
	public static void setSession(HttpServletRequest request, String key, Object obj)
	{
		HttpSession session = request.getSession();
		session.setAttribute(key, obj);
	}
	/**
	 * 查询session里相关属性
	 * @param request
	 * @param key
	 * @return
	 */
	public static Object getSession(HttpServletRequest request, String key){
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(key);
		return obj;
	}
	/**
	 * 移除session 属性
	 * @param request
	 * @param key
	 */
	public static void removeSession(HttpServletRequest request,String key){
		HttpSession session = request.getSession(false);//闃叉鍒涘缓Session
		session.removeAttribute(key);
	}
	/**
	 * 销毁session
	 * @param request
	 */
	public static void invalidate(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
}
