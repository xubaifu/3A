<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>门禁授权</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="/3A/static/3Aselect/select.js"></script>
<link rel="stylesheet" href="/3A/static/3Aselect/select.css"/>
<script type="text/javascript">

	var oldOfficeid = "";
	$(document).ready(function() {
		//监听部门变化（采用定时器实现）
		oldOfficeid = $("#officeId").val();
		setInterval(function(){
			//监听部门变化
			var officrid= $("#officeId").val();
			if(officrid != oldOfficeid){
				oldOfficeid = officrid;
				getUserByOffice();
			}
		},500);
		
		//监听下拉选择框是否改变
		$("#selectArea").on("change", function() {
			$("#selectDoor").children().remove();
			$("#doorList").children().remove();
			
			$.ajax({
				type : "POST",
				dataType : "json",
				data : { },
				url : "/3A/a/accessaontrol/accessControl/getAllDoorByArea",
				async : true,
				beforeSend:function(XMLHttpRequest){
					loading('正在获取门禁列表，请稍等...');
		         },
				success : function(data) {
					//console.log(data)
					 var html = '';
					//var selectHtml = '';
					//拼接未选择的门禁
					for(var i=0;i<data[0].length;i++){
						if(data[0][i].fDoorname != null){
							//${door.fControllerno};${door.fControllerid};${door.fDoorid};${door.fDoorno};${door.fIp};${door.fControllersn}
							html = html + '<div data-value="'+data[0][i].fControllerno+';'+data[0][i].fControllerid+';'+data[0][i].fDoorid+';'+data[0][i].fDoorno+';'+data[0][i].fIp+';'+data[0][i].fControllersn+'" data-index="0" class="item">'+
							data[0][i].fDoorname+
							'</div>';
						}
					}
					$("#doorList").append(html);
					//$("#selectDoor").append(selectHtml);
					//重新加载选项
					two_way_list_selector($("#two_way_list_selector_b"));
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
		});
		
	});
	
	
	//根据部门获取用户
	var getUserByOffice = function(){
		$("#selectUser").children().remove();
		$("#userList").children().remove();
		var officeId = $("#officeId").val();
		console.log(officeId);
		$.ajax({
			type : "POST",
			dataType : "json",
			data : {"officeId":officeId},
			url : "/3A/a/authorizationManage/authorizationManage/getUserByOffice",
			async : true,
			success : function(data) {
				console.log(data);
				$("#selectUser").children().remove();
				var html = "";
				for(var i=0;i<data.length;i++){
					console.log(data[i].name);
					html = html + '<div data-value="'+data[i].id+';'+data[i].cardno+';'+data[i].doorId+'" data-index="'+i+'" class="item">'+data[i].name+'('+data[i].no+')</div>';
				}
				$("#selectUser").append(html);
				//重新加载选项
				two_way_list_selector($("#two_way_list_selector_a"));
				two_way_list_selector($("#two_way_list_selector_b"));
			},
			error : function(){
				alert("信息获取失败");
			}
		});
	};
	
	//授权提交
	var submitFun = function(){
		if($("#userList").children().length <= 0){
			showTip("请选择人员!","",2000,500);
			return;
		}
		if($("#selectDoor").children().length <= 0){
			showTip("请选择机器!","",2000,500);
			return;
		}
		var doorList=[];//存放门禁控制器信息
		var userList=[];//存放用户器信息
		var user = [];
		$("#userList div").each(function(index,element){
			userList[index] = $(this).attr("data-value");
		});
		console.log(JSON.stringify(userList).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, ''));
		var userMessage = JSON.stringify(userList).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, '');
		
		$("#selectDoor div").each(function(index,element){
			doorList[index] = $(this).attr("data-value");
		});
		console.log(JSON.stringify(doorList).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, ''));
		var doorMessage = JSON.stringify(doorList).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, '');
		$.ajax({
			type : "POST",
			dataType : "json",
			data : {"doorMessage" : doorMessage,"userMessage":userMessage},
			url : "/3A/a/authorization/authorization/addAccessControllerBatch",
			async : true,
			beforeSend:function(XMLHttpRequest){
				loading('正在提交，请稍等...');
				$("#btnSubmit").attr("");
	         },
			success : function(data) {
				console.log(data);
				if(data[0]==1){
					showTip("授权成功!","",2000,500);
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
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/authorizationManage/authorizationManage/accessControl">门禁器授权</a></li>
	</ul>
	<div>
		<div style="margin-left: 12%">
			<h5>人员配置</h5><br>
			<label>部门名称：</label>
			<sys:treeselect id="office" name="office.id"
				value="" labelName="office.name"
				labelValue="" title="部门"
				url="/sys/office/treeData?type=2" cssClass="input-small"
				allowClear="true" notAllowSelectParent="false" />
		</div>
		<div id="two_way_list_selector_a" class="two_way_list_selector margin_top_10">
			<div class="select_l">
				<div class="option">
					<div class="l">可选人员</div>
					<!-- <div class="r">
						<a>数量</a>
					</div> -->
				</div>
				<div class="select_a" id="selectUser">
					<!-- <div data-value="1" data-index="0" class="item">张三</div>
					<div data-value="2" data-index="1" class="item">李四</div>
					<div data-value="3" data-index="2" class="item">王五</div>
					<div data-value="4" data-index="3" class="item">赵六</div>
					<div data-value="5" data-index="4" class="item">王七</div>
					<div data-value="6" data-index="5" class="item">周八</div>
					<div data-value="7" data-index="6" class="item">哈哈</div> -->
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
					<div class="r" style="border-bottom: 1px solid #ddd;">已选人员</div>
				</div>
				<div class="select_b" id="userList"></div>
			</div>
		</div>
	</div>
	
	<div>
		<div style="margin-left: 12%">
			<br>
			<h5>控制器配置</h5><br>
			<label>所在区域：</label>
			<select id="selectArea" class="input-medium">
				<option value="">--请选择--</option>
				<option value="saab">区域1</option>
				<!-- <option value="opel">区域2</option>
				<option value="audi">区域2</option>
				<option value="saab">区域2</option> -->
			</select>
		</div>
		<br>
		<div id="two_way_list_selector_b" class="two_way_list_selector margin_top_10">
			<div class="select_l">
				<div class="option">
					<div class="l">可选机器</div>
				</div>
				<div class="select_a" id="doorList">
					<!-- <div data-value="1" data-index="0" class="item">机器1</div>
					<div data-value="7" data-index="6" class="item">机器7</div> -->
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
			<div class="select_r" >
				<div class="option">
					<div class="r" style="border-bottom: 1px solid #ddd;">已选机器</div>
				</div>
				<div class="select_b"  id="selectDoor"></div>
			</div>
		</div>
	</div>
	<div style="margin-left: 44%">
		<br><br>
		<a href="${ctx}/authorizationManage/authorizationManage/accessControl"><input id="btnReset" class="btn btn-primary" type="button" value="重置"/></a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="button" value="授权" onclick="submitFun()"/>
	</div>

</body>
</html>