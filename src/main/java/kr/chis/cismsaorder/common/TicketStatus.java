package kr.chis.cismsaorder.common;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
public enum  TicketStatus {
    TICKET_OPEN("TICKET_OPEN", "요리준비"),
    TICKET_CLOSE("TICKET_CLOSE", "요리완료")
            ;

    private String code;
    private String desc;


    TicketStatus(String code,String desc) {
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
