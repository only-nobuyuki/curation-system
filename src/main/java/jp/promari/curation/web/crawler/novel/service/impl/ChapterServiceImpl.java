package jp.promari.curation.web.crawler.novel.service.impl;

import jp.promari.curation.web.crawler.novel.entity.Chapter;
import jp.promari.curation.web.crawler.novel.repository.ChapterRepository;
import jp.promari.curation.web.crawler.novel.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Override
    public List<Chapter> findAll() {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC,"title"));
        return chapterRepository.findAll(Sort.by(orders));
    }

    @Override
    public Chapter findById(String id) {
        return chapterRepository.findById(id).get();
    }

    @Override
    public Chapter save(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    @Override
    public void delete(String id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public List<Chapter> findByNId(String id) {
        return chapterRepository.findByNId(id);
    }
}
