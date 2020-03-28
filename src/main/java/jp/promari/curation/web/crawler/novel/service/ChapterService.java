package jp.promari.curation.web.crawler.novel.service;

import jp.promari.curation.web.crawler.novel.entity.Chapter;

import java.util.List;

public interface ChapterService {

    List<Chapter> findAll();

    Chapter findById(String id);

    Chapter save(Chapter chapter);

    void delete(String id);

    List<Chapter> findByNId(String id);
}
