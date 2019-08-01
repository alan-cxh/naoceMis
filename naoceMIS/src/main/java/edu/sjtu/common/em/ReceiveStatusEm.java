package edu.sjtu.common.em;

/**
 * 审批状态枚举
 * @author alan_chen
 * @date 2019/7/30
 */
public enum ReceiveStatusEm {

    YES("Y", "已领取"),
    NO("N", "未领取"),
            ;

    ReceiveStatusEm(String code, String text) {
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
