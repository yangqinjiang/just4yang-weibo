/*
Copyright (c) 2007-2009, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package weibo4android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.graphics.drawable.Drawable;
import android.util.Log;

import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

/**
 * A data class representing Basic user information element
 */
public class User extends WeiboResponse implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3473349966713163765L;
	static final String[] POSSIBLE_ROOT_NAMES = new String[]{"user", "sender", "recipient", "retweeting_user"};
	private Weibo weibo;
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
	private Date created_at;               //创建时间
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
	/**
	 * 用户头像
	 * By  yangqinjiang 
	 * weibo:http://weibo.com/yangqinjiang
	 */
	//private Drawable  userIcon;			//用户头像
	private Status status = null;

	
	public User(long id,String name,Drawable  userIcon){
		this.id=id;
		this.name=name;
		//this.userIcon=userIcon;
	}
	public User(long id,String name){
		this.id=id;
		this.name=name;
	}
	/*package*/User(Response res, Weibo weibo) throws WeiboException {
		super(res);
		Element elem = res.asDocument().getDocumentElement();
		init(res, elem, weibo);
	}

	/*package*/User(Response res, Element elem, Weibo weibo) throws WeiboException {
		super(res);
		init(res, elem, weibo);
	}
	/*package*/User(JSONObject json) throws WeiboException {
		super();
		init(json);
	}

	private void init(JSONObject json) throws WeiboException {
		if(json!=null){
			try {
				id = json.getLong("id");
				name = json.getString("name");
				screen_name = json.getString("screen_name");
				location = json.getString("location");
				description = json.getString("description");
				profile_image_url = json.getString("profile_image_url");
				url = json.getString("url");
				allow_all_act_msg = json.getBoolean("allow_all_act_msg");
				followers_count = json.getInt("followers_count");
				friends_count = json.getInt("friends_count");
				created_at = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
				favourites_count = json.getInt("favourites_count");
				following = getBoolean("following", json);
				verified=getBoolean("verified", json);
				statuses_count = json.getInt("statuses_count");
				domain = json.getString("domain");
				gender = json.getString("gender");
				province = json.getInt("province");
				city = json.getInt("city");
				if (!json.isNull("status")) {
					setStatus(new Status(json.getJSONObject("status")));
				}
			} catch (JSONException jsone) {
				throw new WeiboException(jsone.getMessage() + ":" + json.toString(), jsone);
			}
		}
	}

	private void init(Response res,Element elem, Weibo weibo) throws WeiboException {
		this.weibo = weibo;
		ensureRootNodeNameIs(POSSIBLE_ROOT_NAMES, elem);
		id = getChildLong("id", elem);
		name = getChildText("name", elem);
		screen_name = getChildText("screen_name", elem);
		location = getChildText("location", elem);
		description = getChildText("description", elem);
		profile_image_url = getChildText("profile_image_url", elem);
		url = getChildText("url", elem);
		allow_all_act_msg = getChildBoolean("allow_all_act_msg", elem);
		followers_count = getChildInt("followers_count", elem);
		friends_count = getChildInt("friends_count", elem);
		created_at = getChildDate("created_at", elem);
		favourites_count = getChildInt("favourites_count", elem);
		following = getChildBoolean("following", elem);
		statuses_count = getChildInt("statuses_count", elem);
		geo_enabled = getChildBoolean("geo_enabled", elem);
		verified = getChildBoolean("verified", elem);
		domain = getChildText("domain", elem);
		gender = getChildText("gender", elem);
		province = getChildInt("province", elem);
		city = getChildInt("city", elem);
		status = new Status(res, (Element)elem.getElementsByTagName("status").item(0)
				, weibo);
	}

	/**
	 * Returns the id of the user
	 *
	 * @return the id of the user
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns the name of the user
	 *
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the screen name of the user
	 *
	 * @return the screen name of the user
	 */
	public String getScreenName() {
		return screen_name;
	}

	/**
	 * Returns the location of the user
	 *
	 * @return the location of the user
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Returns the description of the user
	 *
	 * @return the description of the user
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the profile image url of the user
	 *
	 * @return the profile image url of the user
	 */
	public URL getProfileImageURL() {
		try {
			return new URL(profile_image_url);
		} catch (MalformedURLException ex) {
			return null;
		}
	}

	/**
	 * Returns the url of the user
	 *
	 * @return the url of the user
	 */
	public URL getURL() {
		try {
			return new URL(url);
		} catch (MalformedURLException ex) {
			return null;
		}
	}

	/**
	 * Test if the user status is protected
	 *
	 * @return true if the user status is protected
	 */
	public boolean isAllowAllActMsg() {
		return allow_all_act_msg;
	}


	public String getUserDomain() {
		return domain;
	}

	/**
	 * Returns the number of followers
	 *
	 * @return the number of followers
	 * @since Weibo4J 1.2.1
	 */
	public int getFollowersCount() {
		return followers_count;
	}

	/**
	 * Returns the code of province
	 *
	 * @return the code of province
	 * @since Weibo4J 1.2.1
	 */
	public int getProvince() {
		return province;
	}

	/**
	 * Returns the code of city
	 *
	 * @return the code of city
	 * @since Weibo4J 1.2.1
	 */
	public int getCity() {
		return city;
	}

	public static List<User> constructUser(Response res)throws WeiboException{

		JSONObject json=res.asJSONObject();
		try {
			//			int next_cursor = json.getInt("next_cursor");
			//			int previous_cursor = json.getInt("previous_cursor");



			JSONArray list = json.getJSONArray("users");
			int size=list.length();
			List<User> users=new ArrayList<User>(size);
			for(int i=0;i<size;i++){
				users.add(new User(list.getJSONObject(i)));
			}
			return users;


		}
		catch (JSONException je) {
			throw new WeiboException(je);
		}

	}

	public static List<User> constructUsers(Response res, Weibo weibo) throws WeiboException {
		Document doc = res.asDocument();
		if (isRootNodeNilClasses(doc)) {
			return new ArrayList<User>(0);
		} else {
			try {
				ensureRootNodeNameIs("users", doc);
				//                NodeList list = doc.getDocumentElement().getElementsByTagName(
				//                        "user");
				//                int size = list.getLength();
				//                List<User> users = new ArrayList<User>(size);
				//                for (int i = 0; i < size; i++) {
				//                    users.add(new User(res, (Element) list.item(i), weibo));
				//                }

				//去除掉嵌套的bug
				NodeList list=doc.getDocumentElement().getChildNodes();
				List<User> users = new ArrayList<User>(list.getLength());
				Node node;
				for(int i=0;i<list.getLength();i++){
					node=list.item(i);
					if(node.getNodeName().equals("user")){
						users.add(new User(res, (Element) node, weibo));
					}
				}

				return users;
			} catch (WeiboException te) {
				if (isRootNodeNilClasses(doc)) {
					return new ArrayList<User>(0);
				} else {
					throw te;
				}
			}
		}
	}

	public static UserWapper constructWapperUsers(Response res, Weibo weibo) throws WeiboException {
		Document doc = res.asDocument();
		if (isRootNodeNilClasses(doc)) {
			return new UserWapper(new ArrayList<User>(0), 0, 0);
		} else {
			try {
				ensureRootNodeNameIs("users_list", doc);
				Element root = doc.getDocumentElement();
				NodeList user = root.getElementsByTagName("users");
				int length = user.getLength();
				if (length == 0) {
					return new UserWapper(new ArrayList<User>(0), 0, 0);
				}
				// 
				Element listsRoot = (Element) user.item(0);
				NodeList list=listsRoot.getChildNodes();
				List<User> users = new ArrayList<User>(list.getLength());
				Node node;
				for(int i=0;i<list.getLength();i++){
					node=list.item(i);
					if(node.getNodeName().equals("user")){
						users.add(new User(res, (Element) node, weibo));
					}
				}
				//
				long previousCursor = getChildLong("previous_curosr", root);
				long nextCursor = getChildLong("next_curosr", root);
				if (nextCursor == -1) { // 兼容不同标签名称
					nextCursor = getChildLong("nextCurosr", root);
				}
				return new UserWapper(users, previousCursor, nextCursor);
			} catch (WeiboException te) {
				if (isRootNodeNilClasses(doc)) {
					return new UserWapper(new ArrayList<User>(0), 0, 0);
				} else {
					throw te;
				}
			}
		}
	}


	public static List<User> constructUsers(Response res) throws WeiboException {
		try {
			JSONArray list = res.asJSONArray();
			int size = list.length();
			List<User> users = new ArrayList<User>(size);
			for (int i = 0; i < size; i++) {
				users.add(new User(list.getJSONObject(i)));
			}
			
			return users;
		} catch (JSONException jsone) {
			throw new WeiboException(jsone);
		} catch (WeiboException te) {
			throw te;
		}  
	}

	/**
	 * 
	 * @param res
	 * @return
	 * @throws WeiboException
	 */
	public static UserWapper constructWapperUsers(Response res) throws WeiboException {
		JSONObject jsonUsers = res.asJSONObject(); //asJSONArray();
		try {
			JSONArray user = jsonUsers.getJSONArray("users");
			int size = user.length();
			List<User> users = new ArrayList<User>(size);
			for (int i = 0; i < size; i++) {
				users.add(new User(user.getJSONObject(i)));
			}
			long previousCursor = jsonUsers.getLong("previous_curosr");
			long nextCursor = jsonUsers.getLong("next_cursor");
			Log.i("WEIBO-DEBUG:", "previousCursor "+previousCursor+
							" nextCursor "+nextCursor);
			if (nextCursor == -1) { // 兼容不同标签名称
				nextCursor = jsonUsers.getLong("nextCursor");
			}
			return new UserWapper(users, previousCursor, nextCursor);
		} catch (JSONException jsone) {
			throw new WeiboException(jsone);
		}
	}

	/**
	 * @param res 
	 * @return 
	 * @throws WeiboException
	 */
	static List<User> constructResult(Response res) throws WeiboException {
		JSONArray list = res.asJSONArray();
		try {
			int size = list.length();
			List<User> users = new ArrayList<User>(size);
			for (int i = 0; i < size; i++) {
				users.add(new User(list.getJSONObject(i)));
			}
			return users;
		} catch (JSONException e) {
		}
		return null;
	}

	public int getFriendsCount() {
		return friends_count;
	}

	public Date getCreatedAt() {
		return created_at;
	}

	public int getFavouritesCount() {
		return favourites_count;
	}

	public String getGender() {
		return gender;
	}

	
