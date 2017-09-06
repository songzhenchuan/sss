package org.lanqiao.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("application 消亡了");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("application 对象诞生");
		
	}
		
}
