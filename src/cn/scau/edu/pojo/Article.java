package cn.scau.edu.pojo;

import java.util.Date;

public class Article {
	private int id;
	private int pid;
	private int rootid;
	private String title;
	private String cont;
	private Date pdate;
	private int isleaf; // 是否是叶子节点 是：0 不是：1
	private int scan;  //已经阅读的数目
	private int authorid; //作者ID
	private int reply;  //已经回答的帖子数
	private int latestreply; //最近回复的帖子ID
	private String authorName;
	private String lreplyName;
	
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getLreplyName() {
		return lreplyName;
	}

	public void setLreplyName(String lreplyName) {
		this.lreplyName = lreplyName;
	}

	public int getScan() {
		return scan;
	}

	public void setScan(int scan) {
		this.scan = scan;
	}

	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	public int getLatestreply() {
		return latestreply;
	}

	public void setLatestreply(int latestreply) {
		this.latestreply = latestreply;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getRootid() {
		return rootid;
	}

	public void setRootid(int rootid) {
		this.rootid = rootid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public int getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", pid=" + pid + ", rootid=" + rootid
				+ ", title=" + title + ", cont=" + cont + ", pdate=" + pdate
				+ ", isleaf=" + isleaf + ", scan=" + scan + ", authorid="
				+ authorid + ", reply=" + reply + ", latestreply="
				+ latestreply + ", authorName=" + authorName + ", lreplyName="
				+ lreplyName + "]";
	}

	

	
}
