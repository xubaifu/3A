package com.thinkgem.jeesite.tdk3A.tbController.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.tdk3A.tbController.entity.TBEntity;
@MyBatisDao
public interface TbControllerDAO extends CrudDao<TBEntity>{
	TBEntity findController(TBEntity tBEntity);
	
	int deleteTbDoor(TBEntity tBEntity);
	int updateTbDoor(TBEntity tBEntity);
	int insertTbDoor(TBEntity tBEntity);
	int insert(TBEntity tBEntity);
	int update(TBEntity tBEntity);
	int delete(TBEntity tBEntity);
	
	List<TBEntity> getControllerById(TBEntity tBEntity);
	
	List<TBEntity> getAllController();
	

}
