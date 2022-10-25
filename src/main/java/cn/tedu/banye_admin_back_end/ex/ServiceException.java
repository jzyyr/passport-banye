package cn.tedu.banye_admin_back_end.ex;

import cn.tedu.banye_admin_back_end.web.ServiceCode;

public class ServiceException extends RuntimeException{

    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode,String message){
        super(message);
        this.serviceCode=serviceCode;
    }

    public ServiceCode getServiceCode(){
        return serviceCode;
    }
}
