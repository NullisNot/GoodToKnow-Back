package com.example.goodToKnow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.goodToKnow.entity.Master;
import com.example.goodToKnow.errors.MasterNotFoundException;
import com.example.goodToKnow.repository.MasterRepository;

@Service
public class MasterService {
  @Autowired
  MasterRepository masterRepository;

  public Master saveMaster(Master master) {
    return masterRepository.save(master);
  }

  public List<Master> findMasters() {
    List<Master> masters = masterRepository.findAll();

    return masters;
  }

  public void deleteMaster(Long id) {
    Optional<Master> master = masterRepository.findById(id);

    if (master.isEmpty()) {
      throw new MasterNotFoundException();
    }

    masterRepository.deleteById(id);
  }
}
