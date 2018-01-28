<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>餐卡机授权</title>
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
		
	});
	
	
	//根据部门获取用户
	var getUserByOffice = function(){
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
					html = html + '<div data-value="'+data[i].id+'" data-index="'+i+'" class="item">'+data[i].name+'</div>';
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
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/authorizationManage/authorizationManage/mealCard">餐卡机授权</a></li>
	</ul>
	<div>
		<div style="margin-left: 12%">
			<h5>人员配置</h5><br>
			<label>所属部门：</label>
			<sys:treeselect id="office" name="office.id"
				value="${testData.office.id}" labelName="office.name"
				labelValue="${testData.office.name}" title="部门"
				url="/sys/office/treeData?type=2" cssClass="input-small"
				allowClear="true" notAllowSelectParent="true" />
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
				<div class="select_b"></div>
			</div>
		</div>
	</div>
	
	<div>
		<div style="margin-left: 12%">
			<br>
			<h5>餐卡机配置</h5><br>
		</div>
		<div id="two_way_list_selector_b" class="two_way_list_selector margin_top_10">
			<div class="select_l">
				<div class="option">
					<div class="l">可选机器</div>
				</div>
				<div class="select_a">
					<div data-value="1" data-index="0" class="item">机器1</div>
					<div data-value="2" data-index="1" class="item">机器2</div>
					<div data-value="3" data-index="2" class="item">机器3</div>
					<div data-value="4" data-index="3" class="item">机器4</div>
					<div data-value="5" data-index="4" class="item">机器5</div>
					<div data-value="6" data-index="5" class="item">机器6</div>
					<div data-value="7" data-index="6" class="item">机器7</div>
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
			<div class="select_r">
				<div class="option">
					<div class="r" style="border-bottom: 1px solid #ddd;">已选机器</div>
				</div>
				<div class="select_b"></div>
			</div>
		</div>
	</div>
	<div style="margin-left: 44%">
		<br><br>
		<input id="btnSubmit" class="btn btn-primary" type="button" value="重置"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="btnReset" class="btn btn-primary" type="button" value="授权"/>
	</div>

</body>
</html>