package voyager.project.push.notification.api.model;

import java.util.Map;

public class PushNotificationModel {
	
	private EventDetails eventDetails;
	
	private Map<String, Object> eventAttributes;

	public EventDetails getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
	}

	public Map<String, Object> getEventAttributes() {
		return eventAttributes;
	}

	public void setEventAttributes(Map<String, Object> eventAttributes) {
		this.eventAttributes = eventAttributes;
	}

	@Override
	public String toString() {
		return "PushNotificationModel [eventDetails=" + eventDetails + ", eventAttributes=" + eventAttributes + "]";
	}
	
	

}
