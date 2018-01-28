<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<!-- <script type="text/javascript" src="/static/jquery/jquery-1.9.1.min.js"></script> -->
	<script type="text/javascript">
	$(document).ready(function() {
		getUsers();
	});
	
	function getUsers(){
		$("#contentTable tbody").children().remove();
		$.ajax({
			type : "POST",
			dataType : "json",
			data : {"name":$("#name").val()},
			url : "testPage/finduser",
			async : true,
			success : function(data) {
				console.log(data);
				var html = "";
				for(var i=0;i<data.length;i++){
					console.log(data[i].name);
					html = html + '<tr><td>'+data[i].name+'</td>'+
									'<td>'+data[i].loginName+'</td><td><a href="${ctx}/test/testPage/list">修改</a><a href="#">删除</a></td>'+
									'</tr>';
				}
				$("#contentTable tbody").append(html);
			},
			error : function(){
				alert(0);
			}
		});
	}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/testPage/list">查询用户</a></li>
		<shiro:hasPermission name="test:testPage:edit"><li><a href="${ctx}/test/testPage/form">修改用户</a></li></shiro:hasPermission>
	</ul>
	<div id="searchForm" class="breadcrumb form-search">
		<ul class="ul-form">
			
			<li><label>名称：</label>
				<input id="name" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" onclick="getUsers()" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>登录名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</body>
</html>