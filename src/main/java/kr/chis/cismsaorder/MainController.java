package kr.chis.cismsaorder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Controller
public class MainController {
    @GetMapping("")
    public String mainindex(){
        return "index";
    }
}
