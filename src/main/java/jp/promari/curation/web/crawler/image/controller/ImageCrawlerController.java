package jp.promari.curation.web.crawler.image.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import jp.promari.curation.web.crawler.image.ImageCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageCrawlerController {

    private static final Logger logger = LoggerFactory.getLogger(ImageCrawlerController.class);

    @RequestMapping(value ="/images", method = RequestMethod.GET)
    public String imageCrawler() {

        CrawlConfig config = new CrawlConfig();

        // 中間クロールデータが格納されるフォルダを設定します。
        // (例：以前に取得したページから抽出され、後でクロールする必要があるURLのリスト)
        config.setCrawlStorageFolder("C:\\tmp\\crawler4j/");

        // クローリング中に使用するスレッドの数。これを増やすと、通常はクロールが速くなります。
        // しかし、クロール速度は他の多くの要因にも依存しています。
        //  これで実験してみて、自分にとって最適なスレッド数を把握することができます。
        int numberOfCrawlers = 8;

        // ダウンロードした画像はどこに保存しますか？
        File storageFolder = new File("/tmp/crawled-images/");

        // 画像はバイナリコンテンツなので、このパラメータをtrueに設定して、画像がクロールに含まれることを確認する必要があります。
        config.setIncludeBinaryContentInCrawling(true);

        List<String> crawlDomains = Arrays.asList("https://promari.jp/tags/programming/");

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
            for (String domain : crawlDomains) {
                controller.addSeed(domain);
            }
        } catch (Exception e) {
            logger.error("Image Crawler Fails.", e.getMessage(), e);
            return e.getMessage();
        }


        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }

        CrawlController.WebCrawlerFactory<ImageCrawler> factory = () -> new ImageCrawler(storageFolder, crawlDomains);
        controller.start(factory, numberOfCrawlers);

        return "Image Crawler Success";
    }
}

