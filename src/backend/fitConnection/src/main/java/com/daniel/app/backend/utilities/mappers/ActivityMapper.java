package com.daniel.app.backend.utilities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.daniel.app.backend.dtos.ActivityDTO;
import com.daniel.app.backend.persistence.model.Activity;

@Mapper
public interface ActivityMapper {

	ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

	@Mapping(source = "activityType", target = "activityType")
	@Mapping(source = "startTime", target = "startTime")
	@Mapping(source = "endTime", target = "endTime")
	@Mapping(source = "distance", target = "distance")
	@Mapping(source = "distance", target = "distance")
	ActivityDTO map(Activity act);
}
