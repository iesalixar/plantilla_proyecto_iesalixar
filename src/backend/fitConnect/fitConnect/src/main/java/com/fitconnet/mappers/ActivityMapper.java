package com.fitconnet.mappers;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;

import com.fitconnet.dto.ActivityDTO;
import com.fitconnet.dto.UserDTO;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.User;

@Mapper
public interface ActivityMapper {

	ActivityMapper ACTIVITY_INSTANCE = Mappers.getMapper(ActivityMapper.class);
	UserMapper USER_INSTANCE = Mappers.getMapper(UserMapper.class);

	ActivityDTO toActivityDTO(Activity activity);

	@InheritInverseConfiguration
	Activity toActivity(ActivityDTO activityDTO);

//	@AfterMapping
//	default void participantsMap(@MappingTarget ActivityDTO target, Activity origin) {
//		target.setParticipants(new HashSet<>());
//		if (origin != null && !CollectionUtils.isEmpty(origin.getParticipants())) {
//			for (User participant : origin.getParticipants()) {
//
//				UserDTO aux = USER_INSTANCE.toUserDTO(participant);
//
//				target.getParticipants().add(aux);
//
//			}
//		}
//
//	}

	default Page<ActivityDTO> toActivityDTOSPage(Page<Activity> page) {
		List<ActivityDTO> activityDTOList = page.getContent().stream().map(this::toActivityDTO)
				.collect(Collectors.toList());
		return new PageImpl<>(activityDTOList, page.getPageable(), page.getTotalElements());
	}
}
