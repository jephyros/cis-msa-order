package kr.chis.cismsaorder.common;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
public enum OrderStatus {
    ORDER_PENGIND("ORDER_PENGIND", "오더접수"),
    ORDER_ACCEPT("ORDER_ACCEPT", "오더확정")
    ;

    private String code;
    private String desc;


    OrderStatus(String code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
