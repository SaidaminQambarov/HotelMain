package com.exadel.hotel.service;

import com.exadel.hotel.dto.HotelDto;
import com.exadel.hotel.entity.Hotel;
import com.exadel.hotel.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    @CacheEvict(value = "addHotel", allEntries = true)
    public HotelDto add(HotelDto hotelDto) {
        return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);
    }

    @CacheEvict(value = "getHotel", allEntries = true)
    public HotelDto get(Long id) {
        return modelMapper.map(hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found")), HotelDto.class);
    }

    @CacheEvict(value = "updateHotel", allEntries = true)
    public HotelDto update(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        modelMapper.map(hotelDto, hotel);
        return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    @CacheEvict(value = "deleteHotel", allEntries = true)
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    @CacheEvict(value = "getAllHotels", allEntries = true)
    public List<HotelDto> getAll() {
        return modelMapper.map(hotelRepository.findAll(), new TypeToken<List<HotelDto>>() {}.getType());
    }

    @CacheEvict(value = "deleteAllHotels", allEntries = true)
    public void deleteAll() {
        hotelRepository.deleteAll();
    }
}
