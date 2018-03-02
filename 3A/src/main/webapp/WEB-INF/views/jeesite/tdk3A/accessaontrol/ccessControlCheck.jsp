<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门禁管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getOfficeDooor();
		});
		//获取部门对应的门禁信息
		var getOfficeDooor = function(){
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"pageNo": $("#pageNo").val(), "pageSize":$("#pageSize").val()},
				url : "/3A/a/accessaontrol/accessControl/getOfficeDooor",
				async : true,
				beforeSend:function(XMLHttpRequest){
					loading('正在获取门禁列表，请稍等...');
		         },
				success : function(data) {
					$("#contentTable tbody").children().remove();
					$("#pagination").children().remove();
					//console.log(data[0].list);
					var html = '';
					for(var i = 0;i<data[0].list.length;i++){
						//var name = data[0].list[i].name.split(",");
						//var name = data[0].list[i].name
						html = html + '<tr>'+
										'<td>'+data[0].list[i].name+'('+data[0].list[i].code+')</td>'+
										'<td>'+data[0].list[i].fDoorname+'</td>'+
										'<td>'+
						    				'<a href="${ctx}/accessaontrol/accessControl/ccessControlEdit" onclick="saveOffice(\''+data[0].list[i].id+'\',\''+data[0].list[i].name+'\')">修改</a>'+
											/* '<a href="#" onclick="return confirmx(\'确认要删除该门禁信息吗？\', this.href)">删除</a>'+ */
										'</td>'+
									'</tr>';
					}
					$("#contentTable tbody").append(html);
					$("#pagination").append(data[0].html);
				},
				error : function(){
					//alert("考勤授权失败");
					showTip("获取门禁列表失败!","",2000,500);
				},
				complete:function(XMLHttpRequest,textStatus){
					//关闭加载框
					closeLoading();
		         },
				
			});
		};
		function page(n,s){
			$("#pagination").children().remove();
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			getOfficeDooor();
        	return false;
        }
		//保存部门信息
		var saveOffice = function(id,name){
			sessionStorage.setItem("officeId", id);
			sessionStorage.setItem("officeName", name);
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/accessaontrol/accessControl/ccessControlCheck">门禁查看</a></li>
		<li><a href="${ctx}/accessaontrol/accessControl/ccessControlEdit">添加</a></li>
	</ul>
	<%-- <div><a href="${ctx}/accessaontrol/accessControl/ccessControlEdit">添加</a></div> --%>
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门名称</th>
				<th>控制器</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<%-- <c:forEach items="${page.list}" var="list" varStatus="st">
				<tr>
					<td>${list.name }</td>
					<td>${list.conId }</td>
					<td>
	    				<a href="${ctx}/accessaontrol/accessControl/ccessControlEdit">修改</a>
						<a href="#" onclick="return confirmx('确认要删除该门禁信息吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach> --%>
		</tbody>
	</table>
	<!-- 分页 -->
	<!-- <div class="pagination"></div> -->
	<div class="pagination" id = "pagination"> </div>
	<div style="clear: both;"></div>
</body>
</html>