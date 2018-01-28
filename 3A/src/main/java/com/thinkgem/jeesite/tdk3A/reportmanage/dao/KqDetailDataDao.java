/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.reportmanage.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.systemmanagement.entity.KqOriginalityData;

/**
 * 考勤信息同步DAO接口
 * @author xubaifu
 * @version 2017-05-01
 */
@MyBatisDao
public interface KqDetailDataDao extends CrudDao<KqOriginalityData> {
	
}