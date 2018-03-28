/**
 * 
 * @date 2014-05-12
 * @project rest_demo
 */
package com.library.system.utils;

import java.io.ByteArrayInputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

public abstract class AbsRestClient {

    protected SysConfig config = SysConfig.getInstance();

    protected boolean isTest = config.getPropertyBoolean("is_test");
    protected String server  = config.getProperty("rest_server");
    protected String sslIP   = config.getProperty("http_ssl_ip");
    protected int sslPort    = config.getPropertyInt("http_ssl_port");
    protected String version = config.getProperty("version");

    private static Logger logger = Logger.getLogger(AbsRestClient.class);


    //==========================================鐭俊鍙戦�佹帴鍙� API===========================================

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
    public abstract String templateSMS(String accountSid, String authToken, String appId, String templateId, String to, String param);

    //==========================================鐭俊鍙戦�佹帴鍙� API===========================================

    /**
     * 鍙屽悜鍥炴嫧
     * @param accountSid 涓昏处鍙稩D
     * @param authToken 涓昏处鍙稵oken
     * @param appId 搴旂敤ID
     * @param fromClient 涓诲彨鐨刢lientNumber
     * @param to 琚彨鐨勫彿鐮�
     * @param toSerNum
     * @return String
     * callback
     */
    public abstract String callback(String accountSid, String authToken, String appId, String fromClient, String to, String fromSerNum, String toSerNum);



    /**
     * 璇锋眰鍦板潃
     * @return
     */
    public StringBuffer getStringBuffer() {
        if(!server.startsWith("https://")){
            return new StringBuffer("https://" + server);
        }
        return new StringBuffer(server);
    }

    /**
     * 鑾峰彇榛樿鐨刪ttpclient
     * @return httpclient
     */
    public DefaultHttpClient getDefaultHttpClient() {
        DefaultHttpClient httpclient = null;
        if (isTest) {
            try {
                SSLHttpClient chc = new SSLHttpClient();
                httpclient = chc.registerSSL(sslIP, "TLS", sslPort, "https");
                HttpParams hParams = new BasicHttpParams();
                hParams.setParameter("https.protocols", "SSLv3,SSLv2Hello");
                httpclient.setParams(hParams);
            } catch (KeyManagementException | NoSuchAlgorithmException e) {
                // TODO: handle exception
                logger.error(e);
            }
        } else {
            httpclient = new DefaultHttpClient();
        }
        return httpclient;
    }

    /**
     * 绛惧悕
     * @param accountSid 娉ㄥ唽浜戜箣璁畼缃戯紝鍦ㄦ帶鍒跺彴涓嵆鍙幏鍙栨鍙傛暟
     * @param authToken 璐︽埛鎺堟潈浠ょ墝
     * @param timestamp 鏃堕棿鎴�
     * @param encryptUtil 鍔犲瘑宸ュ叿绫�
     * @return signature 楠岃瘉鍙傛暟锛歁D5锛堣处鎴稩d + 璐︽埛鎺堟潈浠ょ墝 + 鏃堕棿鎴筹級锛屽叡32浣�(娉�:杞垚澶у啓)
     * @throws Exception
     */
    public String getSignature(String accountSid, String authToken, String timestamp, EncryptUtil encryptUtil) throws Exception {
        String sig = accountSid + authToken + timestamp;
        String signature = encryptUtil.md5Digest(sig);
        return signature;
    }

    /**
     * 鍙戦�乬et璇锋眰
     * @param cType锛氬鎴风鍝嶅簲鎺ユ敹鏁版嵁鏍煎紡锛歛pplication/xml銆乤pplication/json
     * @param accountSid 娉ㄥ唽浜戜箣璁畼缃戯紝鍦ㄦ帶鍒跺彴涓嵆鍙幏鍙栨鍙傛暟
     * @param authToken
     * @param timestamp 鏃堕棿鎴�
     * @param url 璇锋眰鍦板潃
     * @param httpclient http瀹㈡埛绔璞�
     * @param encryptUtil 鍔犲瘑宸ュ叿绫�
     * @return response 璇锋眰鍝嶅簲
     * @throws Exception
     */
    public HttpResponse get(String cType, String accountSid, String authToken, String timestamp, String url, DefaultHttpClient httpclient, EncryptUtil encryptUtil) throws Exception {
        HttpGet httppost = new HttpGet(url);
        httppost.setHeader("Accept", cType);//
        httppost.setHeader("Content-Type", cType + ";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httppost.setHeader("Authorization", auth);
        HttpResponse response = httpclient.execute(httppost);
        return response;
    }

    /**
     * 鍙戦�乸ost璇锋眰
     * @param cType锛氬鎴风鍝嶅簲鎺ユ敹鏁版嵁鏍煎紡锛歛pplication/xml銆乤pplication/json
     * @param accountSid 娉ㄥ唽浜戜箣璁畼缃戯紝鍦ㄦ帶鍒跺彴涓嵆鍙幏鍙栨鍙傛暟
     * @param authToken
     * @param timestamp 鏃堕棿鎴�
     * @param url 璇锋眰鍦板潃
     * @param httpclient http瀹㈡埛绔�
     * @param encryptUtil 鍔犲瘑宸ュ叿绫�
     * @param body 璇锋眰鍙傛暟
     * @return response 璇锋眰鍝嶅簲
     * @throws Exception
     */
    public HttpResponse post(String cType, String accountSid, String authToken, String timestamp, String url, DefaultHttpClient httpclient, EncryptUtil encryptUtil, String body) throws Exception {
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Accept", cType);
        httppost.setHeader("Content-Type", cType + ";charset=utf-8");
        String src = accountSid + ":" + timestamp;
        String auth = encryptUtil.base64Encoder(src);
        httppost.setHeader("Authorization", auth);
        BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 鎵ц瀹㈡埛绔姹�
        HttpResponse response = httpclient.execute(httppost);
        return response;
    }


}
