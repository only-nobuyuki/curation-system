package jp.promari.curation.web.crawler.classmethod.service;

import jp.promari.curation.web.crawler.classmethod.entity.ClassMethodEntity;

import java.util.List;
import java.util.Optional;

public interface ClassMethodService {

    List<ClassMethodEntity> findAll();

    Optional<ClassMethodEntity> findById(String id);

    ClassMethodEntity findByTitle(String title);

    ClassMethodEntity save(ClassMethodEntity novel);

    void delete(String id);

}
