package com.ssafy.happyhouse.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class RootPathListener implements ServletContextListener {
		
    public void contextDestroyed(ServletContextEvent sce)  { 
    }
    public void contextInitialized(ServletContextEvent sce)  {
    	log.info("root set...");
    	ServletContext ctx = sce.getServletContext();
    	ctx.setAttribute("root", ctx.getContextPath());
    }
	
}
