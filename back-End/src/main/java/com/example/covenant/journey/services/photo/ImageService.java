package com.example.covenant.journey.services.photo;


import com.example.covenant.journey.model.photo.Image;
import com.example.covenant.journey.model.photo.ImageType;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.repositories.photo.PhotoRepository;
import com.example.covenant.journey.services.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService extends AbstractService<Image> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

	@Value("${com.covenant.image.folderName}")
	private String imageFolderName;

	@Autowired
	private PhotoRepository photoRepository;

	@Override
	protected BaseRepository<Image> getRepository() {
		return photoRepository;
	}

	public Image saveImage(MultipartFile multipartFile, ImageType imageType) {
//        validateMultipartFile(multipartFile); // TODO
		String fileName = UUID.randomUUID() + multipartFile.getOriginalFilename();
		File directory = new File(getRootDirectory());
		Path path = Paths.get(directory.toPath() + File.separator + fileName);
		try {
			byte[] bytes = multipartFile.getBytes();
			Files.write(path, bytes);
			return createImageEntity(multipartFile, fileName, imageType);
		} catch (IOException e) {
			throw new RuntimeException("Can't save image "); // TODO
		}
	}

	private String getRootDirectory() {
		String folderPath = System.getProperty("user.home", ".") + imageFolderName;
		if (!Files.exists(Paths.get(folderPath))) {
			try {
				LOGGER.info("Directory with path `" + folderPath + "` was created");
				Files.createDirectories(Paths.get(folderPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return folderPath;
	}

	public Image createImageEntity(MultipartFile multipartFile, String fileName, ImageType imageType) {
		Image image = new Image();
		image.setImageName(fileName);
		image.setImageType(imageType);
		image.setFormat(multipartFile.getContentType());
		image.setOriginalName(multipartFile.getOriginalFilename());
		return create(image);
	}

	@Override
	public Image update(Image entity) {
		throw new UnsupportedOperationException("temporary unsupported");
	}

	public ByteArrayResource getImageByName(String filename) throws FileNotFoundException {
		Path imagePath = Paths.get(getRootDirectory()).resolve(filename.trim());
		try {
			ByteArrayResource body = new ByteArrayResource(Files.readAllBytes(imagePath));
			if (!body.exists()) {
				throw new FileNotFoundException("Image was not found");
			}
			return body;
		} catch (Exception e) {
			throw new FileNotFoundException("Can't get image");
		}
	}
}
