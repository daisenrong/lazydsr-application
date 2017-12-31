package com.lazydsr.manager.interceptor;


import com.lazydsr.manager.po.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户拦截类
 * Created by D.SR on 2016/11/2.
 */
@Slf4j
public class UserInterceptor extends HandlerInterceptorAdapter {
    private List allowedPass;

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕
     * 再执行被拦截的Controller
     * 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


//        log = new log<UserInterceptor>();

//        log.info("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();

        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        //System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        log.error("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        //System.out.println("访问地址:" + requestUri);
        log.error("访问地址:" + requestUri);


        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            //System.out.println("用户已登录");
            log.error("用户已登录,用户ID>>>>>>>" + user.getUserName() + ",用户IP地址>>>>" + request.getRemoteAddr() + ",用户主机>>>>" + request.getRemoteHost());
            //System.out.println("放行");
            log.error("放行");
            //System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            log.error("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            return true;
        }

        //for (Object s : allowedPass) {
        //    if (requestUri.contains((String) s)) {
        //        log.error("在可访问列表内,用户IP地址>>>>" + request.getRemoteAddr() + ",用户主机>>>>" + request.getRemoteHost());
        //        //System.out.println("放行");
        //        log.error("放行");
        //        //System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        //        log.error("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        //        return true;
        //    }
        //}
        //System.out.println("拦截");
        log.error("拦截");
        response.sendRedirect(request.getContextPath() + "/user/page/login");

        return false;


    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        log.info("==============执行顺序: 2、postHandle================");
//        if (modelAndView != null) {  //加入当前时间
//            modelAndView.addObject("var", "测试postHandle");
//        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * <p>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        log.info("==============执行顺序: 3、afterCompletion================");
    }

    public void setAllowedPass(List allowedPass) {
        this.allowedPass = allowedPass;
    }

    public List getAllowedPass() {
        return allowedPass;
    }
}
