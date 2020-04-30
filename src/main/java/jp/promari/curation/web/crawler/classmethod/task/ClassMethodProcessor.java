package jp.promari.curation.web.crawler.classmethod.task;

import jp.promari.curation.web.crawler.classmethod.dto.ClassMethodDTO;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Selectable;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ClassMethodProcessor implements PageProcessor {

    @Autowired
    private ClassMethodPipeline novelPipeline;

    //クラスメソッドのアーカイブページのurlに設定する日付
    ZonedDateTime d = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
    DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
    String today = f.format(d);

    /**
     * クローリング先のURL
     */
    private String targetURLToCrawlVer2 = "https://dev.classmethod.jp/"+today;

    /**
     * クローラーの設定
     */
    private Site site = Site.me()
            .setCharset(null)
            .setTimeOut(10 * 1000)
            .setRetrySleepTime(3000)
            .setRetryTimes(3)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");

    private static void accept(Node targetElse) {
        String s = targetElse.childNodes().get(14).toString();
    }

    @Override
    public void process(Page page) {

        //アーカイブページがない場合
        //イベント情報がない場合

        //日別のアーカイブページのイベントタグから詳細ページのurlゲット
        List<String> urls = page.getHtml().getDocument()
                .getElementById("articleNavsTypeNew")
                .select("li.news")
                .select("p.title")
                .select("a")
                .stream()
                .map(element -> element.attr("href"))
                .collect(Collectors.toList());

        System.out.println(urls.get(0));

        //取得したhtmlのnodeをリストに
        List<Node> nodes = page
                .getHtml()
                .getDocument()
                .childNodes().get(1)
                .childNodes().get(1)
                .childNodes().get(0)
                .childNodes().get(1).childNodes();

        //タグ名「item」で抽出
        List targetNodes = nodes.stream()
                .filter(node -> node.nodeName().equals("item"))
                .collect(Collectors.toList());

        List<ClassMethodDTO> classMethodDTOList = new ArrayList<>();
        targetNodes.stream().forEach(target -> {
            Node node = (Node) target;
            // inset DTO
            ClassMethodDTO classMethodDTO = ClassMethodDTO.builder().build();

            // Title
            String rowTitle = node.childNodes().get(1).toString();
            classMethodDTO.setTitle(rowTitle.substring(7, rowTitle.length() - 8));

            // Link
            classMethodDTO.setLink(node.childNodes().get(4).toString());

            // pubdate
            classMethodDTO.setPubdate(node.childNodes().get(6).toString());

            //Permalink
            try {
                classMethodDTO.setPermalink(node.childNodes().get(18));
            } catch (IndexOutOfBoundsException e) {
                classMethodDTO.setPermalink(node.childNodes().get(10));
            }
            //PermalinkId
            classMethodDTO
                    .setPermalinkId(classMethodDTO
                            .getPermalink()
                            .toString()
                            .substring(11, classMethodDTO.getPermalink().toString().length() - 17));

            String permalink = classMethodDTO
                    .getPermalink()
                    .toString()
                    .substring(28, classMethodDTO.getPermalink().toString().length() - 8);


            String regex = "(\\d+)";
            Pattern p = Pattern.compile(regex);
            Matcher permalinkMatcher = p.matcher(permalink);
            String permalinkId = null;
            if (permalinkMatcher.find()) {
                classMethodDTO.setPermalinkId(permalinkMatcher.group());
            }

            // image
            String rowImageUrl = null;
            try {
                rowImageUrl = ((Node) target).childNodes().get(20).toString();
            } catch (IndexOutOfBoundsException e) {
                rowImageUrl = ((Node) target).childNodes().get(12).toString();
            }
            Pattern sImageUrlPattern = Pattern.compile("<\\s*img\\s?[^>]*src\\s*=\\s*([\"'])(.*?)\\1");
            Matcher imageMatcher = sImageUrlPattern.matcher(rowImageUrl);
            String imgUrl = null;
            if (imageMatcher.find()) {
                classMethodDTO.setImage(imageMatcher.group(2));
            }

            classMethodDTOList.add(classMethodDTO);
            page.putField("ClassMethodDTO", classMethodDTOList);
        });
    }

    @Override
    public Site getSite() {
        return site;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 60 * 60 * 1000 * 24)
    public void process() {
        long startTime, endTime;
        System.out.println("Start crawling .....");
        startTime = System.currentTimeMillis();
        Spider.create(new ClassMethodProcessor())
                .addUrl(targetURLToCrawlVer2)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(20)
                .addPipeline(novelPipeline)
                .run();
        endTime = System.currentTimeMillis();
        System.out.println("Crawl ends ... time consuming : {}" + ((endTime - startTime) / 1000));
    }
}