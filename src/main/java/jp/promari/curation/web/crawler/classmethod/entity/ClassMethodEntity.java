package jp.promari.curation.web.crawler.classmethod.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@Document(collection = "domain")
public class ClassMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String link;
    private String pubdate;
    private String permalink;
    private String permalinkId;
    private String image;
}
