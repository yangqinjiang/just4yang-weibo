package cn.yang.weibo4android;

import cn.yang.sina.weibo.beans.nullable.Nullable;
import cn.yang.weibo4android.Status;

public class User implements java.io.Serializable ,Nullable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3473349966713163765L;
	static final String[] POSSIBLE_ROOT_NAMES = new String[]{"user", "sender", "recipient", "retweeting_user"};
	
	private long id;                      //用户id
	private String idstr;                 //idstr
	private String screen_name;            //微博昵称
	private String name;                  //友好显示名称，如Bill Gates(此特性暂不支持)
	private int province;                 //省份编码（参考省份编码表）
	private int city;                     //城市编码（参考城市编码表）
	private String location;              //地址
	private String description;           //个人描述
	private String url;                   //用户博客地址
	private String profile_image_url;       //自定义图像
	private String profile_url;
	private String domain;            //用户个性化URL
	private String weihao;
	private String gender;                //性别,m--男，f--女,n--未知
	private int followers_count;           //粉丝数
	private int friends_count;             //关注数
	private int statuses_count;            //微博数
	private int favourites_count;          //收藏数
	private String created_at;               //创建时间
	private boolean following;            //保留字段,是否已关注(此特性暂不支持)
	private boolean allow_all_act_msg;       //保留字段（暂时不支持）
	private boolean geo_enabled;           //地理
	private boolean verified;             //加V标示，是否微博认证用户

	private int verified_type;
	private boolean allow_all_comment;
	private String avatar_large;
	private String verified_reason;
	private boolean follow_me;
	private int online_status;
	private int bi_followers_count;
	private String lang;
	
	private Status status = null;

	
	public User(){
	}
	public User(long id,String screen_name){
		this.id=id;
		this.screen_name=screen_name;
	}
	
	public User(long id, String idstr, String screen_name, String name,
			int province, int city, String location, String description,
			String url, String profile_image_url, String profile_url,
			String domain, String weihao, String gender, int followers_count,
			int friends_count, int statuses_count, int favourites_count,
			String created_at, boolean following, boolean allow_all_act_msg,
			boolean geo_enabled, boolean verified, int verified_type,
			boolean allow_all_comment, String avatar_large,
			String verified_reason, boolean follow_me, int online_status,
			int bi_followers_count, String lang, Status status) {
		super();
		this.id = id;
		this.idstr = idstr;
		this.screen_name = screen_name;
		this.name = name;
		this.province = province;
		this.city = city;
		this.location = location;
		this.description = description;
		this.url = url;
		this.profile_image_url = profile_image_url;
		this.profile_url = profile_url;
		this.domain = domain;
		this.weihao = weihao;
		this.gender = gender;
		this.followers_count = followers_count;
		this.friends_count = friends_count;
		this.statuses_count = statuses_count;
		this.favourites_count = favourites_count;
		this.created_at = created_at;
		this.following = following;
		this.allow_all_act_msg = allow_all_act_msg;
		this.geo_enabled = geo_enabled;
		this.verified = verified;
		this.verified_type = verified_type;
		this.allow_all_comment = allow_all_comment;
		this.avatar_large = avatar_large;
		this.verified_reason = verified_reason;
		this.follow_me = follow_me;
		this.online_status = online_status;
		this.bi_followers_count = bi_followers_count;
		this.lang = lang;
		this.status = status;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWeihao() {
		return weihao;
	}

	public void setWeihao(String weihao) {
		this.weihao = weihao;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}

	public int getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}

	public int getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(int statuses_count) {
		this.statuses_count = statuses_count;
	}

	public int getFavourites_count() {
		return favourites_count;
	}

	public void setFavourites_count(int favourites_count) {
		this.favourites_count = favourites_count;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public boolean isFollowing() {
		return following;
	}

	public void setFollowing(boolean following) {
		this.following = following;
	}

	public boolean isAllow_all_act_msg() {
		return allow_all_act_msg;
	}

	public void setAllow_all_act_msg(boolean allow_all_act_msg) {
		this.allow_all_act_msg = allow_all_act_msg;
	}

	public boolean isGeo_enabled() {
		return geo_enabled;
	}

	public void setGeo_enabled(boolean geo_enabled) {
		this.geo_enabled = geo_enabled;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public int getVerified_type() {
		return verified_type;
	}

	public void setVerified_type(int verified_type) {
		this.verified_type = verified_type;
	}

	public boolean isAllow_all_comment() {
		return allow_all_comment;
	}

	public void setAllow_all_comment(boolean allow_all_comment) {
		this.allow_all_comment = allow_all_comment;
	}

	public String getAvatar_large() {
		return avatar_large;
	}

	public void setAvatar_large(String avatar_large) {
		this.avatar_large = avatar_large;
	}

	public String getVerified_reason() {
		return verified_reason;
	}

	public void setVerified_reason(String verified_reason) {
		this.verified_reason = verified_reason;
	}

	public boolean isFollow_me() {
		return follow_me;
	}

	public void setFollow_me(boolean follow_me) {
		this.follow_me = follow_me;
	}

	public int getOnline_status() {
		return online_status;
	}

	public void setOnline_status(int online_status) {
		this.online_status = online_status;
	}

	public int getBi_followers_count() {
		return bi_followers_count;
	}

	public void setBi_followers_count(int bi_followers_count) {
		this.bi_followers_count = bi_followers_count;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Status getStatus() {
		if(this.status==null)return Status.NULL;
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String[] getPossibleRootNames() {
		return POSSIBLE_ROOT_NAMES;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", idstr=" + idstr + ", screen_name="
				+ screen_name + ", name=" + name + ", province=" + province
				+ ", city=" + city + ", location=" + location
				+ ", description=" + description + ", url=" + url
				+ ", profile_image_url=" + profile_image_url + ", profile_url="
				+ profile_url + ", domain=" + domain + ", weihao=" + weihao
				+ ", gender=" + gender + ", followers_count=" + followers_count
				+ ", friends_count=" + friends_count + ", statuses_count="
				+ statuses_count + ", favourites_count=" + favourites_count
				+ ", created_at=" + created_at + ", following=" + following
				+ ", allow_all_act_msg=" + allow_all_act_msg + ", geo_enabled="
				+ geo_enabled + ", verified=" + verified + ", verified_type="
				+ verified_type + ", allow_all_comment=" + allow_all_comment
				+ ", avatar_large=" + avatar_large + ", verified_reason="
				+ verified_reason + ", follow_me=" + follow_me
				+ ", online_status=" + online_status + ", bi_followers_count="
				+ bi_followers_count + ", lang=" + lang + ", status=" + status
				+ "]";
	}
	
	public static User NULL=new NullUser();
	
	public static class NullUser extends User implements Nullable{

		
		
		private static final long serialVersionUID = -2846675831914035180L;

		private NullUser(){
			super(0, "0", "None", "None", 0, 0, "None", "None", "None", "None", "None",
					"None", "None", "None", 0, 0, 0, 0, "None", false, false, false, false,
					0, 	false, "http://tp2.sinaimg.cn/1792862597/180/5643071877/1","None",false,0,0,"None",Status.NULL);
		}
		@Override
		public String toString() {
			return "NullUser";
		}
		@Override
		public boolean isNull() {
			return true;
		}
		
	}


	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	
	
}
