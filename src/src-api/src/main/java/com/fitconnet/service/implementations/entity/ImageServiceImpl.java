package com.fitconnet.service.implementations.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fitconnet.dto.entities.ImageDTO;
import com.fitconnet.persitence.model.Activity;
import com.fitconnet.persitence.model.ActivityImg;
import com.fitconnet.persitence.model.Image;
import com.fitconnet.persitence.model.ProfileImg;
import com.fitconnet.persitence.model.User;
import com.fitconnet.persitence.repository.ImageRepository;
import com.fitconnet.service.interfaces.entity.ImageServiceI;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageServiceI {

	private final ImageRepository imageRepository;

	@Override
	public List<ImageDTO> getAll() {
		return imageRepository.findAll().stream().map(this::imageToImageDTO).toList();
	}

	@Override
	public ImageDTO getById(Long id) {
		return imageToImageDTO(imageRepository.findById(id).get());
	}

	@Override
	public void delete(Long id) {
		imageRepository.deleteById(id);
	}

	@Override
	public void create(ImageDTO img) {
		imageRepository.save(imageDTOtoImage(img));
	}

	@Override
	public void update(Long id, ImageDTO img) {
		Optional<Image> aux = imageRepository.findById(id);
		if (aux.isPresent()) {
			imageRepository.deleteById(id);
			imageRepository.save(imageDTOtoImage(img));
		}

	}

	@Override
	public boolean existById(Long id) {
		return imageRepository.existsById(id);
	}

	@Override
	public ImageDTO imageToImageDTO(Image img) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImage(img.getImage());
		return imageDTO;
	}

	@Override
	public Image imageDTOtoImage(ImageDTO img) {
		Image newImage = new Image();
		newImage.setImage(img.getImage());
		return newImage;
	}

	@Override
	public ActivityImg imageDTOToActivityImg(ImageDTO imageDTO, Activity activity) {
		ActivityImg activityImg = new ActivityImg();
		activityImg.setImage(imageDTO.getImage());
		activityImg.setActividad(activity);
		return activityImg;
	}

	@Override
	public ImageDTO activityImgToImageDTO(ActivityImg activityImg) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImage(activityImg.getImage());
		return imageDTO;
	}

	@Override
	public ProfileImg imageDTOToProfileImg(ImageDTO imageDTO, User user) {
		ProfileImg profileImg = new ProfileImg();
		profileImg.setImage(imageDTO.getImage());
		profileImg.setUser(user);
		return profileImg;
	}

	@Override
	public ImageDTO profileImgToImageDTO(ProfileImg profileImg) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setImage(imageDTO.getImage());
		return imageDTO;
	}
}
