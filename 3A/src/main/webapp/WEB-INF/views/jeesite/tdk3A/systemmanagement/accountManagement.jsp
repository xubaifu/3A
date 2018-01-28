<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统账号管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/systemmanagement/systemManagement/accountManagement">系统账号管理</a></li>
	</ul>
	<%-- <div id="searchForm" modelAttribute="systemManagement" action="${ctx}/systemmanagement/systemManagement/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</div> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录账号</th>
				<th>登录密码</th>
				<th>员工编号</th>
				<th>姓名</th>
				<th>部门</th>
				<th>账号角色</th>
				<th>创建日期</th>
				<th>最近登录</th>
				<th>登录IP</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr>
			<tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr><tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr><tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr><tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr><tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr><tr>
				<td>baifu</td>
				<td>********</td>
				<td>12345678</td>
				<td>百富</td>
				<td>技术部</td>
				<td>系统管理员</td>
				<td>2017-02-10</td>
				<td>2017-02-10</td>
				<td>10.3.4.5</td>
				<td>
    				<a href="${ctx}/systemmanagement/systemManagement/addAccountForm">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该系统管理吗？', this.href)">删除</a>
				</td>
			</tr>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>