package com.fitconnet.dto.entities;

import java.time.Duration;
import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class ActivityDTO {
	private String type;
	private Duration duration;
	private String place;
	private Date date;
	private UserDTO creator;
	private Set<UserDTO> participants;

}
