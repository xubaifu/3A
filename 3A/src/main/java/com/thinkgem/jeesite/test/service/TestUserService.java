package com.thinkgem.jeesite.test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.test.dao.TestUserDao;
import com.thinkgem.jeesite.test.entity.TestUserEntity;

@Service
@Transactional(readOnly = true)
public class TestUserService {
	@Resource
	private TestUserDao dao;
	
	public List<TestUserEntity> findUser(Map<String,Object> params){
		List<TestUserEntity> result = dao.findUser(params);
		return result;
	}

}
