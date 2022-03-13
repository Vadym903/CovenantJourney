package com.example.covenant.journey.models.photo;

import com.example.covenant.journey.models.PrimaryEntity;
import com.example.covenant.journey.models.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "auction_photo")
public class PhotoEntity implements PrimaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "content", columnDefinition = "bytea", nullable = false)
    private byte[] bytes;

    @NotBlank
    @Column(name = "type")
    private String type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoEntity that = (PhotoEntity) o;
        return Objects.equals(id, that.id) &&
                Arrays.equals(bytes, that.bytes) &&
                Objects.equals(type, that.type) &&
                Objects.equals(userEntity, that.userEntity);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, type, userEntity);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }
}
