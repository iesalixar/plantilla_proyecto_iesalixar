package com.fitconnet.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.dto.UserDTO;
import com.fitconnet.persitence.model.Activity;

@Mapper
public interface ActivityMapper {

	ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "type", target = "type")
	@Mapping(source = "duration", target = "duration")
	@Mapping(source = "place", target = "place")
	@Mapping(source = "participants", target = "participants")
	ActivityDTO toActivityDTO(Activity activity);

	Activity toActivity(ActivityDTO activityDTO);

	default Page<ActivityDTO> toActivityDTOSPage(Page<Activity> page) {
		List<ActivityDTO> activityDTOList = page.getContent().stream().map(this::toActivityDTO)
				.collect(Collectors.toList());
		return new PageImpl<>(activityDTOList, page.getPageable(), page.getTotalElements());
	}

	List<Activity> toActivityList(List<UserDTO> list);
}
