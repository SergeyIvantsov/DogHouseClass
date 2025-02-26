package org.example.doghouseclass.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doghouseclass.dto.DogDto;
import org.example.doghouseclass.service.DogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class DogController {

    private final DogService dogService;


    @GetMapping("/getDogs")
    public String getDogs(Model model) {
        model.addAttribute("dogs", dogService.findAll());
        model.addAttribute("addDog", new DogDto());
        return "dog";
    }


    @PostMapping("/addDog")
    public String saveDog(@Valid @ModelAttribute("addDog") DogDto dogDto,
                          BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            dogService.save(dogDto);
            return "redirect:/getDogs";
        }
        return "dog";
    }




}
