package cn.yang.sina.weibo.beans;

import weibo4android.Weibo;
import weibo4android.http.AccessToken;
import weibo4android.http.RequestToken;

public class OAuthConstant
{
    public static final String CALLBACK_URL = "wuyexionglovejing";
    private static Weibo weibo = null;
    private static OAuthConstant instance = null;
    private RequestToken requestToken;
    private AccessToken accessToken;
    private String token;
    private String tokenSecret;
    public static final String CONSUMER_KEY = "31303245";
	public static final String CONSUMER_SECRET = "4ba67d2ea28d50dd10cf47f2628ef8fe";

    private OAuthConstant()
    {
    };

    public static synchronized OAuthConstant getInstance()
    {
        if (instance == null)
            instance = new OAuthConstant();
        return instance;
    }

    public Weibo getWeibo()
    {
        if (weibo == null)
            weibo = new Weibo();
        return weibo;
    }

    public AccessToken getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken)
    {
        this.accessToken = accessToken;
        this.token = accessToken.getToken();
        this.tokenSecret = accessToken.getTokenSecret();
    }

    public RequestToken getRequestToken()
    {
        return requestToken;
    }

    public void setRequestToken(RequestToken requestToken)
    {
        this.requestToken = requestToken;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getTokenSecret()
    {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret)
    {
        this.tokenSecret = tokenSecret;
    }
}
