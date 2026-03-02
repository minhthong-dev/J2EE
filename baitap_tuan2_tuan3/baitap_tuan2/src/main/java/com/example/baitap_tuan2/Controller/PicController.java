package com.example.baitap_tuan2.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.baitap_tuan2.Service.PicService;
import com.example.baitap_tuan2.models.Pic;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/pics")
public class PicController {

    @Autowired
    private PicService picService;

    @GetMapping("/")
    public String getAllPics(Model model) {
        List<Pic> pics = picService.getAllPics();
        model.addAttribute("pics", pics);
        return "pic";
    }

    @GetMapping("/add-pic")
    public String showAddForm(Model model) {
        model.addAttribute("pic", new Pic("", "", "", 0L));
        return "addpic";
    }

    @PostMapping("/update-pic")
    public String processUpdate(@ModelAttribute Pic pic) {
        picService.addOrUpdatePic(pic);
        return "redirect:/api/pics/";
    }

    @GetMapping("/update-pic/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Pic pic = picService.getPicById(id);
        if (pic == null) {
            return "redirect:/api/pics/";
        }
        model.addAttribute("pic", pic);
        return "addpic";
    }

    @GetMapping("/{id}")
    public String getPicById(@PathVariable("id") String id) {
        Pic pic = picService.getPicById(id);
        return pic != null ? pic.toString() : "Pic not found";
    }

    @PostMapping("/addupdate")
    public String addOrUpdatePic(@Valid @ModelAttribute("pic") Pic pic, BindingResult result,
            @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            return "addpic";
        }

        if (!imageFile.isEmpty()) {
            try {
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.write(path, imageFile.getBytes());
                pic.setUrl("/images/" + fileName);
                // Optionally update fileSize based on the uploaded file
                pic.setFileSize(imageFile.getSize() / 1024); // KB
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        picService.addOrUpdatePic(pic);
        return "redirect:/api/pics/";
    }

    @GetMapping("/delete/{id}")
    public String deletePic(@PathVariable("id") String id) {
        picService.deletePic(id);
        return "redirect:/api/pics/";
    }
}