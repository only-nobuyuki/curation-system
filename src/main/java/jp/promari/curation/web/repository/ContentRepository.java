package jp.promari.curation.web.repository;

import jp.promari.curation.web.entity.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Content,String> {
    Content findByCId(String id);
}
