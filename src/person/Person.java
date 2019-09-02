package person;

public class Person {
	private String givenName;
	private String surName;
	private int age;
	private String gender;
	private String eMail;
	private String phone;
	private String address;
	
	//constructor
	public Person(String givenName, String surName, int age, String gender, String eMail, String phone,
			String address) {
		super();
		this.givenName = givenName;
		this.surName = surName;
		this.age = age;
		this.gender = gender;
		this.eMail = eMail;
		this.phone = phone;
		this.address = address;
	}

	
	//getters
	public String getGivenName() {
		return givenName;
	}

	public String getSurName() {
		return surName;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String geteMail() {
		return eMail;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	//setters
	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}	
	
	public void print() {
		System.out.println("Name "+this.surName+" "+this.givenName);
		System.out.println("Age "+this.age);
	}
}
