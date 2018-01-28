<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门禁刷卡记录</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/reportmanage/tDSwiperecord/">门禁刷卡记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tDSwiperecord" action="${ctx}/reportmanage/tDSwiperecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<li><label>职工姓名：</label>
			<input name="name" maxlength="100" class="input-medium" value="${tDSwiperecord.name}"/>
		</li> 
		<li><label>职工编号：</label>
			<input  name="no" maxlength="100" class="input-medium" value="${tDSwiperecord.no}"/>
		</li>
		<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tDSwiperecord.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${tDSwiperecord.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>	
			</li>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<td>职工姓名</td>
				<td>职工编号</td>
				<td>门禁名称</td>
				<td>刷卡时间</td>
				<%-- <shiro:hasPermission name="reportmanage:tDSwiperecord:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tDSwiperecord">
			<tr>
				<td>${tDSwiperecord.name }</td>
				<td>${tDSwiperecord.no }</td>
				<td>${tDSwiperecord.fDoorNames }</td>
				<td><fmt:formatDate value="${tDSwiperecord.fReaddate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<%-- <tr>
				<shiro:hasPermission name="reportmanage:tDSwiperecord:edit"><td>
    				<a href="${ctx}/reportmanage/tDSwiperecord/form?id=${tDSwiperecord.id}">修改</a>
					<a href="${ctx}/reportmanage/tDSwiperecord/delete?id=${tDSwiperecord.id}" onclick="return confirmx('确认要删除该门禁刷卡记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr> --%>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>