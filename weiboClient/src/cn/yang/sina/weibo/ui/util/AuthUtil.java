package cn.yang.sina.weibo.ui.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weibo4android.Weibo;
import weibo4android.WeiboException;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;



/**
 * �û�OAuth��Ȩ������
 * @author Touch Android
 * http://bbs.droidstouch.com
 */
public class AuthUtil 
{

	
	
	private static Weibo weibo;
	private static RequestToken requestToken ;
	static String getAuthorizationURL=null;
	static
	{
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		
		weibo = new Weibo();
		weibo.setOAuthConsumer(Weibo.CONSUMER_KEY, Weibo.CONSUMER_SECRET);
		
	}
	
	public static String getRequestToken(){
		/*try {
			requestToken = weibo.getOAuthRequestToken();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String requsetStr = requestToken.getToken();
		System.out.println("requsetStr-->"+requsetStr);
		return requsetStr;
	}
	
	/**
	 * 
	 * @return String
	 */
	public static  String getAuthorizationURL()
	{
		try
		{
			requestToken = weibo.getOAuthRequestToken();
			
			return requestToken.getAuthorizationURL();
		} catch (WeiboException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static String readContentFromPost(String url, String token) throws IOException {
        URL postUrl = new URL(url);
        // ������
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        // �����Ƿ���connection�������Ϊ�����post���󣬲���Ҫ����http�����ڣ������Ҫ��Ϊtrue
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        // Post ������ʹ�û���
        connection.setUseCaches(false);

        // URLConnection.setInstanceFollowRedirects �ǳ�Ա����������ڵ�ǰ����
        connection.setInstanceFollowRedirects(true);
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        // ���ģ�����������ʵ��get��URL��'?'��Ĳ����ַ�һ��
        String content = "userId=" + "15976543860@139.com" + "&passwd=" + "yang19890617"
                         + "&oauth_callback=none" + "&action=submit" + "&from=" + "null"
                         + "&oauth_token=" + token;
        out.writeBytes(content);

        out.flush();
        out.close(); // flush and close
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection
            .getInputStream()));
        String line = "";
        String str = "";
        System.out.println("Contents of post request");
        while ((line = reader.readLine()) != null) {
            str += line;
        }
        System.out.println("Contents of post request ends");
        reader.close();
        connection.disconnect();
        return str;
    }
	/**
	 * ���PINֵ��ʹ��������ʽ
	 * @param url
	 * @param token
	 * @return
	 * @throws IOException
	 */
	public static String getPin(String url, String token) throws IOException {
        String html = readContentFromPost(url, token);
        System.out.println("html-->"+html);
		String reg="[0-9]{6}";
		Pattern pattern =Pattern.compile(reg);
		
		Matcher matcher =pattern.matcher(html);
		
        String pin = null;

		if(matcher.find())
		{
			pin=matcher.group(0);
		}
		System.out.println("getPin!!!!!!!!  " + pin);
        return pin;
    }
	
	
	/**
	 * 获取AccessToken信息
	 * @param pin
	 * @return
	 */
	public static AccessToken getAccessToken(String pin)
	{
		try
		{
			AccessToken accessToken =requestToken.getAccessToken(pin);
			
			System.out.println("Access token: "+ accessToken.getToken());
			System.out.println("Access token secret: "+ accessToken.getTokenSecret());
			System.out.println("Access userID: "+accessToken.getUserId());
			System.out.println("Access userName: "+accessToken.getScreenName());
			
			return accessToken;
		} catch (WeiboException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	
	
	
	
	
}
