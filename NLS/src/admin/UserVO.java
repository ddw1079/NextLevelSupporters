package admin;

public class UserVO{
	private int id;
	private String login_id;
	private String name;
	private String phone;
	private Boolean is_active;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public UserVO(int id, String login_id, String name, String phone, Boolean is_active) {
		super();
		this.id = id;
		this.login_id = login_id;
		this.name = name;
		this.phone = phone;
		this.is_active = is_active;
	}
	

}
