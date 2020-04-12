package jp.promari.curation;


import jp.promari.curation.web.crawler.novel.task.NovelProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

@SpringBootApplication
@EnableWebMvc
public class PromariCurationApp extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PromariCurationApp.class, args);

    }

}
