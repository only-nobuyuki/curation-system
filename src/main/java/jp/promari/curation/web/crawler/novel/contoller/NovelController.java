package jp.promari.curation.web.crawler.novel.contoller;

import jp.promari.curation.web.crawler.common.ResultBean;
import jp.promari.curation.web.crawler.novel.entity.Chapter;
import jp.promari.curation.web.crawler.novel.entity.Content;
import jp.promari.curation.web.crawler.novel.entity.Novel;
import jp.promari.curation.web.crawler.novel.service.ChapterService;
import jp.promari.curation.web.crawler.novel.service.ContentService;
import jp.promari.curation.web.crawler.novel.service.NovelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Novel Controller")
@CrossOrigin
@RestController
public class NovelController {

    @Autowired
    private NovelService novelService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ContentService contentService;

    @ApiOperation(value = "Get all novels",notes = "Get all novels")
    @GetMapping("/novels")
    public ResultBean getNovelAll(){
        List<Novel> novels = novelService.findAll();
        if(novels != null){
            return ResultBean.success().add("novels",novels);
        }else{
            return ResultBean.fail();
        }
    }

    @ApiOperation(value = "Get all chapters by novel ID",notes = "Get all chapters by novel ID")
    @ApiImplicitParam(name = "id",value = "Novel ID")
    @GetMapping("/chapters/{id}")
    public ResultBean getNovelChapters(@PathVariable("id") String id){
        List<Chapter> chapters = chapterService.findByNId(id);
        if(chapters != null){
            return ResultBean.success().add("chapters",chapters);
        }else{
            return ResultBean.fail();
        }
    }

    @ApiOperation(value = "Get content based on chapter id" ,notes = "Get content based on chapter id")
    @ApiImplicitParam(name = "id",value = "Chapter ID")
    @GetMapping("/content/{id}")
    public ResultBean getChapterContent(@PathVariable("id") String id){
        Content content = contentService.findByCId(id);
        if(content != null){
            return ResultBean.success().add("content",content);
        }else{
            return ResultBean.fail();
        }
    }

}
