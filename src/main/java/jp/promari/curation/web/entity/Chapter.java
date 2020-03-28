package jp.promari.curation.web.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Chapter {
    @Id
    private String id;
    private String title;
    private String nId;
}
