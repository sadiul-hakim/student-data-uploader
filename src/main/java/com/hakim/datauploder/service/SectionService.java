package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Section;
import com.hakim.datauploder.repository.SectionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepo sectionRepo;

    public Section save(Section dataType) {
        return sectionRepo.save(dataType);
    }

    public Section getById(long id) {
        return sectionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find section by id : " + id));
    }

    public Section getByName(String type) {
        return sectionRepo.findByName(type)
                .orElseThrow(() -> new RuntimeException("Could not find section by type : " + type));
    }

    public List<Section> getAll(){
        return sectionRepo.findAll();
    }
}
