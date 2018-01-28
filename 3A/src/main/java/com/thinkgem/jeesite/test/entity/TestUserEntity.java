package com.thinkgem.jeesite.test.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TestUserEntity extends DataEntity<TestUserEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String loginName;

	public TestUserEntity() {
		super();
	}

	public TestUserEntity(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
