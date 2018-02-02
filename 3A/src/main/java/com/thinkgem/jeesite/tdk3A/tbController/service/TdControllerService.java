package com.thinkgem.jeesite.tdk3A.tbController.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.tbController.dao.TbControllerDAO;
import com.thinkgem.jeesite.tdk3A.tbController.entity.TBEntity;
@Service
public class TdControllerService extends CrudService<TbControllerDAO, TBEntity>{
	/*public Page<TBEntity> findPage(Page<TBEntity> page, TBEntity authorization) {
		return super.findPage(page, authorization);
	}*/
	@Resource
	private TbControllerDAO tbControllerDAO;
	
	public TBEntity findController(TBEntity tBEntity){
		return tbControllerDAO.findController(tBEntity);
	}
	public int insertController(TBEntity tBEntity){
		int num = tbControllerDAO.insert(tBEntity);
		int num2 = tbControllerDAO.insertTbDoor(tBEntity);
		
		return num;
	}
	public int updateController(TBEntity tBEntity){
		int num = tbControllerDAO.update(tBEntity);
		int num2 = tbControllerDAO.updateTbDoor(tBEntity);
		return num;
	}
	public int delController(TBEntity tBEntity){
		int num = tbControllerDAO.delete(tBEntity);
		int num2 = tbControllerDAO.deleteTbDoor(tBEntity);
		return num;
	}
	
	public List<TBEntity> getControllerById(TBEntity tBEntity){
		return tbControllerDAO.getControllerById(tBEntity);
	}
}
