package jp.promari.curation.web.repository;

import jp.promari.curation.web.entity.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChapterRepository extends MongoRepository<Chapter,String> {
    List<Chapter> findByNId(String id);
}
