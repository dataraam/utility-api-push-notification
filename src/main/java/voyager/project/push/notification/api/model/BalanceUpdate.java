package voyager.project.push.notification.api.model;

public class BalanceUpdate {
	
	private String benfAccountNumber;
	
	private String date;
	
	private String benfName;
	
	private String deviceId;
	
	private String currency;
	
	private String benfType;
	
	private String previousBalance;
	
	private String updatedBalance;
	
	private String custId;
	
	

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setBenfAccountNumber(String benfAccountNumber) {
		this.benfAccountNumber = benfAccountNumber;
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

	public void setBenfType(String benfType) {
		this.benfType = benfType;
	}

	public void setPreviousBalance(String previousBalance) {
		this.previousBalance = previousBalance;
	}

	public void setUpdatedBalance(String updatedBalance) {
		this.updatedBalance = updatedBalance;
	}

	public String getBenfAccountNumber() {
		return benfAccountNumber;
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

	public String getBenfType() {
		return benfType;
	}

	public String getPreviousBalance() {
		return previousBalance;
	}

	public String getUpdatedBalance() {
		return updatedBalance;
	}

	@Override
	public String toString() {
		return "BalanceUpdate [benfAccountNumber=" + benfAccountNumber + ", date=" + date + ", benfName=" + benfName
				+ ", deviceId=" + deviceId + ", currency=" + currency + ", benfType=" + benfType + ", previousBalance="
				+ previousBalance + ", updatedBalance=" + updatedBalance + ", custId=" + custId + "]";
	}
	
	

}
