package jp.promari.curation.web.entity;

import lombok.Data;

@Data
public class NovelBO {

    private Novel novel;

    private Chapter chapter;

    private Content content;
}
