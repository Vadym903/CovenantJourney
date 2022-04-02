package com.example.covenant.journey.testData;

import com.example.covenant.journey.model.photo.PhotoEntity;
import com.example.covenant.journey.model.user.User;
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
        User user = userService.create(getValidUser("IgorAustraliaZed", "Hello I'm old auctioneer from Australia, i'm looking for old furniture and old weapon especially muskets and melee weapons such as halberds and swords", "Igor", "Dmytrov"));
        user.setPhoto(photoService.getShorterPhotoEntityById(loadImage("user2.jfif", user).getId()));
        userService.update(user);
    }

    private User getValidUser(String login, String description, String firstName, String lastName) {
        User user = new User();
        user.setEmail("defaultemail@gmail.com");
        user.setFullName(firstName);
        user.setLogin(login);
        user.setPassword("dfgsfsdfg");
        user.setDescription(description);
        return user;
    }

    private PhotoEntity loadImage(String imageName, User user) {
        if (imageName != null) {
            try {
                PhotoEntity photoEntity = new PhotoEntity();
                Resource resource = new ClassPathResource("demo-images/" + imageName);
               // InputStream is = getClass().getClassLoader().getResourceAsStream("demoImages/" + imageName);
                byte[] targetArray = ByteStreams.toByteArray(resource.getInputStream());
                photoEntity.setBytes(targetArray);
                photoEntity.setType("image/jpg");
                photoEntity = photoService.create(photoEntity, user);
                return photoEntity;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
