<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考勤卡刷卡统计</title>
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
		<li class="active"><a href="${ctx}/reportmanage/reportManage/attendanceReport">考勤卡刷卡统计</a></li>
	</ul>
	<div id="searchForm" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>员工编号：</label>
				<input  maxlength="100" class="input-medium"/>
			</li>
			<li><label>一卡通编号：</label>
				<input  maxlength="100" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>	
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提交统计"/></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<div>
		<h5>统计结果</h5>
		<div  style="margin-left: 4%">
			<br>
			<span>统计报表导出：</span><input id="export" class="btn btn-primary" type="submit" value="导出"/>
		</div>
	</div>
	<br>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>员工编号</th>
				<th>一卡通编号</th>
				<th>考勤刷卡机器号</th>
				<th>考勤刷卡时间</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr>
			<tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr><tr>
				<td>345634</td>
				<td>4645645</td>
				<td>123456</td>
				<td>2017-01-03</td>
			</tr>
		</tbody>
	</table>
</body>
</html>