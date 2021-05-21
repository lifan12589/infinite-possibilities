package com.infinitePossibilities.repository;

import com.infinitePossibilities.domain.AopEx;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AopExRepository extends JpaRepository<AopEx, Integer> {


    public List<AopEx> findByAge(Integer age);

    public List<AopEx> findByIdIn(List<Integer> id);

    public List<AopEx> deleteByIdIn(List<Integer>  id);

    public List<AopEx> findAllByIdIn(List<Integer> id);
}
