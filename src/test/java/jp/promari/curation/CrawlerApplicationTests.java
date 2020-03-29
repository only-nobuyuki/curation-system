package jp.promari.curation;

import jp.promari.curation.web.crawler.novel.service.ChapterService;
import jp.promari.curation.web.crawler.novel.service.ContentService;
import jp.promari.curation.web.crawler.novel.service.NovelService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CrawlerApplicationTests {

    private Logger logger = LoggerFactory.getLogger(CrawlerApplicationTests.class);

    @Autowired
    private NovelService novelService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ContentService contentService;

    @Test
    public void testService(){
        novelService.findAll().forEach(item -> logger.info("Query test：{}",item));
        chapterService.findAll().forEach(item -> logger.info("Query test：{}",item));
        contentService.findAll().forEach(item -> logger.info("Query test：{}",item));
    }


    @Test
    public void testLogger() {
        logger.info("Test log");
    }


}
