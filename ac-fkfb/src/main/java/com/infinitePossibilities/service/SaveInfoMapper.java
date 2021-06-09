package com.infinitePossibilities.service;

import com.infinitePossibilities.dao.Save;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SaveInfoMapper extends JpaRepository<Save,String> {

    List<Save> findAllByIdBetween(Long id1,Long id2);

    List<Save> findAllByIdIn(List<Long> id);

}