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

import com.fitconnet.persitence.model.Activity;
import com.fitconnet.service.interfaces.ActivityServiceI;

@RestController
@RequestMapping("/api/v1/actividades")
public class ActivityController {

	@Qualifier("activityService")
	private final ActivityServiceI activityService;
	private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	public ActivityController(ActivityServiceI activityService) {
		this.activityService = activityService;
	}

	@GetMapping
	public ResponseEntity<Page<Activity>> getAllActivities(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		logger.info("ActivityController :: getAllActivities");
		Pageable pageable = PageRequest.of(page, size);
		Page<Activity> activityPage = activityService.getAllActivity(pageable);
		// Page<ActivityDTO> activityDTOPage =
		// activityMapper.toActivityDTOSPage(activityPage);
		return new ResponseEntity<>(activityPage, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
		logger.info("ActivityController :: createActivity");
		activityService.createActivity(activity);
		// ActivityDTO createdActivityDTO = activityService.createActivity(activity);
		return new ResponseEntity<>(activity, HttpStatus.CREATED);
	}

}