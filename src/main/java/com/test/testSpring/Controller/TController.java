package com.test.testSpring.Controller;


import com.test.testSpring.Entity.JpaEntitys;
import com.test.testSpring.Repository.RestApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class TController {

    private final com.test.testSpring.Service.restService restService;


    @Async
    @GetMapping("/")
    public ResponseEntity<?> home() {

        return new ResponseEntity<>(restService.homePage(),HttpStatus.OK);
    }

    @Async
    @PostMapping("/post")
    public ResponseEntity<?> create(@RequestBody JpaEntitys jpaEntitys) throws IllegalAccessException {
        return new ResponseEntity<>(restService.Saved(jpaEntitys), HttpStatus.OK);
    }

    @Async
    @GetMapping("/Detail/{id}")
    public ResponseEntity<?> Detail(@PathVariable Long id) {
        return new ResponseEntity<>(restService.Detaild(id), HttpStatus.OK);
    }

    @Async
    @DeleteMapping("/api/detail/{id}/delete/")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody JpaEntitys jpaEntitys) {
        return new ResponseEntity<>(restService.delete(id), HttpStatus.OK);
    }



}
