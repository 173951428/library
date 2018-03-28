package com.library.system.utils;
/**
 * 
 * 返回json
 * @author Administrator
 * 
 */
public class JsonResult {
	private boolean result;//结果
	private String msg;//结果提示信息
	private String content;//结果内容
	
	public JsonResult(){}
	
	public JsonResult(boolean result, String msg, String content) {
		this.result = result;
		this.msg = msg;
		this.content = content;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public boolean getResult(){
		return result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
