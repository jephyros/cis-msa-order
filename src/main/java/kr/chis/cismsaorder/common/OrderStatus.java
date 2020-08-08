package kr.chis.cismsaorder.common;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
public enum OrderStatus {
    ORDER_PENDING("ORDER_PENDING", "오더접수"),
    ORDER_ACCEPT("ORDER_ACCEPT", "오더확정"),
    ORDER_REJECT("ORDER_REJECT", "오더거절")

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
