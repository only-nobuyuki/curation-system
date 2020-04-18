package jp.promari.curation.web.crawler.classmethod.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.jsoup.nodes.Node;

@Data
@Setter
@Builder
public class ClassMethodDTO {


    private String id;
    private String title;
    private String link;
    private String pubdate;
    private Node permalink;
    private String permalinkId;
    private String image;
}
