package org.example.doghouseclass.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.doghouseclass.dto.DogDto;
import org.example.doghouseclass.entity.Dog;
import org.example.doghouseclass.repository.DogRepository;
import org.example.doghouseclass.service.DogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {


    private final DogRepository dogRepository;


    @Override
    public List<DogDto> findAll() {
        return dogRepository.findAll().stream()
                .map(dog -> DogDto.builder().
                        id(dog.getId()).
                        age(dog.getAge()).
                        name(dog.getName()).
                        type(dog.getType()).build())
                .collect(Collectors.toList());
    }

    @Override
    public Dog save(DogDto dogDto) {
        return dogRepository.save(Dog.builder().age(dogDto.getAge())
                .name(dogDto.getName())
                .type(dogDto.getType())
                .build());
    }

    @Override
    public void delete(Integer id) {
        dogRepository.deleteById(id);
    }

    @Override
    public DogDto update(DogDto dogDto) {
        Dog dog = convertDtoToEntity(dogDto);
        if (dog != null) {
            return convertEntityToDto(dog);
        } else return null;
    }

    public Dog convertDtoToEntity(DogDto dogDto) {
        Dog dog = new Dog();
        dog.setId(dogDto.getId());
        dog.setAge(dogDto.getAge());
        dog.setName(dogDto.getName());
        dog.setType(dogDto.getType());
        return dog;
    }


    public DogDto convertEntityToDto(Dog dog) {
        DogDto dogDto = new DogDto();
        dogDto.setId(dog.getId());
        dogDto.setAge(dog.getAge());
        dogDto.setName(dog.getName());
        dogDto.setType(dog.getType());
        return dogDto;
    }
}
