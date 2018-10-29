package com.common.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author sukang
 */
public class StatusInfo {


    @NotBlank(message = "申请编号(applyNo)不能为空")
    private String applyNo;

    @NotBlank(message = "类型(type)不能为空")
    @Max(value = 2,message = "类型(type)只能取1~2")
    @Min(value = 1,message = "类型(type)只能取1~2")
    private String type;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
