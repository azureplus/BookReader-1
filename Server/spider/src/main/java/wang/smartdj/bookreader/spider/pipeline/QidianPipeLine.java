package wang.smartdj.bookreader.spider.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import wang.smartdj.bookreader.spider.dao.QidianBookDAO;
import wang.smartdj.bookreader.spider.dao.QidianChapterDAO;
import wang.smartdj.bookreader.spider.dao.QidianSectionDAO;
import wang.smartdj.bookreader.spider.entity.qidian.QidianBookModel;
import wang.smartdj.bookreader.spider.entity.qidian.QidianChapterModel;
import wang.smartdj.bookreader.spider.entity.qidian.QidianSectionModel;
import wang.smartdj.bookreader.spider.global.Config;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Arthur on 2016/12/13.
 */
@Component("QidianPipeLine")
public class QidianPipeLine implements Pipeline {
    private final Logger logger = LoggerFactory.getLogger(Config.class);
    @Autowired
    private QidianBookDAO qidianBookDAO;

    @Autowired
    private QidianSectionDAO qidianSectionDAO;

    @Autowired
    private QidianChapterDAO qidianChapterDAO;

    @Override
    public void process(ResultItems resultItems, Task task) {
        QidianBookModel bookModel = (QidianBookModel) resultItems.get("bookModel");
        List<QidianChapterModel> chapterModels = (List<QidianChapterModel>) resultItems.get("chapterModels");
        List<QidianSectionModel> sectionModel = (List<QidianSectionModel>)resultItems.get("sectionModel");

        if(bookModel != null){
            QidianBookModel result = qidianBookDAO.saveAndFlush(bookModel);
//            QidianBookModel result = qidianBookDAO.save(bookModel);
            logger.debug("");
        }

        if(sectionModel != null){
            qidianSectionDAO.save(sectionModel);
            logger.debug("");
        }

        if(chapterModels != null){
            qidianChapterDAO.save(chapterModels);
            logger.debug("");
        }
    }
}
