package com.library.system.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class SSLHttpClient {
	/**
	 * 娉ㄥ唽SSL杩炴帴
	 * @param hostname 璇锋眰鐨勪富鏈哄悕锛圛P鎴栬�呭煙鍚嶏級
	 * @param protocol 璇锋眰鍗忚鍚嶇О锛圱LS-瀹夊叏浼犺緭灞傚崗璁級
	 * @param port 绔彛鍙�
	 * @param scheme 鍗忚鍚嶇О
	 * @return HttpClient瀹炰緥
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public DefaultHttpClient registerSSL(String hostname,String protocol,int port,String scheme)throws NoSuchAlgorithmException, KeyManagementException {
		//鍒涘缓涓�涓粯璁ょ殑HttpClient
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//鍒涘缓SSL涓婁笅鏂囧疄渚�
		SSLContext ctx = SSLContext.getInstance(protocol);
		//鏈嶅姟绔瘉涔﹂獙璇�
		X509TrustManager tm = new X509TrustManager() {
		   /**
		    * 楠岃瘉瀹㈡埛绔瘉涔�
		    */
		   @Override
		   public void checkClientTrusted(X509Certificate[] chain,String authType)
			 throws java.security.cert.CertificateException {
			 //杩欓噷璺宠繃瀹㈡埛绔瘉涔�	楠岃瘉	
		   }

		   /**
		    * 楠岃瘉鏈嶅姟绔瘉涔�
		    * @param chain 璇佷功閾�
		    * @param authType 浣跨敤鐨勫瘑閽ヤ氦鎹㈢畻娉曪紝褰撲娇鐢ㄦ潵鑷湇鍔″櫒鐨勫瘑閽ユ椂authType涓篟SA
		    */
		   @Override
		   public void checkServerTrusted(X509Certificate[] chain,String authType)
			 throws java.security.cert.CertificateException {
			   if (chain == null || chain.length == 0)   
		           throw new IllegalArgumentException("null or zero-length certificate chain");   
		       if (authType == null || authType.length() == 0)   
		           throw new IllegalArgumentException("null or zero-length authentication type");   
		   
		       boolean br = false;   
		       Principal principal = null;   
		       for (X509Certificate x509Certificate : chain) {   
		           principal = x509Certificate.getSubjectX500Principal();   
		           if (principal != null) {
		               br = true;   
		               return;   
		           }   
		       }   
		       if (!br) {   
		          throw new CertificateException("鏈嶅姟绔瘉涔﹂獙璇佸け璐ワ紒");   
		       }   
		   }
		   /**
		    * 杩斿洖CA鍙戣鐨勮瘉涔�
		    */
		   @Override
		   public X509Certificate[] getAcceptedIssuers() {
			   return new X509Certificate[0];
		   }
		};
		//鍒濆鍖朣SL涓婁笅鏂�
		ctx.init(null, new TrustManager[]{tm}, new java.security.SecureRandom());
		//鍒涘缓SSL杩炴帴
		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme sch = new Scheme(scheme, port, socketFactory);
		//娉ㄥ唽SSL杩炴帴
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
		return httpclient;
	} 
}
