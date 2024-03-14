package com.fitconnet.dto;

import java.time.Duration;
import java.util.List;

public class ActivityDTO {

	private Long idActivity;
	private String activityType;
	private Duration duration;
	private String place;
	private List<UserDTO> participants;

	public Long getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Long idActivity) {
		this.idActivity = idActivity;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<UserDTO> getParticipants() {
		return participants;
	}

	public void setParticipants(List<UserDTO> participants) {
		this.participants = participants;
	}

}
