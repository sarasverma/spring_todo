package com.saras.todo.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

// we will be having only 1 doodle for app
// should not store image in DB instead S3 bucket

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doodle {
    @Id
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
}
