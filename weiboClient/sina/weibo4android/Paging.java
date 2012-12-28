
package weibo4android;

import cn.yang.sina.weibo.beans.nullable.Nullable;

public class Paging implements java.io.Serializable,Nullable {
    private int page = 1;
    private int count = 5;
    private long sinceId = 0;
    private long maxId = 0;
    private int cursor=0;
    private static final long serialVersionUID = -3285857427993796670L;

    
    public Paging() {}

  
	public Paging(int page) {
        setPage(page);
    }

    public Paging(long sinceId) {
        setSinceId(sinceId);
    }
    
    public Paging(int page, long sinceId) {
        this(page);
        setSinceId(sinceId);
    }
    
    public Paging(int page, int count) {
        this(page);
        setCount(count);
    }


    public Paging(int page, int count, long sinceId) {
        this(page, count);
        setSinceId(sinceId);
    }

    public Paging(int page, int count, long sinceId, long maxId) {
        this(page, count, sinceId);
        setMaxId(maxId);
    }

    
  


	public int getCursor() {
		return cursor;
	}


	public void setCursor(int cursor) {
		this.cursor = cursor;
	}


	public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (page < 1) {
            throw new IllegalArgumentException("page should be positive integer. passed:" + page);
        }
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count should be positive integer. passed:" + count);
        }
        this.count = count;
    }

    public Paging count(int count) {
        setCount(count);
        return this;
    }

    public long getSinceId() {
        return sinceId;
    }

    public void setSinceId(int sinceId) {
        if (sinceId < 1) {
            throw new IllegalArgumentException("since_id should be positive integer. passed:" + sinceId);
        }
        this.sinceId = sinceId;
    }

    public Paging sinceId(int sinceId) {
        setSinceId(sinceId);
        return this;
    }

    public void setSinceId(long sinceId) {
        if (sinceId < 1) {
            throw new IllegalArgumentException("since_id should be positive integer. passed:" + sinceId);
        }
        this.sinceId = sinceId;
    }

    public Paging sinceId(long sinceId) {
        setSinceId(sinceId);
        return this;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        if (maxId < 1) {
            throw new IllegalArgumentException("max_id should be positive integer. passed:" + maxId);
        }
        this.maxId = maxId;
    }

    public Paging maxId(long maxId) {
        setMaxId(maxId);
        return this;
    }

	public boolean isNull() {
		if(this==null)return true;
		return false;
	}
	
	public static final Paging NULL=new NullPaging();
	public static class NullPaging extends Paging implements Nullable{

		private static final long serialVersionUID = 3753018168128208830L;
		private NullPaging(){
			super(1, 2);
		}
		@Override
		public boolean isNull() {
			return true;
		}
		
	}
}