/*	public Drawable getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(Drawable userIcon) {
		this.userIcon = userIcon;
	}*/

	/**
	 *
	 * @deprecated
	 */
	@Deprecated
	public boolean isFollowing() {
		return following;
	}

	public int getStatusesCount() {
		return statuses_count;
	}

	/**
	 * @return the user is enabling geo location
	 * @since Weibo4J 1.2.1
	 */
	public boolean isGeoEnabled() {
		return geo_enabled;
	}

	/**
	 * @return returns true if the user is a verified celebrity
	 * @since Weibo4J 1.2.1
	 */
	public boolean isVerified() {
		return verified;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
		"weibo=" + weibo +
		", id=" + id +
		", name='" + name + '\'' +
		", screenName='" + screen_name + '\'' +
		", location='" + location + '\'' +
		", description='" + description + '\'' +
		", profileImageUrl='" + profile_image_url + '\'' +
		", province='" + province +'\'' +
		", city='" +city +'\''+
		", domain ='" + domain+ '\'' +
		", gender ='" + gender + '\'' +
		", url='" + url + '\'' +
		", allowAllActMsg=" + allow_all_act_msg +
		", followersCount=" + followers_count +
		", friendsCount=" + friends_count +
		", createdAt=" + created_at +
		", favouritesCount=" + favourites_count +
		", following=" + following +
		", statusesCount=" + statuses_count +
		", geoEnabled=" + geo_enabled +
		", verified=" + verified +
		", status=" + status +
		/*",userIcon="+userIcon+*/
		'}';
	}

}
