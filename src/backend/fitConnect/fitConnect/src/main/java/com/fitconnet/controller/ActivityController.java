package com.fitconnet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.mappers.ActivityMapper;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.service.interfaces.ActivityServiceI;

@RestController
@RequestMapping("/api/v1/actividades")
public class ActivityController {

	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	private final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	private final ActivityMapper activityMapper;

	public ActivityController(ActivityServiceI activityService, ActivityMapper activityMapper) {
		this.activityService = activityService;
		this.activityMapper = activityMapper;
	}

	@GetMapping
	public ResponseEntity<Page<ActivityDTO>> getAllActivities(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("ActivityController :: getAllActivities");
		Pageable pageable = PageRequest.of(page, size);
		Page<Activity> activityPage = activityService.getAllActivity(pageable);
		Page<ActivityDTO> activityDTOPage = activityMapper.toActivityDTOSPage(activityPage);
		return new ResponseEntity<>(activityDTOPage, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
		logger.info("ActivityController :: createActivity");
		Activity activity = activityMapper.toActivity(activityDTO);
		ActivityDTO createdActivityDTO = activityService.createActivity(activity);
		return new ResponseEntity<>(createdActivityDTO, HttpStatus.CREATED);
	}

}