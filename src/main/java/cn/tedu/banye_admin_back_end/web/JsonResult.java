package cn.tedu.banye_admin_back_end.web;

import cn.tedu.banye_admin_back_end.ex.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {

    @ApiModelProperty("业务状态码")
    private Integer state;

    @ApiModelProperty("操作失败时的提示文本")
    private String message;

    @ApiModelProperty("操作成功时的响应数据")
    private T  data;

    public static JsonResult<Void> ok(){
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data){
        JsonResult jsonResult=new JsonResult();
        jsonResult.state=ServiceCode.OK.getValue();
        jsonResult.message=null;
        jsonResult.data=data;
        return jsonResult;
    }
    public static <T> JsonResult <T> fail(ServiceException e){
        return fail(e.getServiceCode(),e.getMessage());
    }

    public static <T> JsonResult <T> fail(ServiceCode serviceCode,String message){
        JsonResult jsonResult=new JsonResult();
        jsonResult.state=serviceCode.getValue();
        jsonResult.message=message;
        return jsonResult;
    }
}
