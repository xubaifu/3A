/**
 * two_way_list_selector.js - v1.0.0 (2016/7/26)
 * 
 * Allows you to easily page layout! by tie. qq：2185987263
 * 
 * Copyright (C) 2016, tie. All rights reserved.
 * 
 */
var two_way_list_selector = function(o) {
	var obj = o;
	var btn_a = o.find(".btn_a");
	var btn_b = o.find(".btn_b");
	var btn_c = o.find(".btn_remove_all");
	var btn_d = o.find(".btn_add_all");
	var select_a = o.find(".select_a");
	var select_b = o.find(".select_b");
	// 是否按下了shift
	var is_shift = false;
	// 是否按下了ctrl
	var is_ctrl = false;
	document.addEventListener("keydown", function(e) {
		is_shift = e.shiftKey;
		is_ctrl = e.ctrlKey;
	}, false);
	document.addEventListener("keyup", function(e) {
		is_shift = is_ctrl = false;
	}, false);
	// 进行排序
	var option_sort = function(o) {
		o.html(o.find("div").toArray().sort(
				function(a, b) {
					return parseInt($(a).attr("data-index"))
							- parseInt($(b).attr("data-index"));
				}));
		obj.find(".item").removeClass("is_select");
		obj.find(".item").unbind("dblclick").dblclick(function() {
			_dblclick($(this));
		});
		obj.find(".item").unbind("click").click(function() {
			_click($(this));
		});
	};
	// 在列表中双击时
	var _dblclick = function(o) {
		var flag = o.parent().attr("class");
		var a;
		if (flag == "select_a") {
			a = o.clone(true);
			select_b.append(a);
			o.remove();
			option_sort(select_b);
		} else {
			a = o.clone(true);
			select_a.append(a);
			o.remove();
			option_sort(select_a);
		}
	};
	// 在列表中单击时
	var temp_index = 0;
	var _click = function(o) {
		var flag = o.parent().attr("class");
		if (is_shift) {
			var this_index = o.index();
			if (temp_index != this_index) {
				obj.find("." + flag + " .item").each(
						function(index, element) {
							if (this_index > temp_index && index < this_index
									&& index >= temp_index) {
								$(this).addClass("is_select");
							}
							if (this_index < temp_index && index > this_index
									&& index <= temp_index) {
								$(this).addClass("is_select");
							}
						});
			}
			;
		}
		if (!is_ctrl && !is_shift) {
			obj.find("." + flag + " .item").each(function() {
				$(this).removeClass("is_select");
			});
		}
		if (o.hasClass("is_select")) {
			o.removeClass("is_select");
		} else {
			o.addClass("is_select");
		}
		temp_index = o.index();
	};
	// 选项单击时
	obj.find(".item").click(function() {
		_click($(this));
	});
	// 选项双击时
	obj.find(".item").dblclick(function() {
		_dblclick($(this));
	});
	// 加入选中
	btn_a.click(function() {
		var a = select_a.find(".is_select").clone(true);
		if (a.size() == 0) {
			return false;
		}
		select_b.append(a);
		select_a.find(".is_select").remove();
		option_sort(select_b);
	});
	// 删除选中
	btn_b.click(function() {
		var a = select_b.find(".is_select").clone(true);
		if (a.size() == 0) {
			return false;
		}
		select_a.append(a);
		select_b.find(".is_select").remove();
		option_sort(select_a);
	});
	// 删除全部
	btn_c.click(function() {
		select_a.append(select_b.find("div"));
		option_sort(select_a);
	});
	// 加入全部
	btn_d.click(function() {
		select_b.append(select_a.find("div"));
		option_sort(select_b);
	});
};
// 页面加载完毕后
$(document).ready(function(e) {
	two_way_list_selector($("#two_way_list_selector_a"));
	two_way_list_selector($("#two_way_list_selector_b"));
});