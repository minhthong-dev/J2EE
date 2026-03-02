package com.example.baitap_tuan2.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.baitap_tuan2.models.Pic;

@Service
public class PicService {

    private List<Pic> pics = new ArrayList<>();
    public List<Pic> getAllPics() {
        return pics;
    }
    public Pic getPicById(String id) {
        return pics.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public String addOrUpdatePic(Pic pic) {
        if (pic.getId() == null || pic.getId().trim().isEmpty()) {
            int maxId = 0;
            if (!pics.isEmpty()) {
                try {
                    maxId = pics.stream()
                            .mapToInt(p -> Integer.parseInt(p.getId()))
                            .max()
                            .orElse(0);
                } catch (NumberFormatException e) {
                    maxId = pics.size();
                }
            }
            pic.setId(String.valueOf(maxId + 1));
            pics.add(pic);
            return "Pic added successfully with ID: " + pic.getId();
        } 
        for (int i = 0; i < pics.size(); i++) {
            if (pics.get(i).getId().equals(pic.getId())) {
                pics.set(i, pic);
                return "Pic updated successfully";
            }
        }
        pics.add(pic);
        return "Pic with new ID added successfully";
    }
    public boolean deletePic(String id) {
        return pics.removeIf(p -> p.getId().equals(id));
    }
}