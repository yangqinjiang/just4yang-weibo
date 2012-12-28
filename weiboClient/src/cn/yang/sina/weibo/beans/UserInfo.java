package cn.yang.sina.weibo.beans;
/**
 * 用户信息，在登陆界面显示
 * @author Administrator
 *
 */
public class UserInfo
{
	/**
	 * userId,用户ID
	 */
	private String userId;
	/**
	 * userName,用户名称
	 */
	private String userName;
	/**
	 * userHeadPortrait,用户头像
	 */
	private String userHeadPortrait;
	/**
	 * token,从用户授权获得的Token
	 */
	private String token;
	/**
	 * tokenSecret,从用户授权获得的TokenSecret
	 */
	private String tokenSecret;
	public UserInfo(String userId, String userName, String userHeadPortrait,
			String token, String tokenSecret) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userHeadPortrait = userHeadPortrait;
		this.token = token;
		this.tokenSecret = tokenSecret;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserHeadPortrait() {
		return userHeadPortrait;
	}
	public void setUserHeadPortrait(String userHeadPortrait) {
		this.userHeadPortrait = userHeadPortrait;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenSecret() {
		return tokenSecret;
	}
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	} 
	
	

}
