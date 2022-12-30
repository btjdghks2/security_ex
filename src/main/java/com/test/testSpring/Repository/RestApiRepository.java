package com.test.testSpring.Repository;

import com.test.testSpring.Entity.JpaEntitys;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestApiRepository extends CrudRepository<JpaEntitys, Long> {

    @Override
    List<JpaEntitys> findAll();
}
