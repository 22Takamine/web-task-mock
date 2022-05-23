package entity;

public class User {

	private Integer userId;
	private String loginId;
	private String password;
	private String userName;
	private Integer role;
	
	public User() {
	}

	public User(Integer userId, String loginId, String password, String userName, Integer role) {
	    this.userId = userId;
	    this.loginId = loginId;
	    this.password = password;
	    this.userName = userName;
	    this.role = role;
	}

	
	public Integer getUserId() {
	    return userId;
	}

	public void setUserId(Integer userId) {
	    this.userId = userId;
	}
	
	
	public String getLoginId() {
	    return loginId;
	}

	public void setLoginId(String loginId) {
	    this.loginId = loginId;
	}


	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}

	
	public String getUserName() {
	    return userName;
	}

	public void setUserName(String userName) {
	    this.userName = userName;
	}

	public Integer getRole() {
	    return role;
	}
	
	public void setRole(Integer role) {
	    this.role = role;
	}

}
