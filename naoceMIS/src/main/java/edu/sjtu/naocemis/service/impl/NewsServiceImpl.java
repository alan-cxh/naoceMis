package edu.sjtu.naocemis.service.impl;

import edu.sjtu.naocemis.dao.NewsDao;
import edu.sjtu.naocemis.entity.News;
import edu.sjtu.naocemis.service.NewsService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author hongzexuan
 * @date 2019/7/31
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    NewsDao NewsDao;
    @Autowired
    NewsService NewsService;

    @Override
    public List<News> getById(String id) {
        return NewsDao.findAll();
    }

    @Override
    public void saveNews(News news) {


        NewsDao.save(news);
    }

    @Override
    public News getByFlowId(String flowId) {
        List<News> byFlowId = NewsDao.getByFlowId(flowId);
        if (!CollectionUtils.isEmpty(byFlowId)) return byFlowId.get(0);
        return null;
    }

    @Override
    public void updateNews(News news) {
        NewsDao.save(news);
    }

    @Override
    public boolean deleteByFlowId(String flowId){
        try{
            News newsOld = NewsService.getByFlowId(flowId+"");
            if(newsOld != null){
                NewsDao.deleteById(newsOld.getId());
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

}

