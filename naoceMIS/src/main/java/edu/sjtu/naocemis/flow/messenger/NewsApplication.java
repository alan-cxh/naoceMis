package edu.sjtu.naocemis.flow.messenger;

import edu.sjtu.common.em.AgreeStatusEm;
import edu.sjtu.infoplus.applicationToolkit.AbstractMessenger;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusEvent;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusResponse;
import edu.sjtu.naocemis.entity.News;
import edu.sjtu.naocemis.flow.model.NewsForm;
import edu.sjtu.naocemis.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import sun.plugin.dom.html.ns4.NS4DOMObject;

import java.util.Date;


public class NewsApplication extends AbstractMessenger {
    @Autowired
    NewsService NewsService;

    //命名规则：onStep[步骤名称]Rendering
    //命名规则：onStep[步骤名称]Action[动作名称]Doing


    //终止流程操作
    public InfoPlusResponse onInstanceKilled(InfoPlusEvent e){
        long flowId = e.getStep().getInstance().getEntryId();
        NewsService.deleteByFlowId(flowId + "");
        return null;
    }

    //申请doing
    public InfoPlusResponse onStepUserFillActionNextDoing(InfoPlusEvent e){
        NewsForm newsForm = e.toBean(NewsForm.class);
        //退回修改时，删除旧的数据
        long flowId = e.getStep().getInstance().getEntryId();
        NewsService.deleteByFlowId(flowId+"");
        //save
        News news = newsForm.setData(e);
        NewsService.saveNews(news);
        return new InfoPlusResponse(e, newsForm);
    }

    //部门负责人审批doing
    public InfoPlusResponse onStepDeptCheckActionAgreeDoing(InfoPlusEvent e){
        NewsForm newsForm = e.toBean(NewsForm.class);
        long flowId=e.getStep().getInstance().getEntryId();
        News news = NewsService.getByFlowId(flowId + "");
        news.setDeptLeader(newsForm.getDeptLeader());
        news.setDeptLeaderDate(newsForm.getDeptLeaderDate());
        news.setDeptLeaderSug(newsForm.getDeptLeaderSug());
        news.setUpdateTime(new Date());
        //审核通过，修改审核状态
        news.setDeptLeaderCheckStatus(AgreeStatusEm.AGREE.getCode());
        NewsService.updateNews(news);
        return new InfoPlusResponse(e,newsForm);
    }

    //党政办宣传干事审批doing
    public InfoPlusResponse onStepSecretaryCheckActionAgreeDoing(InfoPlusEvent e){
        NewsForm newsForm = e.toBean(NewsForm.class);
        long flowId=e.getStep().getInstance().getEntryId();
        News news = NewsService.getByFlowId(flowId + "");
        news.setSecretary(newsForm.getSecretary());
        news.setSecretaryDate(newsForm.getSecretaryDate());
        news.setSecretarySug(news.getSecretarySug());
        news.setUpdateTime(new Date());
        //审核通过，修改审核状态
        news.setSecretaryCheckStatus(AgreeStatusEm.AGREE.getCode());
        NewsService.updateNews(news);
        return new InfoPlusResponse(e,newsForm);
    }

    //院领导审批doing
    public InfoPlusResponse onStepLeaderCheckActionAgreeDoing(InfoPlusEvent e){
        NewsForm newsForm = e.toBean(NewsForm.class);
        long flowId=e.getStep().getInstance().getEntryId();
        News news = NewsService.getByFlowId(flowId + "");
        news.setColLeader(newsForm.getColLeader());
        news.setColLeaderDate(newsForm.getColLeaderDate());
        news.setColLeaderSug(newsForm.getSecretarySug());
        news.setUpdateTime(new Date());
        //审核通过，修改审核状态
        news.setColLeaderCheckStatus(AgreeStatusEm.AGREE.getCode());
        NewsService.updateNews(news);
        return new InfoPlusResponse(e,newsForm);
    }

    //宣传干事发布doing
    public InfoPlusResponse onStepSecretaryConfirmActionSubmitDoing(InfoPlusEvent e){
        NewsForm newsForm=e.toBean(NewsForm.class);
        long flowId = e.getStep().getInstance().getEntryId();
        News news = NewsService.getByFlowId(flowId + "");
        news.setUpdateTime(new Date());
        news.setSecretaryReleaseStatus(AgreeStatusEm.Release.getCode());
        NewsService.updateNews(news);
        return new InfoPlusResponse(e, newsForm);
    }

}
