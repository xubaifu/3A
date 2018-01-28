<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人餐卡统计</title>
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
		<li class="active"><a href="${ctx}/reportmanage/reportManage/findPersonList">个人餐卡统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="reportManage" action="${ctx}/reportmanage/reportManage/findPersonList" method="post"  class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>	
			</li> --%>
			<li><label>职工姓名：</label>
				<form:input  path="name" maxlength="100" class="input-medium"/>
			</li> 
			<li><label>职工编号：</label>
				<form:input  path="no" maxlength="100" class="input-medium"/>
			</li>
			<li><label>部门名称：</label>
				<sys:treeselect id="office" name="officeId" value="${reportManage.officeId }" labelName="officeName" labelValue="${reportManage.officeName }"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="clearfix"></li>
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportManage.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${reportManage.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>	
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div>
		<h5>统计结果</h5>
		<div  style="margin-left: 4%">
			<br>
			<c:forEach items="${sum}" var="sum">
				<span>${sum.mealType }刷卡次数：</span><span>${sum.times}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>总消费金额：</span><span>${sum.allmoney}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>个人承担金额：</span><span>${sum.person}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span>部门承担金额：</span><span>${sum.subsidy}</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<br><br>
			</c:forEach>
			<!-- <span>早餐刷卡次数：</span><span>1234</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>午餐刷卡次数：</span><span>4321</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>晚餐刷卡次数：</span><span>1234</span><br><br>
			<span>其他餐别刷卡次数：</span><span>1234</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>总消费金额：</span><span>1234</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>个人承担金额：</span><span>1234</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<span>部门承担金额：</span><span>1234</span>&nbsp;&nbsp;&nbsp;&nbsp; -->
			
		</div>
	</div>
	<table id="" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>刷卡次数</th>
				<th>职工名称</th>
				<th>职工编号</th>
				<th>刷卡时间</th>
				<th>刷卡餐别</th>
				<th>本次消费</th>
				<th>个人承担</th>
				<th>本次补助</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="detail">
				<tr>
					<td>${detail.times}</td>
					<td>${detail.name}</td>
					<td>${detail.no}</td>
					<td><fmt:formatDate value="${detail.consumeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${detail.mealType}</td>
					<td>${detail.allmoney}</td>
					<td>${detail.person}</td>
					<td>${detail.subsidy}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</body>
</html>