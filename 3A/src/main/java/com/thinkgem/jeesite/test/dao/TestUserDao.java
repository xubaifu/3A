package com.thinkgem.jeesite.test.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.test.entity.TestUserEntity;

@MyBatisDao
public interface TestUserDao {
	public List<TestUserEntity> findUser(Map<String,Object> params);
}
