package voyager.project.push.notification.api.model;

public class EventDetails {
	
	private String eventCode;
	
	private String custId;
	
	private String deviceId;

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEventCode() {
		return eventCode;
	}

	public String getCustId() {
		return custId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	@Override
	public String toString() {
		return "EventDetails [eventCode=" + eventCode + ", custId=" + custId + ", deviceId=" + deviceId + "]";
	}
	
	

}
