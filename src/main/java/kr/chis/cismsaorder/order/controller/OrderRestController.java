package kr.chis.cismsaorder.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    @PostMapping("reg")
    public ResponseEntity orderSave(){
        return null;
    }
}
