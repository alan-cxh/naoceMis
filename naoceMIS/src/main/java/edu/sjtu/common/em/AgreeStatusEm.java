package edu.sjtu.common.em;

/**
 * 审批状态枚举
 * @author alan_chen
 * @date 2019/7/30
 */
public enum  AgreeStatusEm {

    AGREE("Y", "通过"),
    DIS_AGREE("N", "驳回"),
    Release("Y","已发布"),
    DIS_Release("N","暂未发布")
            ;

    AgreeStatusEm(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
