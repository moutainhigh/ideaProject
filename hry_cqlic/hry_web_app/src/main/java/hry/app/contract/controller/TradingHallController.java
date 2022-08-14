package hry.app.contract.controller;

import hry.app.lend.aspect.LoginValid;
import hry.app.lend.model.LendUser;
import hry.bean.ApiJsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2018/12/21 15:46
 */
@RestController
@Api(value = "合约交易大厅功能", description = "合约交易大厅功能", tags = "合约交易大厅功能")
@RequestMapping("/contract")
public class TradingHallController {

    /**
     * 首次进入交易大厅
     */
    @GetMapping("/exchange")
    @LoginValid(isInMethod = true)
    public ApiJsonResult exchange(HttpServletRequest request, @ApiParam(hidden = true) LendUser user) {










        return new ApiJsonResult();
    }


}