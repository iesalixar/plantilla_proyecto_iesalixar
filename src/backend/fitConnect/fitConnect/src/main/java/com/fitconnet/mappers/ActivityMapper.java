package com.fitconnet.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.persitence.model.Activity;

@Mapper
public interface ActivityMapper {

	ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

	@Mapping(source = "idActivity", target = "idActivity")
	@Mapping(source = "activityType", target = "activityType")
	@Mapping(source = "duration", target = "duration")
	@Mapping(source = "distance", target = "distance")
	@Mapping(source = "place", target = "place")
	@Mapping(source = "user", target = "user")
	@Mapping(source = "participants", target = "participants")
	ActivityDTO activityToActivityDTO(Activity activity);

	Activity activityDTOToActivity(ActivityDTO activityDTO);

	List<ActivityDTO> toActivityDTOS(List<Activity> activityList);

}
