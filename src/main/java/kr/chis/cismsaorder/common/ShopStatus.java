package kr.chis.cismsaorder.common;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
public enum ShopStatus {
    OPEN("OPEN", "요리준비"),
    CLOSE("CLOSE", "요리완료"),
    PREPARING("PREPARING","준비중")

    ;

    private String code;
    private String desc;


    ShopStatus(String code,String desc) {
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
