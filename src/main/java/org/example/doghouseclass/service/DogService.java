package org.example.doghouseclass.service;


import org.example.doghouseclass.dto.DogDto;
import org.example.doghouseclass.entity.Dog;
import org.example.doghouseclass.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DogService {

    List<DogDto> findAll();

    Dog save(DogDto dogDto);

    void delete(Integer id);

    DogDto update(DogDto dogDto);


}
