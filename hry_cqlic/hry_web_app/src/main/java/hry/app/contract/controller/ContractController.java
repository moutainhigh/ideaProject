package hry.app.contract.controller;

import hry.app.lend.aspect.LoginValid;
import hry.app.lend.model.LendUser;
import hry.bean.ApiJsonResult;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2018/12/21 16:02
 */
@RestController
@RequestMapping("/user/contract")
@Api(value = "合约核心功能", description = "合约核心功能", tags = "合约核心功能")
public class ContractController {


    /**
     * 委托下单
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @PostMapping("/lendMoney")
    @ApiOperation(value = "委托下单", httpMethod = "POST")
    @LoginValid
    public ApiJsonResult addEntrust(HttpServletRequest request,
                                    @ApiParam(value = "币种", required = true) String coinCode,
                                    @ApiParam(value = "周期1当周 2次周 3季度", required = true) BigDecimal cycle,
                                    @ApiParam(value = "类型1买入 2卖出", required = true) Integer type,
                                    @ApiParam(hidden = true) LendUser user){







        return new ApiJsonResult();
    }


}