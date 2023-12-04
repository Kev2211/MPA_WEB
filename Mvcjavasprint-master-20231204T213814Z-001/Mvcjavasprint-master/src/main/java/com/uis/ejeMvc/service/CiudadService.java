package com.uis.ejeMvc.service;

import com.uis.ejeMvc.model.Ciudad;
import com.uis.ejeMvc.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    public Ciudad saveCiudad(Ciudad ciudad){
        if(ciudad.getId() != 0)
        {
            return ciudadRepository.save(ciudad);
        }
        return null;
    }

    public Page<Ciudad> getAllCiudad(Integer page, Integer size, Boolean eneablePagination){
        return ciudadRepository.findAll(eneablePagination ? PageRequest.of(page, size): Pageable.unpaged());
    }

    public void deleteCiudad(Long id){
        ciudadRepository.deleteById(id);
    }

    public Ciudad editCiudad(Ciudad ciudad){
        if(ciudad.getId() != 0 && ciudadRepository.existsById(ciudad.getId()))
        {
            return ciudadRepository.save(ciudad);
        }
        return null;
    }
}
