<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>临时卡记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		
	});
	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
       	return false;
       }
	function addTemporaryCard(){
		var nos = "",idKey = "",personIds = "";
		$("#contentTable tbody input").each(function(i,obj){
			if($(obj).val() != ""){
				nos = $(obj).val() + "," + nos;
				idKey = $(obj).attr("id") + "," + idKey;
				personIds = $(obj).attr("class") + "," + personIds;
			}
		});
		$("#nos").val(nos);
		$("#idKey").val(idKey);
		$("#personIds").val(personIds);
		$("#addTemporaryCard").submit();
		 /* $.ajax({
			url : "temporaryCard/addTemporaryCard",
			data : {"nos":nos,"idKey":idKey,"personIds":personIds},
			type : "post",
			async : true,
			dataType : "json",
			success : function(data) {
				
			},
			error : function(){
				alert("保存失败！");
			}
		});  */
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/temporarycard/temporaryCard">临时卡记录管理</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="temporaryCardEntity" action="${ctx}/temporarycard/temporaryCard" method="post"  class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo }"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>临时卡号：</label>
				<form:input path="cardno"  maxlength="100" class="input-medium"/>
			</li>
			<li><label>刷卡时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${temporaryCardEntity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -- 
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${temporaryCardEntity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>	
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<a href="javascript:addTemporaryCard()">&nbsp;保&nbsp;存&nbsp;</a>
	<br><br>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>临时卡号</th>
				<th>刷卡时间</th>
				<th>持卡人编号</th>
			</tr>
		</thead>
		<tbody>
			<form:form id="addTemporaryCard" modelAttribute="temporaryCardEntity" action="${ctx}/temporarycard/temporaryCard/addTemporaryCard" method="post"  class="breadcrumb form-search">
			<input id="nos" name="nos" type="hidden" value=""/>
			<input id="idKey" name="idKey" type="hidden" value=""/>
			<input id="personIds" name="personIds" type="hidden" value=""/>
			<c:forEach items="${page.list }" var="list">
				<tr>
					<td>${list.cardno}</td>
					<td><fmt:formatDate value="${list.consumeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<c:if test="${list.remark!='' and list.remark!=null}"><td>${list.remark}</td></c:if>
					<c:if test="${list.remark =='' or list.remark==null}">
					<td><input id="${list.idKey}" class="${list.personId}" value=""></td>
					</c:if>
				</tr>
			</c:forEach>
			</form:form>
		</tbody>
	</table>
	<!-- 分页 -->
	<div class="pagination">${page}</div>

</body>
</html>