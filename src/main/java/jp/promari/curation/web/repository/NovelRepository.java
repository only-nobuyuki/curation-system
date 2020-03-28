package jp.promari.curation.web.repository;

import jp.promari.curation.web.entity.Novel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NovelRepository extends MongoRepository<Novel,String> {

    List<Novel> findByTitle(String title);

}
