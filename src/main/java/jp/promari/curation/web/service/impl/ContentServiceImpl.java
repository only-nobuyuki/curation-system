package jp.promari.curation.web.service.impl;

import jp.promari.curation.web.entity.Content;
import jp.promari.curation.web.repository.ContentRepository;
import jp.promari.curation.web.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public Content findById(String id) {
        return contentRepository.findById(id).get();
    }

    @Override
    public Content save(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public void delete(String id) {
        contentRepository.deleteById(id);
    }

    @Override
    public Content findByCId(String id) {
        return contentRepository.findByCId(id);
    }
}
