package jp.promari.curation.web.crawler.novel.repository;

import jp.promari.curation.web.crawler.novel.entity.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChapterRepository extends MongoRepository<Chapter,String> {
    List<Chapter> findByNId(String id);
}
