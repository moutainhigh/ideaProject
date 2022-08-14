package com.zming.cloudcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult<T> {
    private Long code;
    private Boolean success = false;
    private T data;
    public JsonResult(long code, boolean success, T data) {
        this.code = code;
        this.success = success;
        this.data = data;
    }
}
