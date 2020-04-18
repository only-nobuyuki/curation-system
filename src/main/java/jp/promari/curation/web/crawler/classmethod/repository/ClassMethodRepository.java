package jp.promari.curation.web.crawler.classmethod.repository;

import jp.promari.curation.web.crawler.classmethod.entity.ClassMethodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClassMethodRepository extends MongoRepository<ClassMethodEntity, String> {

    Optional<ClassMethodEntity> findById(String id);

    ClassMethodEntity findByTitle(String title);

}
