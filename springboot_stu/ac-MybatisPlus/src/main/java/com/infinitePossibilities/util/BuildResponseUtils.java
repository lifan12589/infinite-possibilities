package com.infinitePossibilities.util;

import com.infinitePossibilities.enums.CodeMsgEnum;
import com.infinitePossibilities.model.vo.ResponseVo;

/**
 * 构建返回结果工具类
 *
 * @author 154594742@qq.com
 * @date: 2021/2/22 10:02:00
 */
public final class BuildResponseUtils {

    /**
     * 构建正确请求的response
     *
     * @return ResponseVo 统一的返回结果
     */
    public static ResponseVo<?> success() {
        ResponseVo<?> response = new ResponseVo<>();
        response.setCode(CodeMsgEnum.SUCCESS.code);
        response.setMsg(CodeMsgEnum.SUCCESS.msg);
        return response;
    }

    /**
     * 构建业务异常的response
     * @param codeMsgEnum 枚举
     * @return ResponseVo 统一的返回结果
     */
    public static ResponseVo<?> success(CodeMsgEnum codeMsgEnum) {
        ResponseVo<?> response = new ResponseVo<>();
        response.setCode(codeMsgEnum.code);
        response.setMsg(codeMsgEnum.msg);
        return response;
    }

    /**
     * 构建自定义code和msg的业务异常
     *
     * @param code 自定义code
     * @param msg 自定义msg
     * @return ResponseVo 统一的返回结果
     */
    public static ResponseVo<?> success(String code, String msg) {
        ResponseVo<?> response = new ResponseVo<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    /**
     * 构建系统异常的response(只用于系统异常)
     * @return ResponseVo 统一的返回结果
     */
    public static ResponseVo<?> error() {
        ResponseVo<?> response = new ResponseVo<>();
        response.setCode(CodeMsgEnum.FAIL.code);
        response.setMsg(CodeMsgEnum.FAIL.msg);
        return response;
    }

    /**
     * 构建返回结果
     * @param obj 结果数据
     * @param <T> 结果数据的泛型
     * @return ResponseVo 统一的返回结果
     */
    public static <T> ResponseVo<T> buildResponse(T obj) {
        ResponseVo<T> response = new ResponseVo<>();
        response.setData(obj);
        response.setCode(CodeMsgEnum.SUCCESS.code);
        response.setMsg(CodeMsgEnum.SUCCESS.msg);
        return response;
    }
}
