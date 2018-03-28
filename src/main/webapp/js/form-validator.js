
var dataTypeRepository = {
	'name'      : /^[\u4E00-\u9FA5]{2,8}|([\u4E00-\u9FA5]{2,8}(?:·[\u4E00-\u9FA5]{2,8})+|([a-zA-Z]+\s?)+)$/,
	'year'      : /^[0-9]{4}$/,
	'date'      : /^[12]\d{3}(0\d|1[0-2])([0-2]\d|3[01])$/,
	'cellphone' : /^\d{11}$/,
	'code'      :/(0[1-9]{2,3})/,
	'phone'     : /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/,
	'email'     : /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
	'money'     : /^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$/,
	'province'  : /^(shanghai)|(beijin)|(tianjin)|(chongqing)|(neimenggu)|(hebei)|(liaoning)|(jilin)|(heilongjiang)|(jiangsu)|(anhui)|(shandong)|(zhejiang)|(jiangxi)|(fujian)|(hunan)|(hubei)|(henan)|(guangdong)|(guangxi)|(guizhou)|(sichuan)|(yunnan)|(shanxi)|(gansu)|(ningxia)|(qinghai)|(xinjiang)|(xizang)|(hainan)|(shanxi)|(taiwan)|(xianggang)|(aomen)|(haiwai)$/};

var validateFormRunning = false;
function validateForm(containerId) {
	if (validateFormRunning) {return false;}
	
	validateFormRunning = true;
	
	var result = true;
	
	$("#" + containerId + " :input").each(function(i, e) {
		var val = $(e).val();

		if ( $(e).attr("nullableError") != null && ($(e).attr("nullable") == null || $(e).attr("nullable") == 'false') ) {
			var inputType = $(e).attr("type");
			 if (inputType === "radio") {
				 var key = $(e).attr("name");
				 val = $("#" + containerId + " :input[name='"+key+"']:checked").val();
			 }
			 if (inputType === "checkbox") {
				 var key = $(e).attr("name");
				 val = $("#" + containerId + " :input[name='"+key+"']:checked");
			 }
			if (val == null || val.length == 0) {
				layer.alert($(e).attr("nullableError"),{icon: 0});
				result = false;
				return false;
			}
		}
		
		if ($(e).attr("nullable") != 'true') {
			if ($(e).attr("_maxlength") != null && $(e).attr("maxlengthError") != null) {
				if (val.length > $(e).attr("_maxlength")) {
					layer.alert($(e).attr("maxlengthError"),{icon: 0});
					result = false;
					return false;
				}
			}
			
			if ($(e).attr("_minLength") != null && $(e).attr("minlengthError") != null) {
				if (val.length < $(e).attr("_minLength")) {
					layer.alert($(e).attr("minlengthError"),{icon: 0});
					result = false;
					return false;
				}
			}
			
			if ($(e).attr("_pattern") != null && $(e).attr("patternError") != null) {
				if ( ! eval($(e).attr("_pattern")).test(val) ) {
					layer.alert($(e).attr("patternError"),{icon: 0});
					result = false;
					return false;
				}
			}
			
			if ($(e).attr("dataType") != null && $(e).attr("dataTypeError") != null) {
				var dataTypeRegx = dataTypeRepository[$(e).attr("dataType")];
				if (dataTypeRegx != undefined) {
					if( ! dataTypeRegx.test(val) ) {
						layer.alert($(e).attr("dataTypeError"),{icon: 0});
						result = false;
						return false;
					}
				} else {
					result = false;
					return false;
				}
			}
		}
    });

	validateFormRunning = false;
	return result;
}