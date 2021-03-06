package kr.chis.cismsaorder.order.event;

import lombok.Getter;

/**
 * @author InSeok
 * Date : 2020/08/09
 * Remark :
 */
@Getter
public class OrderAcceptMessage {
    private Boolean success;
    private String orderId;
    private String errCode;
    private String errMessage;

    public Boolean isSuccess(){
        return success;
    }

    public void success(String orderId){
        this.success = true;
        this.orderId = orderId;
        this.errCode="";
        this.errMessage="";

    }
    public void fail(String orderId,String errCode,String errMessage){
        this.success = false;
        this.orderId = orderId;
        this.errCode=errCode;
        this.errMessage=errMessage;
    }

    public OrderAcceptMessage() {
        this.success = true;
        this.orderId = "";
        this.errCode="";
        this.errMessage="";
    }
}
