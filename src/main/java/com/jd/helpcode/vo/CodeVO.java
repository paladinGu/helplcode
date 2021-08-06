package com.jd.helpcode.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeVO {

    @ApiModelProperty("活动code")
    private String activityCode;


    @ApiModelProperty("助力码")
    private String shareCode;


}
