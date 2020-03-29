package jp.promari.curation.web.crawler.novel.repository;

import jp.promari.curation.web.crawler.novel.entity.Novel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NovelRepository extends MongoRepository<Novel,String> {

    List<Novel> findByTitle(String title);

}
