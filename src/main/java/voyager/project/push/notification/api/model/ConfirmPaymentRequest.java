package voyager.project.push.notification.api.model;

public class ConfirmPaymentRequest {
	
	private String fromAccountNumber;
	
	private String toAccountNumber;
	
	private String amount;
	
	private String date;
	
	private String benfName;
	
	private String deviceId;
	
	private String currency;
	
	private String name;
	
	private String custId;
	
	

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setBenfName(String benfName) {
		this.benfName = benfName;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ConfirmPaymentRequest [fromAccountNumber=" + fromAccountNumber + ", toAccountNumber=" + toAccountNumber
				+ ", amount=" + amount + ", date=" + date + ", benfName=" + benfName + ", deviceId=" + deviceId
				+ ", currency=" + currency + ", name=" + name + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getFromAccountNumber() {
		return fromAccountNumber;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public String getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public String getBenfName() {
		return benfName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getCurrency() {
		return currency;
	}

	public String getName() {
		return name;
	}
	
	
	

}
