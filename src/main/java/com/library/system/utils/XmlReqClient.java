/**
 * @author Glan.duanyj
 * @date 2014-05-12
 * @project rest_demo
 */
package com.library.system.utils;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class XmlReqClient extends AbsRestClient {
    private static Logger logger = Logger.getLogger(XmlReqClient.class);


    @Override
    public String callback(String accountSid, String authToken, String appId,
                           String fromClient, String to, String fromSerNum, String toSerNum) {
        // TODO Auto-generated method stub
        String result = "";
        DefaultHttpClient httpclient = getDefaultHttpClient();
        try {
            //MD5鍔犲瘑
            EncryptUtil encryptUtil = new EncryptUtil();
            // 鏋勯�犺姹俇RL鍐呭
            String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 鏃堕棿鎴�
            String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
            String url = getStringBuffer().append("/").append(version)
                    .append("/Accounts/").append(accountSid)
                    .append("/Calls/callBack")
                    .append(".xml?sig=").append(signature).toString();
            String body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><callback>")
                    .append("<appId>").append(appId).append("</appId>")
                    .append("<fromClient>").append(fromClient).append("</fromClient>")
                    .append("<to>").append(to).append("</to>")
                    .append("<fromSerNum>").append(fromSerNum).append("</fromSerNum>")
                    .append("<toSerNum>").append(toSerNum).append("</toSerNum>")
                    .append("</callback>")).toString();
            HttpResponse response = post("application/xml", accountSid, authToken, timestamp, url, httpclient, encryptUtil, body);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 鍏抽棴杩炴帴
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }


    /**
     * 鐭俊鍙戦�佹帴鍙�
     * @param accountSid 娉ㄥ唽浜戜箣璁畼缃戯紝鍦ㄦ帶鍒跺彴涓嵆鍙幏鍙栨鍙傛暟
     * @param authToken 娉ㄥ唽浜戜箣璁畼缃戯紝鍦ㄦ帶鍒跺彴涓嵆鍙幏鍙栨鍙傛暟
     * @param appId 鍒涘缓搴旂敤鏃剁郴缁熷垎閰嶇殑鍞竴鏍囩ず锛屽湪鈥滃簲鐢ㄥ垪琛ㄢ�濅腑鍙互鏌ヨ
     * @param templateId 鍒涘缓鐭俊妯℃澘鏃剁郴缁熷垎閰嶇殑鍞竴鏍囩ず锛屽湪鈥滅煭淇＄鐞嗏�濅腑鍙互鏌ヨ
     * @param to 闇�瑕佷笅鍙戠煭淇＄殑鎵嬫満鍙风爜,鏀寔鍥介檯鍙风爜锛岄渶瑕佸姞鍥藉鐮�
     * @param param 闇�瑕佷笅鍙戠煭淇＄殑鎵嬫満鍙风爜,鏀寔鍥介檯鍙风爜锛岄渶瑕佸姞鍥藉鐮�
     * @return String
     * templateSMS
     */
    @Override
    public String templateSMS(String accountSid, String authToken,
                              String appId, String templateId, String to, String param) {
        // TODO Auto-generated method stub
        String result = "";
        DefaultHttpClient httpclient = getDefaultHttpClient();
        try {
            //MD5鍔犲瘑
            EncryptUtil encryptUtil = new EncryptUtil();
            // 鏋勯�犺姹俇RL鍐呭
            String timestamp = DateUtil.dateToStr(new Date(),
                    DateUtil.DATE_TIME_NO_SLASH);// 鏃堕棿鎴�
            String signature = getSignature(accountSid, authToken, timestamp, encryptUtil);
            String url = getStringBuffer().append("/").append(version)
                    .append("/Accounts/").append(accountSid)
                    .append("/Messages/templateSMS")
                    .append(".xml?sig=").append(signature).toString();
            String body = (new StringBuilder("<?xml version='1.0' encoding='utf-8'?><templateSMS>")
                    .append("<appId>").append(appId).append("</appId>")
                    .append("<templateId>").append(templateId).append("</templateId>")
                    .append("<to>").append(to).append("</to>")
                    .append("<param>").append(param).append("</param>")
                    .append("</templateSMS>")).toString();
            HttpResponse response = post("application/xml", accountSid, authToken, timestamp, url, httpclient, encryptUtil, body);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 鍏抽棴杩炴帴
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }
}
