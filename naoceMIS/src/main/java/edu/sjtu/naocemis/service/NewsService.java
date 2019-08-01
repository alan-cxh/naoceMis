package edu.sjtu.naocemis.service;

import edu.sjtu.naocemis.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getById(String id);

    void saveNews(News news);

    News getByFlowId(String flowId);

    void updateNews(News news);

    boolean deleteByFlowId(String s);

}