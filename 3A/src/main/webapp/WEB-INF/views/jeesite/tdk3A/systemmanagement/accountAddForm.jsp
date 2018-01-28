<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>系统账号管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		/* $(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		}); */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/systemmanagement/systemManagement/accountManagement">系统账号列表</a></li>
		<li class="active"><a href="#">账号管理</a></li>
	</ul><br/>
	<div id="searchForm" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/>
		<ul class="ul-form">
			<li><label>登录账号：</label>
				<input  maxlength="100" class="input-medium" readonly="readonly" value="baifu"/>
			</li>
			<li><label>员工编号：</label>
				<input  maxlength="100" class="input-medium"/>
			</li>
			<li class="clearfix"></li>
			<li><label>姓名：</label>
				<input  maxlength="100" class="input-medium"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${testData.office.id}" labelName="office.name" labelValue="${testData.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="clearfix"></li>
			<li><label>账号角色：</label>
				<input  maxlength="100" class="input-medium"/>
			</li>
			
			<li class="clearfix"></li>
		</ul>
		<div style="margin-left: 33%">
			<br><br>
			<input id="btnSubmit" class="btn btn-primary" type="button" value="取消"/>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="btnReset" class="btn btn-primary" type="button" value="提交"/>
		</div>
	</div>
</body>
</html>