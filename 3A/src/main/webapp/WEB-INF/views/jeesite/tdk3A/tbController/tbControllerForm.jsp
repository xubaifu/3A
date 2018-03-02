<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门禁控制管理</title>
	<meta name="decorator" content="default"/>
	<!-- <script type="text/javascript" src="/3A/static/3Aselect/select.js"></script>
	<link rel="stylesheet" href="/3A/static/3Aselect/select.css"/> -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					fControllerid: {required:true},
					fControllerno: {required:true},
					fNote: {required:true},
					fControllersn: {required:true},
					fIp: {required:true},
					fPort: {required:true},
				},
				messages: {
					fControllerid: {required:"不能为空"},
					fControllerno: {required:"不能为空"},
					fNote: {required: "不能为空"},
					fControllersn: {required: "不能为空"},
					fIp: {required: "不能为空"},
					fPort: {required: "不能为空"},
				},
				submitHandler: function(form){
					console.log("gggggg")
					var boo = true;
					if($("#trpe").val() == "add"){
						$.ajax({
							type : "POST",
							dataType : "json",
							data : {"fControllerid":$("#fControllerid").val(),"fControllerno":$("#fControllerno").val()},
							url : "/3A/a/dbcontroller/manage/getControllerById",
							async : false,
							success : function(data) {
								//console.log(data);
								if(data.length > 0){
									boo = false;
									alert("控制器已存在");
								}
							}
						});
					}/* else{
						if(boo){
							//loading('正在提交，请稍等...');
							form.submit();
						}
					} */
					if(boo){
						//loading('正在提交，请稍等...');
						form.submit();
					}
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
		});
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dbcontroller/manage/">门禁控制器列表</a></li>
		<shiro:hasPermission name="dbcontroller:manage:edit"><li class="active"><a href="#">控制器编辑</a></li></shiro:hasPermission>
	</ul>
	<form:form id="inputForm" modelAttribute="tbController" action="${ctx}/dbcontroller/manage/save" method="post" class="form-horizontal">
		<%-- <form:hidden path="id"/> --%>
		<sys:message content="${message}"/>
		<form:hidden id="trpe" path="type"/>
		<div class="control-group">
			<label class="control-label">控制器名称:</label>
			<div class="controls">
				<form:input path="fNote" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">控制器ID:</label>
			<div class="controls">
				<form:input path="fControllerid" htmlEscape="false" maxlength="50" class="required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">控制器编号:</label>
			<div class="controls">
				<form:input path="fControllerno" htmlEscape="false" maxlength="50" class="required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">控制器SN:</label>
			<div class="controls">
				<form:input path="fControllersn" htmlEscape="false" maxlength="50" class="required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">控制器IP:</label>
			<div class="controls">
				<form:input path="fIp" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">控制器端口:</label>
			<div class="controls">
				<form:input path="fPort" htmlEscape="false" maxlength="50" class="required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">控制器区域:</label>
			<div class="controls">
				<form:select path="fZoneid">
					<form:options items="${fns:getDictList('entrance_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		
		<div class="form-actions">
			<shiro:hasPermission name="sys:user:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
<script>
	if($("#trpe").val() == "update"){
		$("#fControllerid").attr("readonly","readonly");
		$("#fControllerno").attr("readonly","readonly");
	}
</script>
</body>
</html>