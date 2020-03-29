package jp.promari.curation.web.crawler.novel.service.impl;

import jp.promari.curation.web.crawler.novel.entity.Novel;
import jp.promari.curation.web.crawler.novel.repository.NovelRepository;
import jp.promari.curation.web.crawler.novel.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelServiceImpl implements NovelService {

    @Autowired
    private NovelRepository novelRepository;

    @Override
    public List<Novel> findAll() {
        return novelRepository.findAll();
    }

    @Override
    public Novel findById(String id) {
        return novelRepository.findById(id).get();
    }

    @Override
    public List<Novel> findByTitle(String title) {
        return novelRepository.findByTitle(title);
    }

    @Override
    public Novel save(Novel novel) {
        return novelRepository.save(novel);
    }

    @Override
    public void deleet(String id) {
        novelRepository.deleteById(id);
    }
}
