package jp.promari.curation.web.crawler.novel.repository;

import jp.promari.curation.web.crawler.novel.entity.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<Content,String> {
    Content findByCId(String id);
}
