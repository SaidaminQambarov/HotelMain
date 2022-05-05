package com.exadel.demo.service;

import com.exadel.demo.dto.FloorDto;
import com.exadel.demo.entity.Floor;
import com.exadel.demo.repository.FloorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {

    public FloorService (FloorRepository floorRepository, ModelMapper modelMapper) {
        this.floorRepository = floorRepository;
        this.modelMapper = modelMapper;
    }

    private final FloorRepository floorRepository;

    protected final ModelMapper modelMapper;

    public FloorDto add(FloorDto floorDto) {
        return modelMapper.map(floorRepository.save(modelMapper.map(floorDto, Floor.class)), FloorDto.class);
    }

    public FloorDto update(Long id, FloorDto floorDto) {
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Floor with id " + id + " not found"));
        Floor floor1 = modelMapper.map(floorDto, Floor.class);
        floor1.setId(floor.getId());
        return modelMapper.map(floorRepository.save(floor1), FloorDto.class);
    }

    public FloorDto get(Long id) {
        return modelMapper.map(floorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Floor with id " + id + " not found")), FloorDto.class);
    }

    public List<FloorDto> getAll() {
        return modelMapper.map(floorRepository.findAll(), new TypeToken<List<FloorDto>>() {}.getType());
    }

    public void delete(Long id) {
        floorRepository.deleteById(id);
    }

    public void deleteAll() {
        floorRepository.deleteAll();
    }

    public List<FloorDto> getAllByHotelId(Long hotelId) {
        return modelMapper.map(floorRepository.findAllByHotelId(hotelId), new TypeToken<List<FloorDto>>() {}.getType());
    }


}
