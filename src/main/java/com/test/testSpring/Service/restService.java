package com.test.testSpring.Service;

import com.test.testSpring.Entity.JpaEntitys;
import com.test.testSpring.Repository.RestApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class restService {

    @Async
    public void asyncMethod(int i) {
        try {
            Thread.sleep(500);
            log.info("[AsyncMethod]"+"-"+i);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }


    private final RestApiRepository restApiRepository;

    @Transactional
    public JpaEntitys Saved(JpaEntitys jpaEntitys) {
        return restApiRepository.save(jpaEntitys);

    }

    @Transactional
    public JpaEntitys Detaild(Long id) {
        return restApiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인바람"));
    }

    @Transactional(readOnly = true)
    public List<JpaEntitys> homePage() {
        return restApiRepository.findAll();
    }

    @Transactional
    public JpaEntitys update(Long id, JpaEntitys jpaEntitys) {
        JpaEntitys jpaentitys = restApiRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id를 확인해주세요"));
        jpaentitys.setTitle(jpaEntitys.getTitle());
        jpaentitys.setAuther(jpaEntitys.getAuther());

        return null;
    }
    @Transactional
    public String delete(Long id) {
        restApiRepository.deleteById(id);
        return "ok";
    }

}
