/**
 * 在window中初始化ZcefUtils对象
 */
if (this.ZcefUtils == null) {
	this.ZcefUtils = {};
	/**
	 * 信息框
	 */
	ZcefUtils.alertIconInfo = "ui-icon ui-icon-info";
	/**
	 * 提问框
	 */
	ZcefUtils.alertIconHelp = "ui-icon ui-icon-help";

	/**
	 * 为String添加trim函数,去掉字符串两端的空格
	 * @returns {string}
	 */
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	};

	/**
	 * 为String添加parseJSON函数,使其可以直接转换为JSON对象
	 * @returns {JSON}
	 */
	String.prototype.parseJSON = function() {
		return JSON.parse(this);
	};
}
(function() {
	/**
	 * 获取字符串长度(一个中文算两个长度)
	 * @param str 字符串
	 * @returns {number}
	 */
	ZcefUtils.getStrLength = function(str) {
		var strLength = 0;
		str = str + "";

		for ( var i = 0; i < str.length; i++) {
			if ((str.charCodeAt(i) & 0xff00) != 0) {
				strLength++;
			}
			strLength++;
		}
		return strLength;
	};

	/**
	 * @param str 格式化数字为定长的字符串
	 * @param digit 定长:规定这个字符串需要几位,不足的在前面补0 示例 str:123 digit:5 return 00123
	 * @returns {string}
	 */
	ZcefUtils.formatNumberToString = function(str, digit) {
		var yDigit = (str + "").length;
		for ( var i = 0; i < digit - yDigit; i++) {
			str = "0" + str;
		}
		return str;
	};

	/**
	 * 获得表单值
	 * @param containerId 容器id，比如:<form>表单的id，<div>容器的id
	 * @returns {Object}
	 */
	ZcefUtils.obtainValues = function(containerId) {
		var selector = ZcefUtils.getSelectorPrefix(containerId);
		var o = new Object();
		$(selector + " :input[type!='checkbox']").each(
				function() {
					var jq = $(this);
					var key = jq.attr("name");
					var tagName = jq.get(0).tagName.toLowerCase();// var tmpTagName = this.tagName
					var inputType = jq.attr("type");
					// 排除按钮
					if (tagName === "button" || inputType === "button"
							|| inputType == "submit" || tagName === "reset"
							|| inputType === "reset") {
						// 在jquery的循环中，return false相当于普通循环中的break，return
						// true相当于普通循环中的continue
						return true;
					}
					if (inputType === "radio") {
						o[key] = $(selector + " :input[name='" + key + "']:checked").val();
					} else {
						o[key] = jq.val();
					}
				});
		var checkboxValues = ZcefUtils.obtainCheckboxValues(containerId);
		$.extend(o, checkboxValues);
		return o;
	};

	/**
	 * 获得表单中checkbox的值
	 * @param containerId  容器id，比如:<form>表单的id，<div>容器的id
	 * @returns {Object}
	 */
	ZcefUtils.obtainCheckboxValues = function(containerId) {
		var checkboxValues = new Object();
		var selector = ZcefUtils.getSelectorPrefix(containerId);
		var nameArr = [];
		var $chks = $(selector + " :input:checkbox:checked");
		$chks.each(function() {
			// inArray这个方法， 在低版本的jquery可能没有， 建议用高版本的
			if ($.inArray($(this).attr("name"), nameArr) == -1) {
				nameArr.push($(this).attr("name"));
			}
		});

		// 遍历数组
		$.each(nameArr, function(i, item) {
			var valArr = new Array();
			$(selector + " :input[name='" + item + "']:checkbox:checked").each(
					function() {
						valArr.push($(this).val());
					});
			checkboxValues[item] = valArr.join(",");
		});
		return checkboxValues;
	};

	/**
	 * 设置表单值
	 * @param containerId 容器ID
	 * @param values 值对象
	 * @param excludes 排除自动赋值的栏位name,是数组类型
	 */
	ZcefUtils.putValues = function(containerId, values, excludes) {
		// excludes如果有值，必须是数组类型
		if (excludes && !$.isArray(excludes)) {
			layer.alert("excludes必须是数组", {
				icon : 0
			});
			return;
		}
		var selector = ZcefUtils.getSelectorPrefix(containerId);
		$(selector + " :input").each(
			function() {
				var jq = $(this);
				var key = jq.attr("name");
				var tagName = jq.get(0).tagName.toLowerCase();// var tmpTagName = this.tagName
				var inputType = jq.attr("type");
				// 存在
				if (excludes && $.inArray(key, excludes) != -1) {
					return true;
				}
				// 排除按钮
				if (tagName === "button" || inputType === "button"
						|| inputType == "submit" || tagName === "reset" || inputType === "reset") {
					// 在jquery的循环中，return false相当于普通循环中的break，return
					// true相当于普通循环中的continue
					return true;
				}
				if (inputType === "radio" || inputType === "checkbox") {
					var v = jq.attr("value");
					if (v == values[key]) {
						jq.attr("checked", true);
					}
				} else {
					jq.val(values[key]);
				}
			});
		/*
		 * $.each(values, function (key,item) { //$("body
		 * :input[name='school']").val("school2"); //$("body
		 * :input[name='gender'][value='female']").attr("checked",true);
		 * //$("body
		 * :input[name='interest'][value='swimming']").attr("checked",true); var
		 * contorl = $(selector + " :input[name='"+key+"']");
		 * if(contorl.attr("type") === "radio" || contorl.attr("type") ===
		 * "checkbox"){ $(selector + "
		 * :input[name='"+key+"'][value='"+item+"']").attr("checked",true);
		 * }else{ $(selector + " :input[name='"+key+"']").val(item); } });
		 */
	};

	/**
	 * 设置页面栏位不可编辑
	 * @param containerId 容器ID
	 * @param options 页面按钮默认不灰显，需要通过设置disabledBtns指定灰显按钮的id excludes排除不灰显的栏位
	 */
	ZcefUtils.controlDisabled = function(containerId, options) {
		var settings = {
			disabledBtns : [],
			excludes : []
		};
		// 合并参数
		settings = $.extend(settings, options);

		var selector = ZcefUtils.getSelectorPrefix(containerId);
		$(selector + " :input").each(function(index, ele) {
			var $ele = $(ele);
			var tagName = $ele.get(0).tagName.toLowerCase();
			var type = $ele.attr("type");
			var name = $ele.attr("name");
			var id = $ele.attr("id");
			// 存在
			if ($.inArray(name,settings.excludes) != -1) {
				return true;
			}
			if (tagName == "button" || type == "button") {
				// 不存在
				if ($.inArray(id,settings.disabledBtns) == -1) {
					return true;
				}
			}
			$ele.attr("disabled", true);
		});
	};

	/**
	 * 打开添加页面
	 * @param title 标题
	 * @param url 页面URL
	 * @param width 页面宽度 默认值800
	 * @param height 页面高度 默认值当前窗体高度-50
	 * @closeFunc 页面关闭后的回调函数
	 */
	ZcefUtils.openAddLayer = function(options) {
		var settings = {
			title : '添加',
			url : '404.shtml',
			width : 800,
			height : ($(window).height() - 50),
			closeFunc : null
		};
		settings = $.extend(settings, options);
		layer.open({
			type : 2,
			area : [ settings.width + 'px', settings.height + 'px' ],
			fix : false, // 不固定
			maxmin : true,
			shade : 0.4,
			title : settings.title,
			content : settings.url,
			end : settings.closeFunc
		});
	};

	/**
	 * 打开修改页面
	 * @param title 标题
	 * @param url 页面URL
	 * @param width 页面宽度 默认值800
	 * @param height 页面高度 默认值当前窗体高度-50
	 * @param openAfterFunc 打开页面后的初始化函数
	 * @param closeFunc 页面关闭后的回调函数
	 */
	ZcefUtils.openUpdateLayer = function(options) {
		var settings = {
			title : '修改',
			url : '404.shtml',
			width : 800,
			height : ($(window).height() - 50),
			openAfterFunc : null,
			yesFunc : null,
			closeFunc : null
		};
		settings = $.extend(settings, options);
		layer.open({
			type : 2,
			area : [ settings.width + 'px', settings.height + 'px' ],
			fix : false, // 不固定
			maxmin : true,
			shade : 0.4,
			title : settings.title,
			content : settings.url,
			success : settings.openAfterFunc,
			yes : settings.yesFunc,
			end : settings.closeFunc
		});
	};

	/**
	 * 删除
	 * @param url 删除的URL
	 * @param p 删除时的参数对象，比如：{primaryKey:value}
	 * @param deleteAfterFunc 删除后的回调函数
	 */
	ZcefUtils.deleteLayer = function(options) {
		var settings = {
			url : null,
			param : null,
			deleteAfterFunc : null,
			dataType : "text"
		};
		// 合并参数
		settings = $.extend(settings, options);
		if (!settings.url) {
			layer.alert("删除URL不能为空", {
				icon : 0
			});
			return;
		}

		layer.confirm('确认要删除吗？', {
			icon : 3,
			title : '提示'
		}, function(index) {
			// 发送删除请求
			var httpRequest = new HTTPRequest({
				url : settings.url,
				data : settings.param,
				dataType : settings.dataType,
				successFunc : function(result) {
					layer.msg('删除成功!', {
						icon : 1,
						time : 2000
					// 2秒关闭（如果不配置，默认是3秒）
					});
					if (settings.deleteAfterFunc) {
						settings.deleteAfterFunc.call(this);
					}
				}
			});
			httpRequest.sendJSON();
			
			layer.close(index);
		});
	};
	
	/**
	 * 自定义消息交互提示框
	 * @param url URL
	 * @param tipsMessage 自定义消息提示
	 * @param param 参数对象，比如：{primaryKey:value}
	 * @param afterFunc 回调函数
	 */
	ZcefUtils.confirmLayer = function(options) {
		var settings = {
			url : null,
			tipsMessage : null,
			param : null,
			afterFunc : null,
			dataType : "text"
		};
		// 合并参数
		settings = $.extend(settings, options);
		if (!settings.url) {
			layer.alert("URL不能为空", {
				icon : 0
			});
			return;
		}
		if (!settings.tipsMessage) {
			layer.alert("消息内容不能为空", {
				icon : 0
			});
			return;
		}

		layer.confirm(settings.tipsMessage, {
			icon : 3,
			title : '提示'
		}, function(index) {
			// 发送删除请求
			var httpRequest = new HTTPRequest({
				url : settings.url,
				data : settings.param,
				dataType : settings.dataType,
				successFunc : function(result) {
					layer.msg('操作成功!', {
						icon : 1,
						time : 2000
					// 2秒关闭（如果不配置，默认是3秒）
					});
					if (settings.afterFunc) {
						settings.afterFunc.call(this);
					}
				}
			});
			httpRequest.sendJSON();
			layer.close(index);
		});
	};
	
	/**
	 * 自定义消息交互提示框,带后台返回值
	 * @param url URL
	 * @param tipsMessage 自定义消息提示
	 * @param param 参数对象，比如：{primaryKey:value}
	 * @param afterFunc 回调函数
	 */
	ZcefUtils.confirmLayerWithResult = function(options) {
		var settings = {
			url : null,
			tipsMessage : null,
			param : null,
			afterFunc : null,
			dataType : "text"
		};
		// 合并参数
		settings = $.extend(settings, options);
		if (!settings.url) {
			layer.alert("URL不能为空", {
				icon : 0
			});
			return;
		}
		if (!settings.tipsMessage) {
			layer.alert("消息内容不能为空", {
				icon : 0
			});
			return;
		}

		layer.confirm(settings.tipsMessage, {
			icon : 3,
			title : '提示'
		}, function(index) {
			// 发送删除请求
			var httpRequest = new HTTPRequest({
				url : settings.url,
				data : settings.param,
				dataType : settings.dataType,
				successFunc : function(result) {
					layer.msg('操作成功!', {
						icon : 1,
						time : 2000
					// 2秒关闭（如果不配置，默认是3秒）
					});
					if (settings.afterFunc) {
						settings.afterFunc.call(this,result);
					}
				}
			});
			httpRequest.sendJSON();
			layer.close(index);
		});
	};

	/**
	 * 统一分页查询
	 * @param options 分页参数对象
	 */
	ZcefUtils.pagingQuery = function(options){
		var settings = {
			sqlStatement : null,//分页SQL，必须提供
			paramContainer : "paramContainer",//查询条件容器ID
			parameters : null,//查询参数，备注：paramContainer和parameters两者任意提供一种即可,优先取parameters
			pageTemplate : "pageTemplate",//分页模板ID
			pagingShowContainer : "pagingShowContainer",//分页数据呈现的容器ID
			pagingIndexContainer : "pagingIndexContainer",//分页索引呈现的容器ID,比如：首页、上一页、下一页、尾页显示的地方
			countContainer : "countContainer",//分页统计数呈现的容器ID,比如：总记录条数、总共多少页显示的地方
			currentPage : 1,//默认当前页，第1页
			pageNumber : 10,//默认分页大小，10条记录
			isAsync : true//默认异步发送查询请求
		};
		// 合并参数
		settings = $.extend(settings, options);
		
		// 默认创建保存当前页的隐藏域
		if (!$("#currentPage").val()) {
			$("<input type=\"hidden\" name=\"currentPage\" id=\"currentPage\" value=\"1\" />").appendTo($("#" + settings.paramContainer));
		}
		
		//组织查询参数
		var searchParam = settings.parameters ? settings.parameters : ZcefUtils.obtainValues(settings.paramContainer);
		searchParam.sqlStatement = searchParam.sqlStatement ? searchParam.sqlStatement : settings.sqlStatement;
		//searchParam.currentPage = searchParam.currentPage ? searchParam.currentPage : settings.currentPage;
		searchParam.currentPage = settings.currentPage;
		searchParam.pageNumber = searchParam.pageNumber ? searchParam.pageNumber : settings.pageNumber;

		var httpRequest = new HTTPRequest({
			url : "query/pagingQuery.ajax",
			data : searchParam,
			successFunc : function(result){
				if (!result) return;

				$('#' + settings.countContainer).html("总 " + result.totalNumber + " 条  每页 " + result.pageNumber + " 条  共 " + result.totalPage + " 页");

				// 获取分页模板
				var gettpl = $('#' + settings.pageTemplate).html();

				// 分页数据渲染
				laytpl(gettpl).render(result, function(html) {
					$('#' + settings.pagingShowContainer).html(html);
				});

				laypage({
					cont : $('#' + settings.pagingIndexContainer), // 容器。值支持id名、原生dom对象，jquery对象,
					pages : result.totalPage, //总页数
					curr : result.currentPage || 1, //当前页
					// skip: true, //是否开启跳页
					skin : 'molv',
					groups : 2, // 连续显示分页数
					jump : function(obj, first) { //触发分页后的回调
						if(!first){ //必需这样判断，不然就死循环。点击跳页触发函数自身，并传递当前页：obj.curr
							// 设置当前页，用于查询页面弹出框关闭时，页面不跳转到第一页
							$("#currentPage").val(obj.curr);
							settings.currentPage = obj.curr;
							ZcefUtils.pagingQuery(settings);
						}
					}
				});
			}
		});
		httpRequest.sendJSON();
	};
	
	/**
	 * 统一不分页查询
	 * @param options 查询参数对象
	 */
	ZcefUtils.noPagingQuery = function(options){
		var settings = {
			sqlStatement : null,//分页SQL，必须提供
			paramContainer : "paramContainer",//查询条件容器ID
			parameters : null,//查询参数，备注：paramContainer和parameters两者任意提供一种即可,优先取parameters
			pageTemplate : "pageTemplate",//模板ID
			showContainer : "showContainer",//数据呈现的容器ID
			isAsync : true//默认异步发送查询请求
		};
		// 合并参数
		settings = $.extend(settings, options);
		
		//组织查询参数
		var searchParam = settings.parameters ? settings.parameters : ZcefUtils.obtainValues(settings.paramContainer);
		searchParam.sqlStatement = searchParam.sqlStatement ? searchParam.sqlStatement : settings.sqlStatement;

		var httpRequest = new HTTPRequest({
			url : "query/noPagingQuery.ajax",
			data : searchParam,
			successFunc : function(result){
				if (!result) return;

				// 获取页面模板
				var gettpl = $('#' + settings.pageTemplate).html();

				// 分页数据渲染
				laytpl(gettpl).render(result, function(html) {
					$('#' + settings.showContainer).html(html);
				});
			}
		});
		httpRequest.sendJSON();
	};
	
	
	/**
	 * 创建时间下拉框的选项值
	 * @param 时间下拉框的id值
	 */
	ZcefUtils.createTimeOption = function(selectId) {
		var timeSelect = $("#" + selectId);
		// 清空
		timeSelect.empty();
		for ( var i = 0; i < 24; i++) {
			var h = i < 10 ? "0" + i : i;
			if (i === 17) {
				$("<option value ='" + h + ":00:00' title='" + h + ":00' selected > " + h + ":00</option>").appendTo(timeSelect);
			} else {
				$("<option value ='" + h + ":00:00' title='" + h + ":00'> " + h + ":00</option>").appendTo(timeSelect);
			}
			$("<option value ='" + h + ":30:00' title='" + h + ":30'> " + h + ":30</option>").appendTo(timeSelect);
		}
	};

	/**
	 * 关闭当前窗体
	 */
	ZcefUtils.closeWindow = function() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
				window.opener = null;
				window.close();
			} else {
				window.open('', '_top');
				window.top.close();
			}
		} else if (navigator.userAgent.indexOf("Firefox") > 0) {
			window.location.href = 'about:blank ';
		} else {
			window.opener = null;
			window.open('', '_self', '');
			window.close();
		}
	};
	
	/**
	 * 两个日期的相差天数
	 * @param startDt 起始日期
	 * @param endDt 结束日期
	 * @return 相差天数
	 */
	ZcefUtils.diffDays = function(startDt,endDt){
		var startDate = ZcefUtils.stringToDate(startDt);
		var endDate = ZcefUtils.stringToDate(endDt);
		if(!startDate || !endDate){
			return null;
		}
		return Math.abs(parseInt((endDate - startDate) / 86400000));
	};
	
	/**
	 * 将字符串日期转化为Date对象
	 * @param strDate 字符串日期
	 * 格式：YYYY-MM-dd 
	 */
	ZcefUtils.stringToDate = function(strDate){
		if(!strDate)return null;
		
        var arys = strDate.split('-');
        if(arys && arys.length == 3){
        	return new Date(arys[0],arys[1],arys[2]);
        }else{
        	return null;
        }
	};
	
	/**
	 * 选择器前缀
	 * @param containerId 容器ID
	 * @returns {string} 前缀
	 */
	ZcefUtils.getSelectorPrefix = function(containerId) {
		return containerId ? ("#" + containerId) : "body";
	};

	/**
	 * 消息对话框(已废弃，采用layer提供的弹出框)
	 * @param title 标题
	 * @param content 内容
	 * @param width 宽度，
	 * @param height 高度
	 * @param iconType 图标样式类别
	 */
	ZcefUtils.alert = function(title, content, width, height, iconType) {
		try {
			var div = $("<div/>");
			div.attr("title", title);
			var p = $("<p/>");
			p.css("width", "100%");
			p.append(content);

			var span = $("<span />");
			span.addClass(iconType ? iconType : ZcefUtils.alertIconInfo);
			span.attr("style", "float: left;margin:3px 7px 0 0");
			p.append(span);

			div.append(p);
			$("body").append(div);

			div.dialog({
				width : width ? width : Math.floor($(window).width() / 4),
				height : height ? height : "auto",
				modal : true,
				hide : "fade",
				show : "fade",
				minWidth : 300,
				minHeight : 130,
				resizable : false,
				buttons : [ {
					text : "确定",
					click : function() {
						$(this).dialog("close");
					}
				} ],
				close : function() {
					$(this).remove();
				}
			});
		} catch (e) {
			alert(content);
		}
	};

	/**
	 * 确认对话框(已废弃，采用layer提供的弹出框)
	 * 
	 * @param title 标题
	 * @param content 内容
	 * @param confirmCall 确认回调函数
	 * @param cancelCall 取消回调函数
	 * @param width 宽度
	 * @param height 高度
	 */
	ZcefUtils.alertConfirm = function(title, content, confirmCall, cancelCall,
			width, height) {
		var div = $("<div />");
		div.attr("title", title ? title : "系统提示");
		div.attr("text-align", "center");
		var p = $("<p />");

		var span = $("<span />");
		span.addClass("ui-icon ui-icon-alert");
		span.attr("style", "float: left;margin:3px 7px 0 0");
		p.append(span);
		p.append(content);
		div.append(p);
		$("body").append(div);
		div.dialog({
			autoOpen : true,
			modal : true,
			width : width ? width : Math.floor($(window).width() / 4),
			height : height ? height : "auto",
			minWidth : 300,
			minHeight : 130,
			buttons : {
				"确定" : function() {
					confirmCall.call(this);
					$(this).remove();
				},
				"取消" : function() {
					cancelCall.call(this);
					$(this).remove();
				}
			},
			close : function() {
				$(this).remove();
			}
		});
	};
})();

