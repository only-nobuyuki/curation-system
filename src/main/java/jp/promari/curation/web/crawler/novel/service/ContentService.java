package jp.promari.curation.web.crawler.novel.service;

import jp.promari.curation.web.crawler.novel.entity.Content;

import java.util.List;

public interface ContentService {

    List<Content> findAll();

    Content findById(String id);

    Content save(Content content);

    void delete(String id);

    Content findByCId(String id);
}
