package voyager.project.push.notification.api.model;

public class PersonalDetails {

	private String emailId;
	
	private String phoneNo;
	
	private String country;
	
	private String city;
	
	private String address;
	
	private String pinCode;

	private String deviceId;
	
	private String custId;
	
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "PersonalDetails [emailId=" + emailId + ", phoneNo=" + phoneNo + ", country=" + country + ", city="
				+ city + ", address=" + address + ", pinCode=" + pinCode + ", deviceId=" + deviceId + ", custId="
				+ custId + "]";
	}
	
	
}