/*******************************************************************************
 * * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符 * 年(y)可以用 1-4
 * 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg: (new Date()).pattern("yyyy-MM-dd
 * hh:mm:ss.S")==> 2006-07-02 08:09:04.423 (new Date()).pattern("yyyy-MM-dd E
 * HH:mm:ss") ==> 2009-03-10 二 20:09:04 (new Date()).pattern("yyyy-MM-dd EE
 * hh:mm:ss") ==> 2009-03-10 周二 08:09:04 (new Date()).pattern("yyyy-MM-dd EEE
 * hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new Date()).pattern("yyyy-M-d
 * h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

/*
 * ! Math.uuid.js (v1.4) http://www.broofa.com mailto:robert@broofa.com
 * 
 * Copyright (c) 2010 Robert Kieffer Dual licensed under the MIT and GPL
 * licenses.
 */

/*
 * Generate a random uuid.
 * 
 * USAGE: Math.uuid(length, radix) length - the desired number of characters
 * radix - the number of allowable values for each character.
 * 
 * EXAMPLES: // No arguments - returns RFC4122, version 4 ID >>> Math.uuid()
 * "92329D39-6F5C-4520-ABFC-AAB64544E172"
 *  // One argument - returns ID of the specified length >>> Math.uuid(15) // 15
 * character ID (default base=62) "VcydxgltxrVZSTV"
 *  // Two arguments - returns ID of the specified length, and radix. (Radix
 * must be <= 62) >>> Math.uuid(8, 2) // 8 character ID (base=2) "01001010" >>>
 * Math.uuid(8, 10) // 8 character ID (base=10) "47473046" >>> Math.uuid(8, 16) //
 * 8 character ID (base=16) "098F4D35"
 */
