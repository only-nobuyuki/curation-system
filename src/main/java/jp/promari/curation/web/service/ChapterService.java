package jp.promari.curation.web.service;

import jp.promari.curation.web.entity.Chapter;

import java.util.List;

public interface ChapterService {

    List<Chapter> findAll();

    Chapter findById(String id);

    Chapter save(Chapter chapter);

    void delete(String id);

    List<Chapter> findByNId(String id);
}
