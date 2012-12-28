package cn.yang.sina.weibo.ui.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import weibo4android.http.BASE64Encoder;
/**
 * 
 * @author 艾志敏
 * 版本号：CATTSOFT 1.2.0
 * 日期：2009年3月8日
 * 功能：对一个字符串进行md5加密
 */
public class EncryptUtil {
	
	private static final char[] HEX_DIGITS ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	private static MessageDigest messageDigest =null;
	static{
		try{
			messageDigest=MessageDigest.getInstance("MD5");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
	}
	
	public static String getMD5String(String str){
		return getMD5String(str.getBytes());
	}
	private static String getMD5String(byte[] bytes) {
		messageDigest.update(bytes);
		return bytesToHex(messageDigest.digest());
	}
	private static String bytesToHex(byte[] bytes) {
		
		return bytesToHex(bytes,0,bytes.length);
	}
	private static String bytesToHex(byte[] bytes, int start, int length) {
		StringBuilder sb = new StringBuilder();
		for(int i = start;i<start+length;i++){
			sb.append(byteToHex(bytes[i]));
		}
		return sb.toString();
	}
	private static String byteToHex(byte bt) {
		// TODO Auto-generated method stub
		return HEX_DIGITS[(bt&0xf0)>>4]+""+HEX_DIGITS[bt&0xf];
	}
	/**
	 * 对字符串加密的方法
	 * @param str
	 * @return
	 */
	public static String toMessageDigest(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
}
