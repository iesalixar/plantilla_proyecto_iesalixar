package com.fitconnet.dto;

import java.time.Duration;

public class ActivityDTO {

	private Long id;
	private String type;
	private Duration duration;
	private String place;
	// private List<UserDTO> participants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

//	public List<UserDTO> getParticipants() {
//		return participants;
//	}
//
//	public void setParticipants(List<UserDTO> participants) {
//		this.participants = participants;
//	}

}
