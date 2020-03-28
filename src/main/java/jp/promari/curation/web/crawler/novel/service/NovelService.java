package jp.promari.curation.web.crawler.novel.service;

import jp.promari.curation.web.crawler.novel.entity.Novel;

import java.util.List;

public interface NovelService {

        List<Novel> findAll();

        Novel findById(String id);

        List<Novel> findByTitle(String title);

        Novel save(Novel novel);

        void deleet(String id);

}
