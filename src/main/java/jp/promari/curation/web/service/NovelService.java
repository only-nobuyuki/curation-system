package jp.promari.curation.web.service;

import jp.promari.curation.web.entity.Novel;

import java.util.List;

public interface NovelService {

        List<Novel> findAll();

        Novel findById(String id);

        List<Novel> findByTitle(String title);

        Novel save(Novel novel);

        void deleet(String id);

}
