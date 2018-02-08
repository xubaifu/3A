<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>一卡通统一授权管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="/3A/static/3Aselect/select.js"></script>
	<link rel="stylesheet" href="/3A/static/3Aselect/select.css"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//重置开始和结束时间
			$("#startTime").val(getNowFormatDate());
			$("#endTime").val("2099-12-31 00:00:00");
		});
		/* //复选框全选全不选
		var selectFun = {
				selectAllAttendance : function () {
					  $("#attendanceSelect :checkbox").prop("checked", true);  
				},
				unSelectAttendance : function () {  
					   $("#attendanceSelect :checkbox").prop("checked", false);  
				},
				selectAllMealCard : function () {
					  $("#mealCardSelect :checkbox").prop("checked", true);  
				},
				unSelectMealCard : function () {  
					   $("#mealCardSelect :checkbox").prop("checked", false);  
				}
		}; */
		//添加一行门禁授权
		var addFun = function(){
			var html = '<tr class="forRemove">'+
							'<td><select id="" class="input-medium">'+
								'<c:forEach items="${listDoor}" var="door">'+
								'<option value ="${door.fControllerno};${door.fControllerid};${door.fDoorid};${door.fDoorno};${door.fIp};${door.fControllersn}">${door.fDoorname}</option>'+
								'</c:forEach>'+
							'</select></td>'+
							'<td><a href="javascript:void(0)" onclick="removeFun(this)">删除</a>'+
						'</select></td>'+
						'</tr>';
			$("#tableTbody").append(html);
			$("#111").select2();
			$("#222").select2();
		};
		//删除添加的门禁授权
		var removeFun = function(obj){
			if(confirm("确认删除吗")){
				$(obj).parent().parent().remove();
			}
		};
		//重置
		var reSetFun = function(){
			/* selectFun.unSelectAttendance();
			selectFun.unSelectMealCard(); */
			$("#searchForm input").val("");
			$(".forRemove").remove();
		};
		
		//考勤授权
		var addAttendanceFun = function(){
			var userNo = $("#userNo").val();
			if($.trim(userNo) == null || $.trim(userNo) == ""){
				alert("员工卡不能为空");
				return;
			}
			var cardNo = $("#cardNo").val();
			if($.trim(cardNo) == null || $.trim(cardNo) == ""){
				alert("卡号不能为空");
				return;
			}
			var userName = $("#userName").val();
			if($.trim(userName) == null || $.trim(userName) == ""){
				alert("姓名不能为空");
				return;
			}
			var officeId = $("#officeId").val();
			var reason = $("#reason").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var kqId = $("#kqId").val();
			
			var typeNo = $("#typeNo").val();
			if(typeNo == ""){
				alert("餐卡类型不能为空");
				return;
			}
			//return;
			//将门禁控制器的信息存入list，后台解析使用
			/* var list=[];
			$("#tableTbody input").each(function(index,element){
				list[index] = $(this).val();
			});
			var listLength = list.length;
			$("#tableTbody select").each(function(index,element){
				list[index+listLength] = $(this).val();
			});
			var doorMessage = JSON.stringify(list).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, ''); */
			
			
			
			var doorList=[];//存放门禁控制器信息
			
			$("#selectDoor div").each(function(index,element){
				doorList[index] = $(this).attr("data-value");
			});
			/* console.log(JSON.stringify(doorList).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, '')); */
			var doorMessage = JSON.stringify(doorList).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, '');
			
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {
					"kqId":$.trim(kqId),
					"userNo":$.trim(userNo),
					"cardNo":$.trim(cardNo),
					"userName":$.trim(userName),
					"officeId":officeId,
					"reason":$.trim(reason),
					"startTime":startTime,
					"endTime":endTime,
					"doorId" :$("#doorId").val(),
					"oldCardNo" :$("#oldCardNo").val(),
					"userId" :$("#userId").val(),
					"typeNo" : typeNo,
					"foodId" : $("#foodId").val(),
					/* "fControllerno":$("#fControllerno1").val(),
					"fControllerid":$("#fControllerid1").val(),
					"fDoorid":$("#fDoorid1").val(),
					"fDoorno":$("#fDoorno1").val(),
					"fControllersn":$("#fControllersn1").val(),
					"fIp":$("#fIp1").val(), */
					"doorMessage" : doorMessage
					//"doorArr" : list
					},
				url : "/3A/a/authorization/authorization/addAttendanceFun",
				async : true,
				beforeSend:function(XMLHttpRequest){
					$("#btnSubmit").attr("onclick","");
					$("#btnReset").attr("onclick","");
					loading('正在提交，请稍等...');
		         },
				success : function(data) {
					console.log(data);
					if(data[0]==1){
						//alert("授权成功!");
						showTip("授权成功!","",2000,500);
						$("#searchForm input").attr("readonly","readonly");
						$("#btnSubmit").hide();
						$("#btnReset").hide();
						$("#goBack").show();
						
					}
				},
				error : function(){
					//alert("考勤授权失败");
					showTip("授权失败!","",2000,500);
				},
				complete:function(XMLHttpRequest,textStatus){
					//关闭加载框
					closeLoading();
		         },
				
			});
		};
		
		
		
		//获取当前时间
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/authorization/authorization/">一卡通统一授权列表</a></li>
		<shiro:hasPermission name="authorization:authorization:edit"><li class="active"><a href="#">一卡通授权编辑</a></li></shiro:hasPermission>
	</ul>
	<div id="searchForm" class="breadcrumb form-search">
	
		<!-- <input id="pageNo" name="pageNo" type="hidden" value=""/>
		<input id="pageSize" name="pageSize" type="hidden" value=""/> -->
		<ul class="ul-form" style="height: 90px">
			<li>
				<input id="kqId" name="kqId" value="${list[0].kqId}" type="hidden" readonly="readonly">
				<input id="doorId" name="doorId" value="${list[0].doorId}" type="hidden" readonly="readonly">
				<input id="userId" name="userId" value="${list[0].userId}" type="hidden" readonly="readonly">
				<input id="foodId" name="foodId" value="${list[0].foodId}" type="hidden" readonly="readonly">
			</li>
			<li><label>职工编号：</label>
				<input id="userNo" name="userNo" maxlength="100" class="input-medium" value="${list[0].userNo}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
			</li>
			<li><label>职工姓名：</label>
				<input id="userName" name="userName" maxlength="100" class="input-medium" value="${list[0].userName}"/>
			</li>
			<li><label>部门名称：</label>
				<sys:treeselect id="office" name="officeId" value="${list[0].officeId}" labelName="office.name" labelValue="${list[0].officeName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<!-- <li><label>岗位：</label>
				<input  maxlength="100" class="input-medium"/>
			</li> -->
			<li><label>职工卡号：</label>
				<input id="cardNo" name="cardNo" maxlength="100" class="input-medium" value="${list[0].cardNo}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
				<input id="oldCardNo" value="${list[0].cardNo}" type="hidden"/>
			</li>
			<!-- <li>
				<label>发卡原因：</label>
				<select id="reason" name="reason" class="input-medium">
					<option value ="">--请选择--</option>
					<option value ="新入职">新入职</option>
					<option value="离职">离职</option>
					<option value="卡丢失，领取新卡">卡丢失，领取新卡</option>
					<option value ="卡损坏，领取新卡">卡损坏，领取新卡</option>
					<option value="临时卡">临时卡</option>
					<option value="其他原因">其他原因</option>
				</select>
			</li> -->
			<li><label>开始时间：</label>
				<input id="startTime" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${list[0].startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
			</li>
			<li><label>结束时间：</label>
				<input id="endTime" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${list[0].endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
					
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</div>
	<!-- 门禁授权 -->
	<div>
		<%-- <h5>门禁授权</h5><br>
		<div style="margin-left: 10px">
				<a href="javascript:addFun()">添加</a>&nbsp;&nbsp;&nbsp;
			</div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>控制器</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tableTbody">
				<c:forEach items="${listUserDoor}" var="door" varStatus="st">
					<c:if test="${not empty door.fDoorname}">
						<tr class="forRemove">
							<td style="display: none;"><input id="door${st.count}" value="${door.fControllerno};${door.fControllerid};${door.fDoorid};${door.fDoorno};${door.fIp};${door.fControllersn}"></td>
							<td>
								${door.fDoorname}
							</td>
							<td><a href="javascript:void(0)" onclick="removeFun(this)">删除</a></td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table> --%>
		
		
		
		
		
		
		
		
		
		
		
		<h5>门禁授权</h5><br>
		<div id="two_way_list_selector_a" class="two_way_list_selector margin_top_10">
			<div class="select_l">
				<div class="option">
					<div class="l">可选门禁</div>
					<!-- <div class="r">
						<a>数量</a>
					</div> -->
				</div>
				<div class="select_a" id="doorList">
					<c:forEach items="${listDoor}" var="door" varStatus="st">
						<c:if test="${not empty door.fDoorname}">
							<div id="door${st.count}" data-value="${door.fControllerno};${door.fControllerid};${door.fDoorid};${door.fDoorno};${door.fIp};${door.fControllersn}" data-index="5" class="item">${door.fDoorname}</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="select_btn">
				<div>
					<input type="button" value="--&gt;" class="button btn_a"> 
					<input type="button" value="--&gt;&gt;" class="button btn_add_all"> 
					<input type="button" value="&lt;&lt;--" class="button btn_remove_all"> 
					<input type="button" value="&lt;--" class="button btn_b">
				</div>
			</div>
			<div class="select_r" id="selectedUser">
				<div class="option">
					<div class="r" style="border-bottom: 1px solid #ddd;">已选门禁</div>
				</div>
				<div class="select_b" id="selectDoor">
					<c:forEach items="${listUserDoor}" var="door" varStatus="st">
						<c:if test="${not empty door.fDoorname}">
							<div id="door${st.count}" data-value="${door.fControllerno};${door.fControllerid};${door.fDoorid};${door.fDoorno};${door.fIp};${door.fControllersn}" data-index="5" class="item">${door.fDoorname}</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		
		
		
		
		
		
		
		
		
		
		
	</div>
	<!-- 考勤授权 -->
	<div style="display: none;">
		<h5>考勤授权</h5><br>
		<div id="attendanceSelect">
			<div style="margin-left: 100px">
				<span>考勤机</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- <a href="javascript:selectFun.selectAllAttendance()" id="selectAll" >全选</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:selectFun.unSelectAttendance()" id="unSelect" >全不选</a> -->
			</div>
			<br>
			<c:forEach items="${listAttendance}" var="attendance" varStatus="st">
				<input style="margin-left: 120px" type="checkbox" value="${attendance.bh}" checked="checked" disabled="disabled"/>${attendance.mc}
				<c:if test="${st.count%5 == 0}"><br><br></c:if>
			</c:forEach>
			
		</div>
	</div>
	<!-- 餐卡授权 -->
	<div><br>
		<h5>餐卡授权</h5><br>
		<div id="mealCardSelect">
		<div  class="breadcrumb form-search">
			<ul class="ul-form">
				<li>
					<label>餐卡类型：</label>
					<select id="typeNo" name="typeNo" class="input-medium">
							<!-- <option value ="">--请选择--</option> -->
							<option value ="2">员工卡</option>
							<option value ="18">客餐卡</option>
					</select>
				</li>
			</ul>
		</div>
			<!-- <div style="margin-left: 100px">
				<span>餐卡机</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:selectFun.selectAllMealCard()">全选</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:selectFun.unSelectMealCard()">全不选</a>
			</div>
			<br>
			<input style="margin-left: 100px" name="" type="checkbox" value="" />1号机
			<input style="margin-left: 100px" name="" type="checkbox" value="" />2号机
			<input style="margin-left: 100px" name="" type="checkbox" value="" />3号机
			<input style="margin-left: 100px" name="" type="checkbox" value="" />4号机
			<input style="margin-left: 100px" name="" type="checkbox" value="" />5号机
			<br><br>
			<input style="margin-left: 100px" name="" type="checkbox" value="" />6号机
			<input style="margin-left: 100px" name="" type="checkbox" value="" />7号机
			<input style="margin-left: 100px" name="" type="checkbox" value="" />8号机 -->
			
		</div>
	</div>
	<div style="margin-left: 33%">
		<br><br>
		<input id="btnReset" class="btn btn-primary" type="button" value="取消，重置" onclick="reSetFun()"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="button" value="授权，提交" onclick="addAttendanceFun()"/>
		<a id="goBack" class="btn btn-primary" href="${ctx}/authorization/authorization/" style="display: none;">返回</a>
	</div>
	
	<script>
	//去除重复门禁
	for(var i = 0; i < $("#doorList div").length; i++){
		console.log($("#doorList div:eq("+i+")"));
		for(var j = 0; j < $("#selectDoor div").length; j++){
			console.log($("#selectDoor div:eq("+j+")"));
			if($("#doorList div:eq("+i+")").attr("data-value") == $("#selectDoor div:eq("+j+")").attr("data-value")){
				$("#doorList div:eq("+i+")").hide();
			}
		}
	}
	</script>
</body>
</html>