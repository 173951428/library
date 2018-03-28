/**
 * 在window中初始化数据字典Dictionary对象
 */
if(this.Dictionary == null){
	this.Dictionary = {};
	this.Dictionary["examineStatus"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "未审批" , "value" : "unexamined" },{ "label" : "正在审批" , "value" : "examining" },{ "label" : "驳回" , "value" : "reject" },{ "label" : "同意" , "value" : "agree" },{ "label" : "拒绝" , "value" : "refuse" }];
	this.Dictionary["highestQualification"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "高中/中专/中技" , "value" : "high_school" },{ "label" : "大专" , "value" : "junior_college" },{ "label" : "专升本" , "value" : "top_up" },{ "label" : "本科" , "value" : "bachelor" },{ "label" : "硕士" , "value" : "master" },{ "label" : "博士" , "value" : "learned_scholar" }];
	this.Dictionary["leaveType"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "事假" , "value" : "casual_leave" },{ "label" : "年假" , "value" : "annual_leave" },{ "label" : "调休" , "value" : "adjust_leave" },{ "label" : "丧假" , "value" : "funeral_leave" },{ "label" : "婚假" , "value" : "marriage_leave" },{ "label" : "病假" , "value" : "sick_leave" },{ "label" : "产假" , "value" : "maternity_leave" },{ "label" : "授乳假" , "value" : "lactation_leave" },{ "label" : "陪产假" , "value" : "paternity_leave" },{ "label" : "难产假" , "value" : "maternity_hard_leave" },{ "label" : "流产假" , "value" : "miscarriage_leave" },{ "label" : "生育假" , "value" : "pregnancy_late_leave" },{ "label" : "多育假" , "value" : "maternity_many_leave" },{ "label" : "产前检查" , "value" : "prenatal_check" },{ "label" : "孕晚期工间休息" , "value" : "rest_in_work" },{ "label" : "公假" , "value" : "public_leave" },{ "label" : "哺乳假" , "value" : "nurse_leave" },{ "label" : "其他" , "value" : "other" },{ "label" : "妈咪月" , "value" : "mommy_month_leave" },{ "label" : "献血假" , "value" : "donate_blood_leave" }];
	this.Dictionary["politicalStatus"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "群众" , "value" : "masses" },{ "label" : "共青团员" , "value" : "league_member" },{ "label" : "团员" , "value" : "member" },{ "label" : "预备党员" , "value" : "probationary_party_member" },{ "label" : "党员" , "value" : "party_member" },{ "label" : "中共党员" , "value" : "chinese_communist_party" }];
	this.Dictionary["ranks"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "N/A" , "value" : "N/A" },{ "label" : "0级" , "value" : "0" },{ "label" : "1级" , "value" : "1" },{ "label" : "2级" , "value" : "2" },{ "label" : "3级" , "value" : "3" },{ "label" : "4级" , "value" : "4" },{ "label" : "5级" , "value" : "5" },{ "label" : "Q级" , "value" : "Q" },{ "label" : "R级" , "value" : "R" },{ "label" : "S级" , "value" : "S" },{ "label" : "T级" , "value" : "T" },{ "label" : "U级" , "value" : "U" },{ "label" : "N级" , "value" : "N" },{ "label" : "初级" , "value" : "L" },{ "label" : "中级" , "value" : "M" },{ "label" : "高级" , "value" : "H" }];
	this.Dictionary["reason"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "出差" , "value" : "business_trip" },{ "label" : "外出开会" , "value" : "out_meeting" },{ "label" : "外出办公" , "value" : "out_office" },{ "label" : "其他" , "value" : "other" }];
	this.Dictionary["role"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "普通员工" , "value" : "staff" },{ "label" : "组长或团队经理" , "value" : "team_leader" },{ "label" : "板块负责人" , "value" : "plate_leader" },{ "label" : "部门负责人" , "value" : "department_leader" },{ "label" : "人事行政部" , "value" : "personnel_administration" }];
	this.Dictionary["signetReasonType1"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "贷款" , "value" : "loans" },{ "label" : "实习协议" , "value" : "internship_agreement" },{ "label" : "三方协议" , "value" : "Tri-party" },{ "label" : "考试报名" , "value" : "exam" },{ "label" : "政府机关（派出所、街道）" , "value" : "government" },{ "label" : "实习鉴定" , "value" : "identification" },{ "label" : "其它" , "value" : "other" }];
	this.Dictionary["signetReasonType2"] = [{ "label" : "签证" , "value" : "visa" }];
	this.Dictionary["signetReasonType3"] = [{ "label" : "收入证明" , "value" : "incomeStatement" }];
	this.Dictionary["signetType"] = [{ "label" : "其它用印" , "value" : "other" },{ "label" : "签证用印" , "value" : "visa" },{ "label" : "收入证明用印" , "value" : "incomeStatement" }];
	this.Dictionary["status"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "正常" , "value" : "NORM" },{ "label" : "试用" , "value" : "PROB" },{ "label" : "实习" , "value" : "INTERN" }];
	this.Dictionary["type"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "正式员工" , "value" : "full_time_employees" },{ "label" : "派遣员工" , "value" : "send_staff" },{ "label" : "行编" , "value" : "bank_prepare" }];
	this.Dictionary["workTimeBased"] = [{ "label" : "请选择" , "value" : "" },{ "label" : "综合工时制" , "value" : "comprehensive" },{ "label" : "标准工时制" , "value" : "standard" },{ "label" : "不定时工时制" , "value" : "untime" },{ "label" : "轮班工时制" , "value" : "shift" }];
}
(function(){
	$("select").each(function(index,ele){
		//将普通的DOM元素转换为jQuery对象
		var $ele = $(ele);
		var listKey = $ele.attr("list");
		var nameKey = $ele.attr("name");
		if(listKey || nameKey){
			//对于select控件，如果设置了list属性，则用list属性从数据字典里加载选项,如果只有name属性，则用name属性取加载项
			var key = listKey || nameKey;
			//取数据字典的对应的选项，添加至下拉框中
			var _arr = Dictionary[key];

			if(!_arr) return true;

			$.each(_arr,function(index,item){
				$("<option value='" + item.value + "' title='" + item.label + "'>"+ item.label + "</option>").appendTo($ele);
			});
		}
	});

	/**
	 * 获取指定key对应的label值
	 */
	Dictionary.getLabel = function (key,val){
		var _arr = Dictionary[key];
		var label = "";
		$.each(_arr,function(index,item){
			if(val == item.value){
				label = item.label;
				return false;
			}
		});
	};
})();
