package jp.promari.curation.web.task;

import jp.promari.curation.web.entity.Chapter;
import jp.promari.curation.web.entity.Content;
import jp.promari.curation.web.entity.Novel;
import jp.promari.curation.web.entity.NovelBO;
import jp.promari.curation.web.service.ChapterService;
import jp.promari.curation.web.service.ContentService;
import jp.promari.curation.web.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class NovelPipeline implements Pipeline {

    @Autowired
    private NovelService novelService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ContentService contentService;

    @Override
    public void process(ResultItems resultItems, Task task) {

        Novel novel = resultItems.get("novel");
        NovelBO novelBO = resultItems.get("novelBO");

        if (novel != null) {
            novelService.save(novel);
        }
        if(novelBO != null){

            List<Novel> tempNovels = novelService.findByTitle(novelBO.getNovel().getTitle());
            if(tempNovels != null){

                Novel tempNovel = tempNovels.get(0);

                Chapter tempChapter = novelBO.getChapter();
                tempChapter.setNId(tempNovel.getId());
                Chapter saveChapter = chapterService.save(tempChapter);

                Content tempContent = novelBO.getContent();
                tempContent.setCId(saveChapter.getId());
                contentService.save(tempContent);
                System.out.println("Update \"{}\"-{} chapter success" + tempNovel.getTitle() + tempChapter.getTitle());
            }else{
                System.out.println("Chapter update failed");
            }
        }
    }
}
