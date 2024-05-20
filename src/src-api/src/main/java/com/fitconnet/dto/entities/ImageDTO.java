package com.fitconnet.dto.entities;

import java.sql.Blob;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ImageDTO {
	@Lob
	private Blob image;

}
