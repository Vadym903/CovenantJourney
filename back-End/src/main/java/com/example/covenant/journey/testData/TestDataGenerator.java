package com.example.covenant.journey.testData;

import com.example.covenant.journey.models.photo.PhotoEntity;
import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.services.photo.PhotoService;
import com.example.covenant.journey.services.user.UserService;
import com.google.common.io.ByteStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class TestDataGenerator {

    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @PostConstruct
    public void generateData() {
        UserEntity userEntity = userService.create(getValidUser("IgorAustraliaZed", "Hello I'm old auctioneer from Australia, i'm looking for old furniture and old weapon especially muskets and melee weapons such as halberds and swords", "Igor", "Dmytrov"));
        userEntity.setPhoto(photoService.getShorterPhotoEntityById(loadImage("user2.jfif", userEntity).getId()));
        userService.update(userEntity);
    }

    private UserEntity getValidUser(String login, String description, String firstName, String lastName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("defaultemail@gmail.com");
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setLogin(login);
        userEntity.setPassword("dfgsfsdfg");
        userEntity.setDescription(description);
        return userEntity;
    }

    private PhotoEntity loadImage(String imageName, UserEntity userEntity) {
        if (imageName != null) {
            try {
                PhotoEntity photoEntity = new PhotoEntity();
                Resource resource = new ClassPathResource("demo-images/" + imageName);
               // InputStream is = getClass().getClassLoader().getResourceAsStream("demoImages/" + imageName);
                byte[] targetArray = ByteStreams.toByteArray(resource.getInputStream());
                photoEntity.setBytes(targetArray);
                photoEntity.setType("image/jpg");
                photoEntity = photoService.create(photoEntity, userEntity);
                return photoEntity;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
