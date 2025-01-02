package com.saras.todo.app.service;

import com.saras.todo.app.model.Doodle;
import com.saras.todo.app.repository.DoodleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DoodleService {

    @Autowired
    DoodleRepo repo;

    public void changeDoodle(MultipartFile imageFile) throws IOException {
        // Delete previous doodle
        repo.deleteAll();

        Doodle doodle = new Doodle();
        doodle.setImageName(imageFile.getOriginalFilename());
        doodle.setImageType(imageFile.getContentType());
        doodle.setImageData(imageFile.getBytes());

        repo.save(doodle);
    }

    public Doodle getDoodle() {
        List <Doodle> doodleList = repo.findAll();
        if(!doodleList.isEmpty())
            return doodleList.get(0);
        return null; // fallback
    }
}
