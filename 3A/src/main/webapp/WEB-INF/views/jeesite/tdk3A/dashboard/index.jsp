<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>test管理</title>
<meta name="decorator" content="default" />
<script
	src="${pageContext.request.contextPath}/static/Echarts/echarts.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		//$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<div>

		<div id="mainBtn" style="width: 100%; height: 40px;">
			<input type="button" class="btn" onclick="changData(1)" value="1月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(2)" value="2月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(3)" value="3月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(4)" value="4月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(5)" value="5月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(6)" value="6月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(7)" value="7月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(8)" value="8月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(9)" value="9月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(10)" value="10月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(11)" value="11月" style="width: 8%">
			<input type="button" class="btn" onclick="changData(12)" value="12月" style="width: 8%">
		</div>

		</div>
	
	<div>
		<div id="main" style="width: 50%; height: 500px; float: left;"></div>
		<div id="main2" style="width: 50%; height: 500px; float: right;"></div>
	</div>
	
	<!-- 设置隐藏域，存放分页属性 -->
	<input id="pageNo" name="pageNo" type="hidden" value="" />
	<input id="pageSize" name="pageSize" type="hidden" value="" />
	<!-- <h3 style="text-align:center">一卡通操作记录</h3>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>姓名</th>
				<th>原因</th>
				<th>一卡通</th>
				<th>状态</th>
				<th>操作人</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>2017-01-03</td>
				<td>张三</td>
				<td>毫无缘由</td>
				<td>卡卡卡</td>
				<td>增加授权</td>
				<td>管理员</td>
			</tr>
			<tr>
				<td>2017-01-03</td>
				<td>张三</td>
				<td>毫无缘由</td>
				<td>卡卡卡</td>
				<td>增加授权</td>
				<td>管理员</td>
			</tr>
			<tr>
				<td>2017-01-03</td>
				<td>张三</td>
				<td>毫无缘由</td>
				<td>卡卡卡</td>
				<td>增加授权</td>
				<td>管理员</td>
			</tr>
			<tr>
				<td>2017-01-03</td>
				<td>张三</td>
				<td>毫无缘由</td>
				<td>卡卡卡</td>
				<td>增加授权</td>
				<td>管理员</td>
			</tr>
			<tr>
				<td>2017-01-03</td>
				<td>张三</td>
				<td>毫无缘由</td>
				<td>卡卡卡</td>
				<td>增加授权</td>
				<td>管理员</td>
			</tr>
			<tr>
				<td>2017-01-03</td>
				<td>张三</td>
				<td>毫无缘由</td>
				<td>卡卡卡</td>
				<td>增加授权</td>
				<td>管理员</td>
			</tr>
		</tbody>
	</table> -->
	<!-- 分页 -->
	<!-- <div class="pagination"></div> -->
	<!-- <div class="pagination">
		<ul>
			<li><a href="javascript:" onclick="page(1,10,'');">« 上一页</a></li>
			<li><a href="javascript:" onclick="page(1,10,'');">1</a></li>
			<li class="active"><a href="javascript:">2</a></li>
			<li class="disabled"><a href="javascript:">下一页 »</a></li>
			<li class="disabled controls"><a href="javascript:">当前 <input
					type="text" value="2"
					onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,10,'');"
					onclick="this.select();"> / <input type="text" value="10"
					onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(2,this.value,'');"
					onclick="this.select();"> 条，共 15 条
			</a></li>
		</ul>
		<div style="clear: both;"></div>
	</div> -->
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '一卡通管理统计',
				subtext : '基于当月月的统计数据',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [/*  '早餐', '午餐', '晚餐', '特餐1', '特餐2', '特餐3' */ ]
			},
			series : [ {
				name : '管理信息',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [ /* {
					value : 335,
					name : '早餐'
				}, {
					value : 310,
					name : '午餐'
				}, {
					value : 234,
					name : '晚餐'
				}, {
					value : 135,
					name : '特餐1'
				}, {
					value : 1548,
					name : '特餐2'
				}, {
					value : 135,
					name : '特餐3'
				} */ ],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);

		var myChart2 = echarts.init(document.getElementById('main2'));
		option2 = {
			title : {
				text : '餐卡消费统计',
				subtext : '基于当月统计数据'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ /* '早餐', '午餐', '晚餐', '特餐' */ ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				show : true,
				feature : {
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : [/*  '1月', '2月', '3月', '4月', '5月', '6月' */ ]
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ /* {
				name : '早餐',
				type : 'line',
				data : [ 2354, 3523, 5576, 3256, 2897, 3475 ],
				//stack : '总量'
			}, {
				name : '午餐',
				type : 'line',
				data : [ 5354, 6523, 3576, 6256, 7897, 3475 ],
				//stack : '总量'
			}, {
				name : '晚餐',
				type : 'line',
				data : [ 2342, 2313, 2576, 675, 445, 5655 ],
				//stack : '总量'
			}, {
				name : '特餐',
				type : 'line',
				data : [ 2146, 1234, 1576, 3454, 1234, 2544 ],
				//stack : '总量'
			}  */]
		};
		myChart2.setOption(option2);

		//切换月份
		changData(null);
		function changData(month) {
			
			var date = new Date();
		    var seperator1 = "-";
		    if(month==null||month == ""){
				var month = date.getMonth() + 1;
			    if (month >= 1 && month <= 9) {
			        month = "0" + month;
			    }
			}
		    //获取本月第一天
		    var startTime = date.getFullYear() + seperator1 + month + seperator1 + 1;
            
            var nextMonthFirstDay=new Date(date.getFullYear(),month,1);
            var oneDay=1000*60*60*24;
            var date2 = new Date(nextMonthFirstDay-oneDay);
            //获取本月最后一天
            var endTime = date.getFullYear() + seperator1 + month + seperator1 + date2.getDate();
           // alert(endTime)
			$.ajax({
				url : "dashboard/getMealsStatistics",
				data : {
					"startTime" : startTime,
					"endTime" : endTime
				},
				type : "post",
				async : true,
				dataType : "json",
				success : function(data) {
					//console.log(data);
					if (data != null || data.length != 0) {
						var mealTypeStr = "";//餐别字符串
						var mealTypeArr = [];//餐别数组
						var dateStr = "";//日期字符串
						var dateArr = [];//日期数组
						for (var i = 0; i < data.length; i++) {
							//获取餐别
							if (mealTypeStr.indexOf(data[i].mealType) == -1) {
								if (mealTypeStr == "") {
									mealTypeStr = data[i].mealType;
								} else {
									mealTypeStr = mealTypeStr + "," + data[i].mealType;
								}
							}
							//获取日期
							if (dateStr.indexOf(data[i].date) == -1) {
								if (dateStr == "") {
									dateStr = data[i].date + "日";
								} else {
									dateStr = dateStr + "," + data[i].date + "日";
								}
							}

						}
						mealTypeArr = mealTypeStr.split(",");//设置餐别
						dateArr = dateStr.split(",");//设置日期
						//计算每餐总量
						var mealNum = [];
						for (var k = 0; k < mealTypeArr.length; k++) {
							mealNum[k] = 0;
							for (var i = 0; i < data.length; i++) {
								if (mealTypeArr[k] == data[i].mealType) {
									//console.log(data[i].mealType);
									mealNum[k] = mealNum[k] + parseFloat(data[i].times);
								}
							}
						}
						//console.log(mealNum);

						//计算每日各餐总量

						var dateNum = [];//存放每天各餐数据

						for (var j = 0; j < mealTypeArr.length; j++) {
							var mealTimes = [];
							for (var k = 0; k < dateArr.length; k++) {
								mealTimes[k] = mealTypeArr[j] + "-" + dateArr[k];
							}
							dateNum[j] = mealTimes;
						}
						//将每天各餐数据赋值给dateNum
						for (var i = 0; i < data.length; i++) {
							for (var j = 0; j < dateNum.length; j++) {
								for (var k = 0; k < dateNum[j].length; k++) {
									if (dateNum[j][k] == (data[i].mealType + "-" + data[i].date + "日")) {
										dateNum[j][k] = data[i].times;
									}
								}

							}
						}
						//将dateNum中无数据的元素替换为0
						for (var j = 0; j < dateNum.length; j++) {
							for (var k = 0; k < dateNum[j].length; k++) {
								if (dateNum[j][k].indexOf("-") != -1) {
									dateNum[j][k] = 0;
								}else{
									dateNum[j][k] = parseFloat(dateNum[j][k]);
								}
							}

						}
						//console.log(dateNum);

						//设置饼图数据
						var pieData = [];
						for (var k = 0; k < mealTypeArr.length; k++) {
							pieData[k] = {
								value : mealNum[k],
								name : mealTypeArr[k]
							};
						}
						//设置折线图数据
						var lineData = [];//折线图数据
						for (var i = 0; i < mealTypeArr.length; i++) {
							var dayData = [];
							for (var k = 0; k < dateNum[i].length; k++) {
								dayData[k] = dateNum[i][k];
							}
							
							lineData[i] = {
										name : mealTypeArr[i],
										type : 'line',
										data : dayData,
										stack : '总量'
								};
							
						}
						//console.log(JSON.stringify(lineData));
						
						myChart.setOption({
							legend : {
								orient : 'vertical',
								left : 'left',
								data : mealTypeArr
							},
							series : [ {
								name : '管理信息',
								type : 'pie',
								radius : '55%',
								center : [ '50%', '60%' ],
								data : pieData,
								itemStyle : {
									emphasis : {
										shadowBlur : 10,
										shadowOffsetX : 0,
										shadowColor : 'rgba(0, 0, 0, 0.5)'
									}
								}
							} ]
						});
						myChart2.setOption({
							legend : {
								data : mealTypeArr
							},
							xAxis : [ {
								type : 'category',
								data : dateArr
							} ],
							series : lineData
						});
					}

				},
				error : function() {
					alert("数据获取失败！");
				}

			});

		}

		//获取当前时间
		/* function getNowFormatDate() {
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
		} */
	</script>
</body>
</html>