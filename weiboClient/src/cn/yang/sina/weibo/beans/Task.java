package cn.yang.sina.weibo.beans;

import java.util.Map;

public class Task {
	public static boolean DEBUG = true;
	/**
	 * 任务ID
	 */
	private int taskId;
	/**
	 * 任务携带的参数
	 */
	private Map<String, Object> taskParams;
	/**
	 * weibo登陆
	 */
	public static final int WEIBO_LOGIN=1;
	/**
	 *  weibo取得AccessToken
	 */
	public static final int WEIBO_ACCESS_TOKEN=2;
	/**
	 * weibo获取授权信息
	 */
	public static final int WEIBO_AUTHORIZED_USE_URI=3;

	/**
	 * 获取当前登录用户及其所关注用户的最新微博  标识
	 */
	public static final int WEIBO_FRIENDS_TIMELINE=4;
	/**
	 * 从数据中查找出所有用户信息  标识
	 */
	public static final int LOGIN_SEARCH_FOR_ALL_USERS_FROM_DB=5;
	/**
	 * 获取授权URL 标识
	 */
	public static final int LOGIN_GET_AUTHENTICATION_URL=6;
	/**
	 * 获取官方表情  标识
	 */
	public static final int WEIBO_GET_EMOTIONS=7;
	/**获取更多friendTimeline的weibo*/
	public static final int LOAD_MORE_FRIEND_TIMELINE_WEIBO=8;
	/**刷新friendTimeline的weibo*/
	public static final int REFRESH_FRIEND_TIMELINE_WEIBO=9;
	/**发布一条新微博*/
	public static final int SEND_A_NEW_WEIBO=10;
	/**GET_ACCESS_TOKEN_OF_USER_FROM_DB从本地数据库中查找到用户的accessToken*/
	public static final int GET_ACCESS_TOKEN_OF_USER_FROM_DB=11;
	/**获取用户的关注列表 */
	public static final int WEIBO_FRIENDS=12;
	/**取消关注一个用户 */
	public static final int WEIBO_DESTROY_FRIEND=13;
	/**获取某个用户最新发表的微博列表 */
	public static final int WEIBO_STATUS_FROM_FRIEND =14;
	/**获取关注好友的好友列表*/
	public static final int WEIBO_FRIENDS_FROM_FRIEND=15;
	/**获取关注好友的粉丝列表*/
	public static final int WEIBO_FOLLOWERS_FROM_FRIEND=16;
	/**获取 更多 关注好友的好友列表*/
	public static final int WEIBO_MORE_FRIENDS_FROM_FRIEND=17;
	/**返回最新20条当前登录用户收到的评论*/
	public static final int WEIBO_GET_COMMENTS_TO_ME=18;
	/**获取最新的提到当前登录用户的评论，即@我的评论  */
	public static final int WEIBO_GET_MSG_AT_ME=19;
	/**收藏列表 */
	public static final int WEIBO_GET_FAVORITES=20;
	/**
	 * 进度条对话框使用的
	 * 标识：正在获取授权信息...
	 */
	public static final int GET_INFO_OF_AUTH =21;
	/**
	 * 进度条对话框使用的
	 * 标识：成功获取授权信息!
	 */
	public static final int SUCCEED_GET_INFO_OF_AUTH =22;
	/**
	 * 进度条对话框使用的
	 * 标识：正在获取用户信息...
	 */
	public static final int GET_INFO_OF_USER =23;
	/**
	 * 进度条对话框使用的
	 * 标识：正在数据库中更新用户信息...
	 */
	public static final int UPDATA_USER_INFO_FROM_DB =24;
	/**
	 * 进度条对话框使用的
	 * 将进度条对话框取消
	 */
	private static final int PORGRESS_DIALOG_DISMISS=26;
	/**
	 * 进度条对话框使用的
	 * 标识：正在往数据库中插入并保存用户信息...
	 */
	public static final int SAVE_USER_INFO_TO_DB = 25;
	/**
	 * 进度条对话框使用的
	 * 标识：显示用户信息在这个"OAuthAccessTokenAct"Activity中
	 */
	public static final int SHOW_USER_INFO=26;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public Map<String, Object> getTaskParams() {
		return taskParams;
	}
	public void setTaskParams(Map<String, Object> taskParams) {
		this.taskParams = taskParams;
	}
	public Task(int taskId, Map<String, Object> taskParams) {
		super();
		this.taskId = taskId;
		this.taskParams = taskParams;
	}
	
	
}
