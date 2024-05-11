package com.fitconnet.dto.requets;

import com.fitconnet.dto.entities.ActivityDTO;

import lombok.Data;

@Data
public class GeneralActivity {
	private ActivityDTO activity;
	private Long userId;
}
