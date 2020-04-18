package jp.promari.curation.web.crawler.classmethod.contoller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jp.promari.curation.web.crawler.classmethod.service.ClassMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Novel Controller")
@CrossOrigin
@RestController
public class ClassMethodController {

    @Autowired
    private ClassMethodService novelService;

    @ApiOperation(value = "Get all novels", notes = "Get all novels")
    @GetMapping("/novels")
    public String getNovelAll() {
//    public ResultBean getNovelAll() {
//        List<Novel> novels = novelService.findAll();
//        if(novels != null){
//            return ResultBean.success().add("novels",novels);
//        }else{
//            return ResultBean.fail();
//        }
        return "success";
    }

//    @ApiOperation(value = "Get all chapters by novel ID", notes = "Get all chapters by novel ID")
//    @ApiImplicitParam(name = "id", value = "Novel ID")
//    @GetMapping("/chapters/{id}")
//    public ResultBean getNovelChapters(@PathVariable("id") String id) {
//        List<Chapter> chapters = chapterService.findByNId(id);
//        if (chapters != null) {
//            return ResultBean.success().add("chapters", chapters);
//        } else {
//            return ResultBean.fail();
//        }
//    }

//    @ApiOperation(value = "Get content based on chapter id", notes = "Get content based on chapter id")
//    @ApiImplicitParam(name = "id", value = "Chapter ID")
//    @GetMapping("/content/{id}")
//    public ResultBean getChapterContent(@PathVariable("id") String id) {
//        Content content = contentService.findByCId(id);
//        if (content != null) {
//            return ResultBean.success().add("content", content);
//        } else {
//            return ResultBean.fail();
//        }
//    }

}
