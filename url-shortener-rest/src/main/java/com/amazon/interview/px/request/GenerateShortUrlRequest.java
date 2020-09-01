package com.amazon.interview.px.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GenerateShortUrlRequest {
    /**
     * 要生成的短链接地址
     */
    @NotNull
    @ApiModelProperty(value = "Long url", example = "https://wwww.baidu.com")
    private String url;
}
