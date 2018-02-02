<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>一卡通统一授权管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			getAuthorizationList();
		});
		//获取一卡通授权列表
		var getAuthorizationList = function(){
			var userNo = $("#userNo").val();
			var cardNo = $("#cardNo").val();
			var userName = $("#userName").val();
			var officeId = $("#officeId").val();
			var cardStatus = $("#cardStatus").val();
			var pageNo = $("#pageNo").val();
			var pageSize = $("#pageSize").val();
			console.log(officeId);
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"cardStatus":cardStatus,"userNo":userNo,"cardNo":cardNo,"userName":userName,"officeId":officeId,"pageNo":pageNo,"pageSize":pageSize},
				url : "/3A/a/authorization/authorization/getAuthorizationList",
				async : true,
				success : function(data) {
					//console.log(data);
					$("#contentTableTbody").children().remove();
					$("#pageDiv").children().remove();
					var list = data[0].list;
					if(list == undefined){
						$("#pageDiv").append(data[0].html);
						return;
					}
					var html = '';
					var status = "";
					for(var i=0;i<list.length;i++){
						status = (list[i].cardStatus==0?"可用":"不可用");
						cardNo = list[i].cardNo;
						if(cardNo == "null" || cardNo == "undefined" || cardNo == null){
							cardNo = "";
						}
						html = html + '<tr>'+
										'<td>'+list[i].userNo+'</td>'+
										'<td>'+list[i].userName+'</td>'+
										'<td>'+list[i].officeName+'</td>'+
										'<td>'+cardNo+'</td>'+
										/* '<td>'+list[i].isOnjob+'</td>'+ */
										'<td>'+status+'</td>'+
										'<td>'+
						    				'<a href="${ctx}/authorization/authorization/form?id='+list[i].userId+'&userNo='+list[i].userNo+'">修改&nbsp;&nbsp;</a>'+
											/* '<a href="${ctx}/authorization/authorization/delete?id=" onclick="return confirmx(\'确认要注销吗？\', this.href)">注销</a>'+ */
										'</td>'+
									'</tr>';
						
					}
					$("#contentTableTbody").append(html);
					$("#pageDiv").append(data[0].html);
					$("#pageNo").val(data[0].pageNo);
					$("#pageSize").val(data[0].pageSize);
				},
				error : function(){
					alert("信息获取失败");
				}
			});
		};
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			getAuthorizationList();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/authorization/authorization/">一卡通统一授权列表</a></li>
		<%-- <shiro:hasPermission name="authorization:authorization:edit"><li><a href="${ctx}/authorization/authorization/form">一卡通授权编辑</a></li></shiro:hasPermission> --%>
	</ul>
	<div id="searchForm" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>职工编号：</label>
				<input id="userNo"  maxlength="100" class="input-medium"/>
			</li>
			<li><label>职工卡号：</label>
				<input id="cardNo"  maxlength="100" class="input-medium"/>
			</li>
		<!-- </ul>
		<ul class="ul-form"> -->
			<li><label>职工姓名：</label>
				<input id="userName"  maxlength="100" class="input-medium"/>
			</li>
			<li><label>部门名称：</label>
				<sys:treeselect id="office" name="office.id" value="${testData.office.id}" labelName="office.name" labelValue="${testData.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>卡状态：</label>
				<select id="cardStatus">
					<option value="">请选择状态</option>
					<option value="0">可用</option>
					<option value="1">不可用</option>
				</select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" value="查询" type="button" onclick="getAuthorizationList()"/></li>
			<li class="clearfix"></li>
		</ul>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>职工编号</th>
				<th>职工名称</th>
				<th>部门名称</th>
				<th>职工卡号</th>
				<!-- <th>在职</th> -->
				<th>卡状态</th>
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