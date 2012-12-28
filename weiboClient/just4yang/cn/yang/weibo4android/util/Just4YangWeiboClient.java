package cn.yang.weibo4android.util;

import java.io.IOException;
import java.net.MalformedURLException;
import weibo4android.Paging;
import android.util.Log;
import cn.yang.sina.weibo.beans.Statuses;
import cn.yang.weibo4android.Comment;
import cn.yang.weibo4android.Comments;
import cn.yang.weibo4android.Favorite;
import cn.yang.weibo4android.Favorites;
import cn.yang.weibo4android.Status;
import cn.yang.weibo4android.User;
import cn.yang.weibo4android.Users;
import cn.yang.weibo4android.WeiboContent;

import com.weibo.net.Weibo;
/**
 * OAuth2.0  WeiboClient
 * @author yangqinjiang17
 *
 */
public class Just4YangWeiboClient {

	private   static String TAG = "Just4YangWeiboClient";
	
	/**
	 * TODO 20120811
	 * 返回好友主页时间线
	 * @param weibo
	 * @param paging
	 * @return  Statuses
	 * @throws MalformedURLException
	 * @throws IOException
	 */
    public static  Statuses getFriendsTimeline(Weibo weibo,Paging paging) throws MalformedURLException, IOException{
    	Log.i(TAG, "getFriendsTimeline running...--3");
    	WeiboGetter<Statuses> sd = new FriendsTimeline<Statuses>(weibo,Statuses.class, paging);
    	
    	return sd.getData();
    }
    
    
    
   
    /**TODO 20120814
     * 获取用户的关注列表 
     * @param weibo
     * @param paging
     */
    public static Users getFriendLists(Weibo weibo,
    		String uid,Paging paging){
    	WeiboGetter<Users> wg = new FriendsList<Users>(weibo, Users.class, uid, paging);
    	return wg.getData();
    }
    
    
    /**TODO 20120814
     * 获取用户的关注列表 
     * @param weibo
     * @param paging
     */
    public static Users getFollowerLists(Weibo weibo,String uid,Paging paging){
    	WeiboGetter<Users> wg = new FollowersList<Users>(weibo, Users.class, uid, paging);
    	return wg.getData();
    }

    /**
     * 获取某个用户最新发表的微博列表
     * @param weibo
     * @param paging
     * @return
     */
    public static Statuses getUserTimeLine(Weibo weibo,String uid,Paging paging){

    	WeiboGetter<Statuses> sd=new UserTimeline<Statuses>(weibo, Statuses.class, paging, uid);
    
    	return sd.getData();
    }
    
    /**
     * 获取最新的提到登录用户的微博列表，即@我的微博
     * @param weibo
     * @param paging
     * @return
     */
    public static Statuses getMentions(Weibo weibo,Paging paging){
    	Log.i(TAG, "getMentions running...");
    	WeiboGetter<Statuses> wg = new MentionLists<Statuses>(weibo, Statuses.class, paging);
    	return wg.getData();
    }
    
    /**TODO 20120814
     * 根据用户ID获取用户信息
     * @param weibo
     * @param userId
     */
    public static User getUserProfile(Weibo weibo,String uid){
    	if(null==uid)
    		throw new RuntimeException("the uid must not null");
    	WeiboGetter<User> wg = new UserProfile<User>(weibo,User.class,uid);
    	return wg.getData();
    }
    /**
     * 转发一条微博
     * @param weibo
     * @param weiboContent
     * @return
     */
    public static Status repost(Weibo weibo,WeiboContent weiboContent){
    	
    	Log.i(TAG, "repost running...");
    	WeiboGetter<Status> wg = new Repost<Status>(weibo, Status.class, weiboContent);
    	return wg.getData();
    }
    /**
     * 评论
     * @param weibo
     * @param weiboContent
     * @return
     */
    public static Comment createComment(Weibo weibo,WeiboContent weiboContent){
    	Log.i(TAG, "createComment running...");
    	WeiboGetter<Comment> wg= new CreateComment<Comment>(weibo, Comment.class, weiboContent);
    	return wg.getData();
    	}
    
 
    
    /**TODO 20120814
     * 获取当前登录用户所接收到的评论列表 
     * @param weibo
     */
    public static Comments getCommentsToMe(Weibo weibo,Paging paging){
    	WeiboGetter<Comments> wg = new CommentToMe<Comments>(weibo, Comments.class, paging);
    	return wg.getData();
    }
    
    /**
     * 获取收藏微博列表
     * @param weibo
     * @param paging
     * @return
     */
    public static Favorites getFavorites(Weibo weibo,Paging paging){
    	Log.i(TAG, "getFavorites running...");
    	WeiboGetter<Favorites> wg = new FavoriteLists<Favorites>(weibo, Favorites.class, paging);
    	return wg.getData();
    }

	/**
	 * 收藏某条微博
	 * @param weibo
	 * @param content
	 * @return
	 */
	public static Favorite createFavorite(Weibo weibo,WeiboContent content){
		Log.i(TAG, "createFavorite running...");
		WeiboGetter<Favorite> wg = new CreateFavorite<Favorite>(weibo, Favorite.class, content);
		return wg.getData();
	}
	/**
	 * 发送一条微博
	 * @param weibo
	 * @param content
	 * @return
	 */
	public static Status sendWeibo(Weibo weibo,WeiboContent content){
		Log.i(TAG, "sendWeibo running...");
    	WeiboGetter<Status> wg = new SendWeibo<Status>(weibo, Status.class, content);
		return wg.getData();
	}
}
