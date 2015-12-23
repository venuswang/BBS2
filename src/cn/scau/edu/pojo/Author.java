package cn.scau.edu.pojo;

public class Author {
	private int id;
	private String name;
	private String sex;
	private String slikes;
	private String mlikes;
	private String privince;
	private String introduce;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSlikes() {
		return slikes;
	}

	public void setSlikes(String slikes) {
		this.slikes = slikes;
	}

	public String getMlikes() {
		return mlikes;
	}

	public void setMlikes(String mlikes) {
		this.mlikes = mlikes;
	}

	public String getPrivince() {
		return privince;
	}

	public void setPrivince(String privince) {
		this.privince = privince;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", slikes=" + slikes + ", mlikes=" + mlikes + ", privince="
				+ privince + ", introduce=" + introduce + ", password="
				+ password + "]";
	}
	
	
}
