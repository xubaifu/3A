<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据同步管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#startTime1").val(getNowFormatDate());
		$("#startTime2").val(getNowFormatDate());
		$("#startTime3").val(getNowFormatDate());
		$("#startTime4").val(getNowFormatDate());
		$("#startTime5").val(getNowFormatDate());
		/* $("#startTime6").val(getNowFormatDate());
		$("#startTime7").val(getNowFormatDate()); */
	});
	//人员同步
	var startOend = {
		startUser : function(type){
			var startTime = $("#startTime1").val();
			var timeLag = $("#timeLag1").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "startUser",
				async : true,
				success : function(data) {
					console.log(data);
					
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		//组织同步
		startOrganization : function(type){
			var startTime = $("#startTime2").val();
			var timeLag = $("#timeLag2").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "startOrganization",
				async : true,
				success : function(data) {
					console.log(data);
					
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		//门禁器同步
		startController : function(type){
			var startTime = $("#startTime").val();
			var timeLag = $("#timeLag").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "startController",
				async : true,
				success : function(data) {
					console.log(data);
					if(data[0] == 1){
						console.log(data);
					}
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		//考勤机同步
		startAttendance : function(type){
			var startTime = $("#startTime3").val();
			var timeLag = $("#timeLag3").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "startAttendance",
				async : true,
				success : function(data) {
					console.log(data);
					
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		//餐卡数据同步
		startMealCard : function(type){
			var startTime = $("#startTime4").val();
			var timeLag = $("#timeLag4").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "startMealCard",
				async : true,
				success : function(data) {
					console.log(data);
					if(data[0] == 1){
						console.log(data);
					}
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		//考勤记录同步
		startKqDetail : function(type){
			var startTime = $("#startTime5").val();
			var timeLag = $("#timeLag5").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "startKqDetail",
				async : true,
				success : function(data) {
					console.log(data);
					if(data[0] == 1){
						console.log(data);
					}
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		//门禁刷卡记录同步
		startTDSwiperecord : function(type){
			var startTime = $("#startTime").val();
			var timeLag = $("#timeLag").val();
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"startTime":startTime,"timeLag":timeLag,"type":type},
				url : "/3A/a/reportmanage/tDSwiperecord/startTDSwiperecord",
				async : true,
				success : function(data) {
					console.log(data);
					if(data[0] == 1){
						console.log(data);
					}
				},
				error : function(){
					alert("数据同步失败！");
				}
			});
		},
		end : {
			
		}
	};
	//获取当前时间
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate() + 1;
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " 00:00:00";
	            
	    return currentdate;
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/systemmanagement/systemManagement/dataManagement">数据同步管理</a></li>
	</ul>
	<div>
		<h5>数据信息同步（HR员工基本信息、考勤数据、餐卡消费数据等同步频率可配置）</h5><br><br><br>
		<span>（供参考）员工基本信息eHR人事系统同步到3A管理系统：</span>
		<div id="searchForm" class="breadcrumb form-search">
			<h5>人员同步</h5>
			<!-- <input id="pageNo" name="pageNo" type="hidden" value=""/>
			<input id="pageSize" name="pageSize" type="hidden" value=""/> -->
			<ul class="ul-form" style="height: 70px">
				<li><label>开始时间：</label>
					<input id="startTime1" type="text" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
						
				</li>
				<li><label>时间间隔：</label>
					<input id="timeLag1"  maxlength="100" class="digits valid" value="24"/>(小时)
					<!-- <label for="digits" class="error">只能输入整数</label> -->
				</li>
				<li><label>启动状态：</label>
				<!-- 取消与门禁系统的交互，不再同步门禁系统数据 -->
					<input id="btnStart" onclick="startOend.startUser('START');" class="btn btn-primary" type="button" value="启动"/>
					<input id="btnEnd" onclick="startOend.startUser('STOP');" class="btn btn-primary" type="button" value="停止"/>
				</li>
				<!-- <li class="clearfix"></li> -->
			</ul>
		</div>
		
		<div id="searchForm" class="breadcrumb form-search">
			<h5>组织同步</h5>
			<!-- <input id="pageNo" name="pageNo" type="hidden" value=""/>
			<input id="pageSize" name="pageSize" type="hidden" value=""/> -->
			<ul class="ul-form" style="height: 70px">
				<li><label>开始时间：</label>
					<input id="startTime2" type="text" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
						
				</li>
				<li><label>时间间隔：</label>
					<input id="timeLag2"  maxlength="100" class="digits valid" value="24"/>(小时)
					<!-- <label for="digits" class="error">只能输入整数</label> -->
				</li>
				<li><label>启动状态：</label>
				<!-- 取消与门禁系统的交互，不再同步门禁系统数据 -->
					<input id="btnStart" onclick="startOend.startOrganization('START');" class="btn btn-primary" type="button" value="启动"/>
					<input id="btnEnd" onclick="startOend.startOrganization('STOP');" class="btn btn-primary" type="button" value="停止"/>
				</li>
				<!-- <li class="clearfix"></li> -->
			</ul>
		</div>
		
		<div id="searchForm" class="breadcrumb form-search">
			<h5>考勤同步</h5>
			<!-- <input id="pageNo" name="pageNo" type="hidden" value=""/>
			<input id="pageSize" name="pageSize" type="hidden" value=""/> -->
			<ul class="ul-form" style="height: 70px">
				<li><label>开始时间：</label>
					<input id="startTime3" type="text" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
						
				</li>
				<li><label>时间间隔：</label>
					<input id="timeLag3"  maxlength="100" class="digits valid" value="24"/>(小时)
					<!-- <label for="digits" class="error">只能输入整数</label> -->
				</li>
				<li><label>启动状态：</label>
				<!-- 取消与门禁系统的交互，不再同步门禁系统数据 -->
					<input id="btnStart" onclick="startOend.startAttendance('START');" class="btn btn-primary" type="button" value="启动"/>
					<input id="btnEnd" onclick="startOend.startAttendance('STOP');" class="btn btn-primary" type="button" value="停止"/>
				</li>
				<!-- <li class="clearfix"></li> -->
			</ul>
		</div>
		
		<div id="searchForm" class="breadcrumb form-search">
			<h5>餐卡同步</h5>
			<!-- <input id="pageNo" name="pageNo" type="hidden" value=""/>
			<input id="pageSize" name="pageSize" type="hidden" value=""/> -->
			<ul class="ul-form" style="height: 70px">
				<li><label>开始时间：</label>
					<input id="startTime4" type="text" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
						
				</li>
				<li><label>时间间隔：</label>
					<input id="timeLag4"  maxlength="100" class="digits valid" value="24"/>(小时)
					<!-- <label for="digits" class="error">只能输入整数</label> -->
				</li>
				<li><label>启动状态：</label>
				<!-- 取消与门禁系统的交互，不再同步门禁系统数据 -->
					<input id="btnStart" onclick="startOend.startMealCard('START');" class="btn btn-primary" type="button" value="启动"/>
					<input id="btnEnd" onclick="startOend.startMealCard('STOP');" class="btn btn-primary" type="button" value="停止"/>
				</li>
				<!-- <li class="clearfix"></li> -->
			</ul>
		</div>
		
		<div id="searchForm" class="breadcrumb form-search">
			<h5>考勤记录同步</h5>
			<!-- <input id="pageNo" name="pageNo" type="hidden" value=""/>
			<input id="pageSize" name="pageSize" type="hidden" value=""/> -->
			<ul class="ul-form" style="height: 70px">
				<li><label>开始时间：</label>
					<input id="startTime5" type="text" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${testData.beginInDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> 
						
				</li>
				<li><label>时间间隔：</label>
					<input id="timeLag5"  maxlength="100" class="digits valid" value="24"/>(小时)
					<!-- <label for="digits" class="error">只能输入整数</label> -->
				</li>
				<li><label>启动状态：</label>
				<!-- 取消与门禁系统的交互，不再同步门禁系统数据 -->
					<input id="btnStart" onclick="startOend.startKqDetail('START');" class="btn btn-primary" type="button" value="启动"/>
					<input id="btnEnd" onclick="startOend.startKqDetail('STOP');" class="btn btn-primary" type="button" value="停止"/>
				</li>
				<!-- <li class="clearfix"></li> -->
			</ul>
		</div>
		
	<div>
		<h5>系统之间数据同步关系如下：</h5>
		<img src="/3A/static/images/u19.jpg" style="width: 700px;height: 400px"></div>
	</div>
</body>
</html>