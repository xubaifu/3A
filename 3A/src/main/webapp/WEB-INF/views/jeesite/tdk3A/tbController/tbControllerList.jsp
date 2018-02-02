<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门禁控制器管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getTbControllerList();
		});
		var getTbControllerList = function(){ 
			var fControllerid = $("#fControllerid").val();
			var fControllerno = $("#fControllerno").val();
			var fNote = $("#fNote").val();
			var pageNo = $("#pageNo").val();
			var pageSize = $("#pageSize").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"fControllerid":fControllerid,"fControllerno":fControllerno,"fNote":fNote,"pageNo":pageNo,"pageSize":pageSize},
				url : "/3A/a/dbcontroller/manage/getTbController",
				async : true,
				success : function(data) {
					$("#contentTableTbody").children().remove();
					$("#pageDiv").children().remove();
					var list = data.list;
					var html = '';
					for(var i=0;i<list.length;i++){
						html = html + '<tr>'+
										'<td>'+list[i].fControllerid+'</td>'+
										'<td>'+list[i].fControllerno+'</td>'+
										'<td>'+list[i].fNote+'</td>'+
										/* '<td>'+list[i].isOnjob+'</td>'+ */
										'<td>'+list[i].fIp+'</td>'+
										'<td>'+
						    				'<a href="${ctx}/dbcontroller/manage/form?fControllerid='+list[i].fControllerid+'">修改&nbsp;&nbsp;</a>'+
											'<a href="${ctx}/dbcontroller/manage/delete?fControllerid='+list[i].fControllerid+'" onclick="return confirmx(\'确认要删除吗？\', this.href)">删除</a>'+
										'</td>'+
									'</tr>';
						
					}
					$("#contentTableTbody").append(html);
					$("#pageDiv").append(data.html);
					$("#pageNo").val(data.pageNo);
					$("#pageSize").val(data.pageSize);
				},
				error : function(){
					alert("信息获取失败");
				}
			});
		};
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			getTbControllerList();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dbcontroller/manage/">门禁控制器列表</a></li>
		<shiro:hasPermission name="dbcontroller:manage:edit"><li><a href="${ctx}/dbcontroller/manage/form">添加</a></li></shiro:hasPermission>
		<%-- <shiro:hasPermission name="authorization:authorization:edit"><li><a href="${ctx}/authorization/authorization/form">一卡通授权编辑</a></li></shiro:hasPermission> --%>
	</ul>
	<div id="searchForm" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>控制器ID：</label>
				<input id="fControllerid"  maxlength="100" class="input-medium"/>
			</li>
			<li><label>控制器编号：</label>
				<input id="fControllerno"  maxlength="100" class="input-medium"/>
			</li>
			<li><label>控制器名称：</label>
				<input id="fNote"  maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" value="查询" type="button" onclick="getTbControllerList()"/></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>控制器ID</th>
				<th>控制器编号</th>
				<th>控制器名称</th>
				<th>控制器IP</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="contentTableTbody">
			
		</tbody>
	</table>
	<!-- 分页 -->
	<div class="pagination" id="pageDiv"></div>
</body>
</html>