package org.lanqiao.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("有一个用户进来了。");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("有一个用户离开了。");
	}

}
