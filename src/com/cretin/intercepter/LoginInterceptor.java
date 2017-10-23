package com.cretin.intercepter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cretin.app.LogConstant;
import com.cretin.po.vo.CustomerUserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 获取注解
		Login login = handlerMethod.getMethodAnnotation(Login.class);

		if (null == login) {
			// 木有声明权限，可以放行
			return true;
		}
		HttpSession session = request.getSession();
		CustomerUserVo user = (CustomerUserVo) session.getAttribute(LogConstant.LOGIN_USER);
		String userid = (String) session.getAttribute(LogConstant.LOGIN_USERID);
		System.out.println("userid:"+userid);
		// 用户未登录
		if (null == user) {
			if (login.value() == ResultType.json) {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=UTF-8");
				OutputStream out = response.getOutputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "utf-8"));
				pw.println("{\n" + 
						"\"message\":\"请先登录\",\n" + 
						"\"code\":2"+
						"}");
				pw.flush();
				pw.close();
			}
			return false;
		}else {
			System.out.println("user:"+user.getUserId());
		}
		return true;
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。
	 * postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之 后，
	 * 也就是在Controller方法调用后执行，但是会在DispatcherServlet进行视图的渲染之前执行.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
	 * 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，主要是用来清理释放资源的
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}