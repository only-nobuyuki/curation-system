package jp.promari.curation.web.crawler.classmethod.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "domain")
public class ClassMethodEntity {
    @Id
    private String id;
    private String title;
    private String link;
    private String pubdate;
    private String permalink;
    private String image;
}
