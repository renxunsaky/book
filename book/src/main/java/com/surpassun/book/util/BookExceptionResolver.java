package com.surpassun.book.util;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class BookExceptionResolver extends SimpleMappingExceptionResolver {
    
    private final static Logger log = Logger.getLogger(BookExceptionResolver.class.getName());
    
    @Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                           Exception ex) {
        
        if (ex instanceof HttpSessionRequiredException) {
            log.info("HttpSessionRequiredException");
            return new ModelAndView(ViewName.REDIRECT_HOME);
            
        }
        
        return super.doResolveException(request, response, handler, ex);
    }
}
