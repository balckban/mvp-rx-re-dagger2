package com.idogs.laosiji.user.http.server;

import com.idogs.laosiji.user.http.vo.YbLoginResponse;
import com.idogs.laosiji.user.http.vo.YbVcodeResponse;
import com.idogs.laosiji.user.http.vo.YbYzCodeResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <b>类名称：</b> UserServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 17:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface UserServer {

    /**
     * 发送校验码,请求发送短信前校对校验码，防止短信盗刷
     * @param phone
     * @param cType
     * @return
     */
    @GET("/sms/gethash")
    Single<Response<YbVcodeResponse>> gethash(@Query("phone")String phone, @Query("cType") Integer cType);

    /**
     * 发送验证码
     * @param phone
     * @param vcode
     * @param type
     * @param cType
     * @return
     */
    @POST("/sms/getsms")
    Single<Response<YbYzCodeResponse>> getsms(@Query("phone")String phone, @Query("vcode")String vcode, @Query("type")Integer type,@Query("cType") Integer cType);

    /**
     * 用户通过手机号码登录
     * @param phone
     * @param yzcode
     * @param sid
     * @param type
     * @param deviceCode
     * @param clientid
     * @param deviceType
     * @return
     */
    @POST("/user/login")
    Single<Response<YbLoginResponse>> login(@Query("phone")String phone,@Query("yzcode")String yzcode,@Query("sid")String sid,@Query("type")Integer type,@Query("deviceCode")String deviceCode,@Query("clientid")String clientid,@Query("deviceType")String deviceType);
}
