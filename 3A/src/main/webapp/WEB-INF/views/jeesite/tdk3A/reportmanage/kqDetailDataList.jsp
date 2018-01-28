<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤刷卡统计</title>
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
		<li class="active"><a href="#">考勤刷卡统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="kqOriginalityData" action="${ctx}/systemmanagement/kqOriginalityData" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!-- <li><label>职工姓名：</label>
				<input  maxlength="100" class="input-medium"/>
			</li> -->
			<li><label>职工卡号：</label>
				<form:input path="cardNo"  maxlength="100" class="input-medium"/>
			</li>
			<li><label>职工编号：</label>
				<form:input  path="user.no" maxlength="100" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${kqOriginalityData.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${kqOriginalityData.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>	
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>职工姓名</th>
				<th>职工卡号</th>
				<th>职工编号</th>
				<!-- <th>考勤机器</th> -->
				<th>考勤位置</th>
				<th>刷卡时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="kqData">
			<tr>
				<td>${kqData.username }</td>
				<td>${kqData.cardNo }</td>
				<td>${kqData.user.no }</td>
				<%-- <td>${kqData.machineNo }</td> --%>
				<td>${kqData.location }</td>
				<td>${kqData.workDate}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>