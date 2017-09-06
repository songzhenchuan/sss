package org.lanqiao.service.impl;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.dao.impl.PasswordAnswerDaoImpl;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.service.PasswordAnswerService;

public class PasswordAnswerServiceImpl implements PasswordAnswerService {
	private PasswordAnswerDao dao=new PasswordAnswerDaoImpl();
	@Override
	public void insertPasswordAnswer(PasswordAnswer passwordAnswer) {
			dao.insert(passwordAnswer);

	}

}
