package jp.promari.curation.web.crawler.novel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Content {
    @Id
    private String id;
    private String details;
    private String cId;
}
