/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.tdk3A.temporarycard.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.tdk3A.temporarycard.dao.TemporaryCardDao;
import com.thinkgem.jeesite.tdk3A.temporarycard.entity.TemporaryCardEntity;

/**
 * 临时卡管理Service
 * @author baifu
 * @version 2017-03-05
 */
@Service
@Transactional(readOnly = true)
public class TemporaryCardService extends CrudService<TemporaryCardDao, TemporaryCardEntity> {
	@Resource
	private TemporaryCardDao temporaryCardDao;
	public TemporaryCardEntity get(String id) {
		return super.get(id);
	}
	
	public List<TemporaryCardEntity> findList(TemporaryCardEntity temporaryCard) {
		return super.findList(temporaryCard);
	}
	
	public Page<TemporaryCardEntity> findPage(Page<TemporaryCardEntity> page, TemporaryCardEntity temporaryCard) {
		return super.findPage(page, temporaryCard);
	}
	
	@Transactional(readOnly = false)
	public void save(TemporaryCardEntity temporaryCard) {
		super.save(temporaryCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(TemporaryCardEntity temporaryCard) {
		super.delete(temporaryCard);
	}
	/**
	 * 临时卡管理
	 * @param temporaryCard
	 */
	@Transactional(readOnly = false)
	public String  addTemporaryCard(TemporaryCardEntity temporaryCard) {
		String message="保存成功";
		String[] nos = temporaryCard.getNos().split(",");
		String[] idKey = temporaryCard.getIdKey().split(",");
		String[] personIds = temporaryCard.getPersonIds().split(",");
		List<TemporaryCardEntity> list = new ArrayList<TemporaryCardEntity>();
		try {
			for(int i = 0;i<nos.length;i++){
				//解析前台传来的nos和idKey
				if(!("".equals(nos[i]) || "null".equals(nos[i]) || nos[i] == null)){
					temporaryCard.setNo(nos[i]);
					//根据no查询foodid
					list = temporaryCardDao.getFoodIdByNo(temporaryCard);
					if(list !=null){
						//设置foorid,personid
						temporaryCard.setFoodId(list.get(0).getFoodId());
						temporaryCard.setPersonId(personIds[i]);
						temporaryCard.setIdKey(idKey[i]);
						//修改数据
						temporaryCardDao.addTemporaryCard(temporaryCard);
					}else{
						message ="保存失败";
					}
				}
			}
		} catch (Exception e) {
			message ="保存失败";
			e.printStackTrace();
		}
		return message;
		
	}
	
}