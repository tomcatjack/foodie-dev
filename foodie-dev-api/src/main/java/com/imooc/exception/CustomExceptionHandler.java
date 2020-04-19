package com.imooc.exception;

import com.imooc.utils.IMOOCJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
/** 
 * 自定义异常
 *
 * @author luqi
 * @date 2020/4/6
 */ 
@RestControllerAdvice
public class CustomExceptionHandler {

    /** 
     * 上传文件超过500k，捕获异常：MaxUploadSizeExceededException
     *
     * @author luqi
     * @date 2020/4/6
     */ 
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        return IMOOCJSONResult.errorMsg("文件上传大小不能超过500k，请压缩图片或者降低图片质量再上传！");
    }
}