(function() {
	// Private array of chars to use
	var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
			.split('');

	Math.uuid = function(len, radix) {
		var chars = CHARS, uuid = [], i;
		radix = radix || chars.length;

		if (len) {
			// Compact form
			for (i = 0; i < len; i++)
				uuid[i] = chars[0 | Math.random() * radix];
		} else {
			// rfc4122, version 4 form
			var r;

			// rfc4122 requires these characters
			uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
			uuid[14] = '4';

			// Fill in random data. At i==19 set the high bits of clock sequence
			// as
			// per rfc4122, sec. 4.1.5
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}

		return uuid.join('');
	};

	// A more performant, but slightly bulkier, RFC4122v4 solution. We boost
	// performance
	// by minimizing calls to random()
	Math.uuidFast = function() {
		var chars = CHARS, uuid = new Array(36), rnd = 0, r;
		for ( var i = 0; i < 36; i++) {
			if (i == 8 || i == 13 || i == 18 || i == 23) {
				uuid[i] = '-';
			} else if (i == 14) {
				uuid[i] = '4';
			} else {
				if (rnd <= 0x02)
					rnd = 0x2000000 + (Math.random() * 0x1000000) | 0;
				r = rnd & 0xf;
				rnd = rnd >> 4;
				uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
			}
		}
		return uuid.join('');
	};

	// A more compact, but less performant, RFC4122v4 solution:
	Math.uuidCompact = function() {
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
				function(c) {
					var r = Math.random() * 16 | 0, v = c == 'x' ? r
							: (r & 0x3 | 0x8);
					return v.toString(16);
				});
	};
})();