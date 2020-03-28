package jp.promari.curation.web.service;

import jp.promari.curation.web.entity.Content;

import java.util.List;

public interface ContentService {

    List<Content> findAll();

    Content findById(String id);

    Content save(Content content);

    void delete(String id);

    Content findByCId(String id);
}
