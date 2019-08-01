package edu.sjtu.naocemis.dto;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author alan_chen
 * @date 2019/7/31
 */
public class ConsumableQueryDto {

    private String applyerAccount;
    private String dept;
    private String applyDate;
    private String startDate;
    private String endDate;

    @Min(1)
    private int pageNum;
    @Min(1)
    private int pageSize;




    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getApplyerAccount() {
        return applyerAccount;
    }

    public void setApplyerAccount(String applyerAccount) {
        this.applyerAccount = applyerAccount;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
