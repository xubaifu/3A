/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.tdk3A.accessaontrol.entity.AccessControlEntity;

/**
 * 角色DAO接口
 * @author ThinkGem
 * @version 2013-12-05
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);
	
	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);
	
	//获取某一区域下的所有门禁控制器
	List<AccessControlEntity> getAllDoorByArea(Role role);
	//获取某一角色下的某一区域下的已有的门禁控制器
	List<AccessControlEntity> getAllDoorAreadySelect(Role role);
	//新增角色门禁对应信息
	void insertRoleDoor(Role role);
	//删除角色门禁对应信息
	void deleteRoleDoor(Role role);
	
	//获取所有二级部门
	List<Office> getAllOffice(Role role);
	//获取某一角色下的所有二级部门
	List<Office> getAllOfficeAreadySelect(Role role);
	//新增角色二级部门对应信息
	void insertRoleOffice2(Role role);
	//删除角色二级部门对应信息
	void deleteRoleOffice2(Role role);
}
