<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典表</title>
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
		<li class="active"><a href="${ctx}/accessaontrol/accessControl/dictionaryTable">字典表</a></li>
	</ul>
	<div id="searchForm" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>临时卡号：</label>
				<input maxlength="100" class="input-medium"/>
			</li>
			<li><label>刷卡时间：</label>
				<input type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 至
				<input type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<div><a href="#">保存</a></div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>临时卡号</th>
				<th>刷卡时间</th>
				<th>持卡人员工号</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1234567</td>
				<td>2016-09-09 12:30:08</td>
				<td><input maxlength="100" class="input-medium"/></td>
			</tr>
			<tr>
				<td>1234567</td>
				<td>2016-09-09 12:30:08</td>
				<td><input maxlength="100" class="input-medium"/></td>
			</tr>
			<tr>
				<td>1234567</td>
				<td>2016-09-09 12:30:08</td>
				<td><input maxlength="100" class="input-medium"/></td>
			</tr>
			<tr>
				<td>1234567</td>
				<td>2016-09-09 12:30:08</td>
				<td><input maxlength="100" class="input-medium"/></td>
			</tr>
			<tr>
				<td>1234567</td>
				<td>2016-09-09 12:30:08</td>
				<td><input maxlength="100" class="input-medium"/></td>
			</tr>
			<tr>
				<td>1234567</td>
				<td>2016-09-09 12:30:08</td>
				<td><input maxlength="100" class="input-medium"/></td>
			</tr>
		</tbody>
	</table>
	<!-- 分页 -->
	<!-- <div class="pagination"></div> -->
	<div class="pagination">
		<ul>
			<li><a href="javascript:" onclick="page(1,10,'');">« 上一页</a></li>
			<li><a href="javascript:" onclick="page(1,10,'');">1</a></li>
			<li class="active"><a href="javascript:">2</a></li>
			<li class="disabled"><a href="javascript:">下一页 »</a></li>
			<li class="disabled controls"><a href="javascript:">当前 <input
					type="text" value="2"
					onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,'');"
					onclick="this.select();"> / <input type="text" value="10"
					onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(2,this.value,'');"
					onclick="this.select();"> 条，共 15 条
			</a></li>
		</ul>
		<div style="clear: both;"></div>
	</div>
</body>
</html>