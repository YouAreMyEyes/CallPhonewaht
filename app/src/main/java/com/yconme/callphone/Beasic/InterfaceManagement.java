package com.yconme.callphone.Beasic;

/**
 * Created by LDX on 2017/6/26.
 */

public interface InterfaceManagement {

    interface PathUrl {
        //登陆地址http://call.ycome.com/
        String login = "http://call.ycome.com/api/employee/login";
        //完善信息
        String Perfectinformation = "http://call.ycome.com/api/employee/info";
        //获取电话列表
        String Telephonelist = "http://call.ycome.com/api/merchant/messages";
        //上传通话记录
        String Uploadcalllog = "http://call.ycome.com/api/tel/submit";
        //获取打电话单条数据
        String Telephonedata = "http://call.ycome.com/api/tel/first";
        //上传通话录音
        String Uploadrecording = "http://call.ycome.com/api/tel/upload";
        //获取电话记录
        String Telephonerecording = "http://call.ycome.com/api/tel/total";
        //电话列表详情
        String Listdetails = "http://call.ycome.com/api/tel/records";
    }


}
