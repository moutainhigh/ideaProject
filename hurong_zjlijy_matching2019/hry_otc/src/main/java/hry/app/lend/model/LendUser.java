package hry.app.lend.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/10/26 11:54
 * @Description:
 */
public class LendUser {

    @ApiModelProperty(hidden = true)
    private Long customerId;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}