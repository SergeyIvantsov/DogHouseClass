package org.example.doghouseclass.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doghouseclass.dto.DogDto;
import org.example.doghouseclass.entity.Dog;
import org.example.doghouseclass.service.DogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DogController {

    private final DogService dogService;

    @GetMapping("/getDogs")
    public String getDogs(Model model) {
        model.addAttribute("dogs", dogService.findAll());
        model.addAttribute("addDog", new DogDto());
        model.addAttribute("deleteDog", new DogDto());
        model.addAttribute("updateDog", new DogDto());
        return "dog";
    }

    @GetMapping("/dogs")
    @ResponseBody
    public List<DogDto> getDogsForDropdown() {
        return dogService.findAll();  // Получаем список собак через сервис
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

    @PostMapping("/deleteDog")
    public String deleteDog(@ModelAttribute("deleteDog") DogDto dogDto) {
        if (dogDto.getId() != null) {
            dogService.delete(dogDto.getId());
            return "redirect:/getDogs";
        }
        return "dog";
    }

    @GetMapping ("/editDog/{id}")
    public String editDog(@PathVariable("id") Integer id, Model model) {
        DogDto dog = dogService.findById(id);
        if (dog != null) {
            DogDto dogDto = new DogDto(dog.getId(), dog.getAge(), dog.getName(), dog.getType());
            model.addAttribute("dog", dogDto);
            return "updateDog";
        }else {
            return "redirect:/getDogs";
        }
    }

    @PostMapping("/updateDog")
    public String updateDog(@ModelAttribute("dog") DogDto dogDto) {
        dogService.update(dogDto);
return "redirect:/getDogs";
    }

}
