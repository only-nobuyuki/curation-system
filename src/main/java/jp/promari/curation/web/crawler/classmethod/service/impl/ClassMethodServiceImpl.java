package jp.promari.curation.web.crawler.classmethod.service.impl;

import jp.promari.curation.web.crawler.classmethod.entity.ClassMethodEntity;
import jp.promari.curation.web.crawler.classmethod.repository.ClassMethodRepository;
import jp.promari.curation.web.crawler.classmethod.service.ClassMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClassMethodServiceImpl implements ClassMethodService {

    @Autowired
    private ClassMethodRepository classMethodRepository;

    @Override
    public List<ClassMethodEntity> findAll() {
        return classMethodRepository.findAll();
    }

    @Override
    public Optional<ClassMethodEntity> findById(String id) {
        Optional<ClassMethodEntity> byId = classMethodRepository.findById(id);
        return byId;
    }

    @Override
    public List<ClassMethodEntity> findByTitle(String title) {
        return classMethodRepository.findByTitle(title);
    }

    @Override
    public ClassMethodEntity save(ClassMethodEntity novel) {
        return classMethodRepository.save(novel);
    }

    @Override
    public void delete(String id) {
        classMethodRepository.deleteById(id);
    }
}
