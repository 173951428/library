package com.library.system.utils;
import java.net.InetAddress;
import java.util.UUID;
/**
 * 主键生成
 * @author 
 *
 */
public final class UUIDGenerator {
	private UUIDGenerator(){}
	
	/**
	 * 获取Java自带的UUID
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}
	
	/**
	 * 生成20位长度的UUID
	 * @return
	 */
	public static String generate20UUID(){
		return new StringBuilder(20)
                    .append(UUIDHelper.format(UUIDHelper.getIP()))
                    .append(UUIDHelper.format(UUIDHelper.getLoTime()))
                    .append(UUIDHelper.format(UUIDHelper.getCount()))
                    .toString();
    }
	
	/**
	 * 生成32位长度的UUID
	 * @return
	 */
	public static String generate32UUID(){
		return new StringBuilder(32)
				.append(UUIDHelper.format(UUIDHelper.getIP()))
				.append(UUIDHelper.format(UUIDHelper.getJVM()))
				.append(UUIDHelper.format(UUIDHelper.getHiTime()))
				.append(UUIDHelper.format(UUIDHelper.getLoTime()))
				.append(UUIDHelper.format(UUIDHelper.getCount()))
				.toString();
	}
	
	/**
	 * 生成36位长度的UUID
	 * @return
	 */
    public static String generate36UUID(){
         return new StringBuilder(36)
           .append(UUIDHelper.format(UUIDHelper.getIP())).append("-")
           .append(UUIDHelper.format(UUIDHelper.getJVM())).append("-")
           .append(UUIDHelper.format(UUIDHelper.getHiTime())).append("-")
           .append(UUIDHelper.format(UUIDHelper.getLoTime())).append("-")
           .append(UUIDHelper.format(UUIDHelper.getCount()))
           .toString();
    }
    
	/**
	 * 帮助类
	 * @Description: TODO
	 * @author zuohongda
	 * @Company Newtouch
	 * @since 2016年4月1日 上午11:26:39 
	 * @version V0.1
	 */
    private static final class UUIDHelper{
    	private UUIDHelper(){
    		
    	}
        private static final int IP;
        private static short counter = (short) 0;
        static{
        	int ipAdd;
            try{
            	ipAdd = ip2Int(InetAddress.getLocalHost().getAddress());
            }catch(Exception e){
            	ipAdd = 0;
            }
            IP = ipAdd;
        }
        private final static int ip2Int(byte []bytes){
        	int result = 0;
        	for (int i = 0; i < 4; i++) {   
        		result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];   
        	}   
        	return result;   
        }
        
        private static short getCount(){
            synchronized(UUIDHelper.class){
                if(counter < 0)
                        counter = 0;
                return counter++;
            }
        }
        
        private static int getIP(){
        	return IP;
        }
        
        private static int getJVM(){
        	return (int)(System.currentTimeMillis()>>>8);
        }
        
        private static short getHiTime(){
        	return (short)(System.currentTimeMillis() >>> 32);
        }
        
        private static int getLoTime(){   
        	return (int) System.currentTimeMillis();   
        }
        
        private static String format(short shortval){
            String formatted = Integer.toHexString(shortval);
            StringBuffer buf = new StringBuffer("0000");
            buf.replace( 4-formatted.length(), 4, formatted );
            return buf.toString();
        }
        
        private static String format(int intval){
            String formatted = Integer.toHexString(intval);
            StringBuffer buf = new StringBuffer("00000000");
            buf.replace(8 - formatted.length(), 8, formatted);
            return buf.toString();
        }
    }
}
