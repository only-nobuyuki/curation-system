package jp.promari.curation.web.crawler.novel.entity;

import lombok.Data;

@Data
public class NovelBO {

    private Novel novel;

    private Chapter chapter;

    private Content content;
}
