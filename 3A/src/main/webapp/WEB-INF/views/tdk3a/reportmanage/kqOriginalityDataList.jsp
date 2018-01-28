<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门禁刷卡记录管理</title>
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
		<li class="active"><a href="${ctx}/reportmanage/kqOriginalityData/">门禁刷卡记录列表</a></li>
		<shiro:hasPermission name="reportmanage:kqOriginalityData:edit"><li><a href="${ctx}/reportmanage/kqOriginalityData/form">门禁刷卡记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="kqOriginalityData" action="${ctx}/reportmanage/kqOriginalityData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="reportmanage:kqOriginalityData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="kqOriginalityData">
			<tr>
				<shiro:hasPermission name="reportmanage:kqOriginalityData:edit"><td>
    				<a href="${ctx}/reportmanage/kqOriginalityData/form?id=${kqOriginalityData.id}">修改</a>
					<a href="${ctx}/reportmanage/kqOriginalityData/delete?id=${kqOriginalityData.id}" onclick="return confirmx('确认要删除该门禁刷卡记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>