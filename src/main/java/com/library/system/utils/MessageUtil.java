package com.library.system.utils;
import java.io.IOException;
import java.util.Random;



import net.sf.json.JSONObject;
// 短信工具类 bylisujie
public class MessageUtil {
   
	private String accountSid;
	private String authToken;
	
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	static AbsRestClient InstantiationRestAPI(boolean enable) {
		if(enable) {
			return new JsonReqClient();
		} else {
			return new XmlReqClient();
		}
	}

	public static String testTemplateSMS(boolean json,String accountSid,String authToken,String appId,String templateId,String to,String param){
		  String result = null;
		try {
		    result=InstantiationRestAPI(json).templateSMS(accountSid, authToken, appId, templateId, to, param);
			
			System.out.println(result);
		} catch (Exception e) {
			 result="ERROR";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 测试说明 参数顺序，请参照各具体方法参数名称
	 * 参数名称含义，请参考rest api 文档
	 * @author Glan.duanyj
	 * @date 2014-06-30
	 * @param params[]
	 * @return void
	 * @throws IOException 
	 * @method main
	 */
	/*  public static void main(String[] args) throws IOException {

		String accountSid="9aca09a3bee9c1aedb6188687c6c7312";
		String token="9604d5fdd84ceaf2c5dabb81d1d7c165";
		String appId="5a30b0c66f2642f898d7b07ec5a62961";
		String templateId="235301";
		String to="13129981591";
		String para="3340";
		testTemplateSMS(true, accountSid,token,appId, templateId,to,para);//短信发送接口
	}*/
	
	public static JsonResult getMessage(String phoneNumber){
		JsonResult jr=new JsonResult();
		String accountSid="9aca09a3bee9c1aedb6188687c6c7312";
		String token="9604d5fdd84ceaf2c5dabb81d1d7c165";
		String appId="5a30b0c66f2642f898d7b07ec5a62961";
		String templateId="235301";
		String to=phoneNumber;  //前台接收的电话号码
		String para=String.valueOf((new Random().nextInt(899999) + 100000));//验证码 
		String jsonStr=testTemplateSMS(true, accountSid,token,appId, templateId,to,para);//短信发送接口
		if(!jsonStr.equals("ERROR")){
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		    String failure=	jsonObject.getJSONObject("resp").getString("failure");
		    if(failure.equals("0")){  //发送成功 .
		    	jr.setMsg(para); 
		    	jr.setResult(true); 
		    	
		    }else{  //发送失败  	
		    	jr.setMsg("FAIL");
		    	jr.setResult(false);
		    }
		}else{ //发送异常
			jr.setResult(false);
			jr.setMsg(jsonStr);
		}
		
		return jr;
	}
	
}
