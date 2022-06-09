package beans;

public class User {
	private String usr = "";
	private String pwd;
	private int role;
	private String name, address, phone;
	private int check;
	
	private String message = "";
	//"Error validating information."
	
	
	
	public User () {}
	
	public String getMessage() {
		return message;
	}

	public User (String usr, String pwd) {
		super();
		this.usr = usr;
		this.pwd = pwd;
	}

	public User(String usr, String pwd, int role, String name, String address, String phone, int check) {
		super();
		this.usr = usr;
		this.pwd = pwd;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.check = check;
	}
	
	
	public User(String usr, String pwd, int role, String name, String address, String phone) {
		super();
		this.usr = usr;
		this.pwd = pwd;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;		
	}

	

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
	
	
	
	
	
	// VALIDATE method
	public boolean validate() {
		
		if(usr == null) {
			message = "no email address set!!";
			return false;
		}
		
		if(pwd == null) {
			message = "no password set!!";
			return false;
		}
		
		
		
		
		String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
		if (!usr.matches(regexMail)) {
			message = "invalid email address!!";
			return false;
		}
		
		/*
		if(!email.matches("\\w+@\\w+\\.\\w+")) {
			message = "invalid email address!!";
			return false;
		}
		*/
		
		
		
		
		String regexPass = "[a-zA-Z0-9_!@#$%^&*]+";
		if(pwd.length() < 3) {
			message = "password must be at least 3 character!!";
			return false;
		} 			
		else if (!pwd.matches(regexPass)) {
			message = "invalid password!!";
			return false;
		}
		/*
		else if(password.matches("\\w*\\s+\\w*")) {
			message = "password can not contain space.";
			return false;
		}
		*/
				
				
		message = "";
		return true;
	}
	
	
	
	
}
