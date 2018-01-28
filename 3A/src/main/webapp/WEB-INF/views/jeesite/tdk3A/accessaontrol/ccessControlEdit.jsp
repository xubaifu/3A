<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门禁管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="/3A/static/3Aselect/select.js"></script>
	<link rel="stylesheet" href="/3A/static/3Aselect/select.css"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//获取部门信息
			$("#officeId").val(sessionStorage.getItem("officeId"));
			$("#officeName").val(sessionStorage.getItem("officeName"));
			//监听下拉选择框是否改变
			$("#selectArea").on("change", function() {
				var officeId = $("#officeId").val();
				if(officeId == null || officeId == ""){
					showTip("请选择部门!","",2000,500);
					return;
				}
				
				$("#selectDoor").children().remove();
				$("#doorList").children().remove();
				
				$.ajax({
					type : "POST",
					dataType : "json",
					data : {"officeId":officeId },
					url : "/3A/a/accessaontrol/accessControl/getAllDoorByArea",
					async : true,
					beforeSend:function(XMLHttpRequest){
						loading('正在获取门禁列表，请稍等...');
			         },
					success : function(data) {
						var html = '';
						var selectHtml = '';
						//拼接未选择的门禁
						for(var i=0;i<data[0].length;i++){
							var boo = false;
							//过滤所有的门禁中已经选择的门禁
							for(var j = 0; j < data[1].length; j++){
								//如果当前门禁已经存在于已选择的门禁中，则不再拼接
								if(data[0][i].fDoorname == data[1][j].fDoorname){
									boo = true;
								}
							}
							if(!boo){
								if(data[0][i].fDoorname != null){
									/* html = html + '<div data-value="'+data[i].fControllerno+';'+data[i].fControllerid+';'+data[i].fDoorid+';'+data[i].fDoorno+';'+data[i].fIp+';'+data[i].fControllersn+'" data-index="0" class="item">'+ */
									html = html + '<div data-value="'+data[0][i].fControllerid+'" data-index="0" class="item">'+
									data[0][i].fDoorname+
									'</div>';
								}
							}
							
						}
						//拼接已选择的门禁
						for(var i=0;i<data[1].length;i++){
							if(data[0][i].fDoorname != null){
								/* html = html + '<div data-value="'+data[i].fControllerno+';'+data[i].fControllerid+';'+data[i].fDoorid+';'+data[i].fDoorno+';'+data[i].fIp+';'+data[i].fControllersn+'" data-index="0" class="item">'+ */
								selectHtml = selectHtml + '<div data-value="'+data[1][i].fControllerid+'" data-index="0" class="item">'+
								data[1][i].fDoorname+
								'</div>';
							}
						}
						$("#doorList").append(html);
						$("#selectDoor").append(selectHtml);
						//重新加载选项
						two_way_list_selector($("#two_way_list_selector_a"));
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
		//保存
		var submitFun = function(){
			console.log($("#officeId").val());
			var officeId = $("#officeId").val();
			if(officeId == null || officeId == ""){
				showTip("请选择部门!","",2000,500);
				return;
			}
			//将门禁控制器的信息存入list，后台解析使用
			var list=[];
			$("#selectDoor div").each(function(index,element){
				console.log(index+":"+$(this).attr("data-value"));
				list[index] = $(this).attr("data-value");
			});
			if(list.length == 0){
				showTip("请选择门禁!","",2000,500);
				return;
			}
			var doorMessage = JSON.stringify(list).replace(/\"/g, '').replace(/\[/g, '').replace(/\]/g, '');
			console.log(doorMessage);
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {"doorMessage":doorMessage,"officeId":officeId},
				url : "/3A/a/accessaontrol/accessControl/addDoorToOffice",
				async : true,
				beforeSend:function(XMLHttpRequest){
					loading('正在提交，请稍等...');
					$("#btnSubmit").remove();
		         },
				success : function(data) {
					console.log(data);
					if(data[0] == 1){
						showTip("修改成功!","",2000,500);
					}
				},
				error : function(){
					showTip("提交失败!","",2000,500);
				},
				complete:function(XMLHttpRequest,textStatus){
					//关闭加载框
					closeLoading();
		         }
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/accessaontrol/accessControl/ccessControlCheck">门禁查看</a></li>
		<li class="active"><a href="${ctx}/accessaontrol/accessControl/ccessControlEdit">门禁编辑</a></li>
	</ul><br/>
	<div id="inputForm" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">部门名称：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${entity.id }" labelName="office.name" labelValue="${entity.name }"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group" style="margin-left: 12%">
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
		<div id="two_way_list_selector_a" class="two_way_list_selector margin_top_10">
			<div class="select_l">
				<div class="option">
					<div class="l">可选门禁</div>
				</div>
				<div class="select_a" id="doorList">
					<!-- <div data-value="1" data-index="0" class="item">1号门</div> -->
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
					<div class="r" style="border-bottom: 1px solid #ddd;">已选门禁</div>
				</div>
				<div class="select_b" id="selectDoor"></div>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="submitFun()"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</div>
</body>
</html>