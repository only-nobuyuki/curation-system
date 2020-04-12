package jp.promari.curation.web.crawler.novel.task;

import jp.promari.curation.web.crawler.novel.entity.Chapter;
import jp.promari.curation.web.crawler.novel.entity.Content;
import jp.promari.curation.web.crawler.novel.entity.Novel;
import jp.promari.curation.web.crawler.novel.entity.NovelBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
public class NovelProcessor implements PageProcessor {

    @Autowired
    private NovelPipeline novelPipeline;

    /**
     * クローリング先のURL
     */
    private String targetURLToCrawl = "https://dev.classmethod.jp/news/200421-workspaces-webinar/";

    private Site site = Site.me()
            .setCharset("UTF-8")
            .setTimeOut(10 * 1000)
            .setRetrySleepTime(3000)
            .setRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().css("ul.list-top div.col-md-5 a").nodes();
        if (nodes.size() != 0) {
            nodes.forEach(item -> {
                page.addTargetRequest(item.links().toString());
            });
            List<Selectable> list = page.getHtml().css("div.page a:nth-child(3) ").nodes();
            page.addTargetRequest(list.get(0).links().toString());

            //processメソッドの動きを確認するため
            System.out.println("sucsess");
        } else {
            List<Selectable> chapters = page.getHtml().css("div#list a").nodes();
            chapters.forEach(item -> {
                page.addTargetRequest(item.links().toString());

            });

//            if (chapters.size() != 0) {
//                String image = page.getHtml().css("div#fmimg img", "src").toString();
//                String title = page.getHtml().css("h1", "text").toString();
//                String autor = page.getHtml().css("div#info p:nth-child(2)", "text").toString();
//                String description = page.getHtml().css("div#intro p", "text").toString();
//                String category = page.getHtml().css("div.con_top>a:nth-of-type(2)", "text").toString();
//                Novel novel = new Novel();
//                novel.setImage(image);
//                novel.setTitle(title);
//                novel.setAuthor(autor);
//                novel.setDescription(description);
//                novel.setCategory(category);
//                page.putField("novel", novel);
//            } else {
//                String novelTitle = page.getHtml().css("div.con_top>a:nth-of-type(3)", "text").toString();
//                String chapterTitle = page.getHtml().css("h1", "text").toString();
//                String content = page.getHtml().css("div#content").toString();//html格式
//
//                Novel novel = new Novel();
//                novel.setTitle(novelTitle);
//                Chapter chapter = new Chapter();
//                chapter.setTitle(chapterTitle);
//                Content content1 = new Content();
//                content1.setDetails(content);
//                NovelBO novelBO = new NovelBO();
//                novelBO.setChapter(chapter);
//                novelBO.setContent(content1);
//                novelBO.setNovel(novel);
//                page.putField("novelBO", novelBO);
//            }
            //processメソッドの動きを確認するため
            System.out.println(chapters.toString());
            System.out.println("fail");
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 60 * 60 * 1000)
    public void process() {
        long startTime, endTime;
        System.out.println("Start crawling .....");
        startTime = System.currentTimeMillis();
        Spider.create(new NovelProcessor())
                .addUrl(targetURLToCrawl)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(20)
                .addPipeline(novelPipeline)
                .run();
        endTime = System.currentTimeMillis();
        System.out.println("Crawl ends ... time consuming : {}" + ((endTime - startTime) / 1000));
    }
}
