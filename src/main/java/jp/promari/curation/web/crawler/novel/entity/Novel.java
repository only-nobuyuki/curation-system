package jp.promari.curation.web.crawler.novel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Novel {
    @Id
    private String id;
    private String title;
    private String author;
    private String description;
    private String image;
    private String category;
}
