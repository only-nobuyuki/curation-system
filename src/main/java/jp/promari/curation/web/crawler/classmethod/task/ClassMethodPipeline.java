package jp.promari.curation.web.crawler.classmethod.task;

import jp.promari.curation.web.crawler.classmethod.dto.ClassMethodDTO;
import jp.promari.curation.web.crawler.classmethod.entity.ClassMethodEntity;
import jp.promari.curation.web.crawler.classmethod.service.ClassMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

@Component
public class ClassMethodPipeline implements Pipeline {

    @Autowired
    private ClassMethodService classMethodService;

    @Override
    public void process(ResultItems resultItems, Task task) {

        List<ClassMethodDTO> classMethodDTO = resultItems.get("ClassMethodDTO");

        if (classMethodDTO != null) {
            classMethodDTO.stream().forEach(dto -> {
                ClassMethodEntity entity = classMethodService.findByTitle(dto.getPermalinkId());
                if (entity == null) {
                    ClassMethodEntity classMethodEntity = ClassMethodEntity.builder()
                            .title(dto.getTitle())
                            .pubdate(dto.getPubdate())
                            .link(dto.getLink())
                            .permalink(dto.getPermalink().toString())
                            .image(dto.getImage())
                            .build();

                    System.out.println(classMethodEntity);
                    classMethodService.save(classMethodEntity);
                }
            });
        }
    }
}