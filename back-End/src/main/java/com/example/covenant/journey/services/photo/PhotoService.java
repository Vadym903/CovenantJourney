package com.example.covenant.journey.services.photo;


import com.example.covenant.journey.models.photo.PhotoEntity;
import com.example.covenant.journey.models.photo.ShorterPhotoEntity;
import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.repositories.photo.PhotoRepository;
import com.example.covenant.journey.repositories.photo.ShorterPhotoRepository;
import com.example.covenant.journey.services.AbstractService;
import com.example.covenant.journey.services.user.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService extends AbstractService <PhotoEntity> {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private ShorterPhotoRepository shorterPhotoRepository;

    @Override
    protected BaseRepository<PhotoEntity> getRepository() {
        return photoRepository;
    }

    @Override
    public PhotoEntity create(PhotoEntity entity) {
        entity.setUserEntity(currentUserService.getCurrentUser());
        return super.create(entity);
    }

    public PhotoEntity create(PhotoEntity entity, UserEntity userEntity) {
        entity.setUserEntity(userEntity);
        return super.create(entity);
    }

    public ShorterPhotoEntity getShorterPhotoEntityById(Long id) {
        return shorterPhotoRepository.findById(id).orElse(null);
    }

    @Override
    public void update(PhotoEntity entity) {
        throw new UnsupportedOperationException("temporary unsupported");
    }
}
