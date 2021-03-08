package com.ta.xutiansheng.xtsapp.api;


import com.ta.xutiansheng.xtsapp.api.bean.BaseResponse;
import com.ta.xutiansheng.xtsapp.api.bean.LoginResult;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.bean.IpLocationResult;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;
import com.ta.xutiansheng.xtsapp.bean.OrderBean;
import com.ta.xutiansheng.xtsapp.bean.ShopBean;
import com.ta.xutiansheng.xtsapp.bean.ShopMenu;
import com.ta.xutiansheng.xtsapp.bean.UserBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * @author xutiansheng
 */
public interface IApiService {
    //登陆接口
    @POST(value = "/users/loging")
    Observable<BaseResponse<LoginResult>> login(@Body RequestBody body);

    //获取商家列表
    @POST(value = "/outfood/getShopList")
    Observable<BaseResponse<List<ShopBean>>> getShopList(@Body RequestBody body);
    //获取商家菜单

    @POST(value = "/shop/getmenuList")
    Observable<BaseResponse<List<ShopMenu>>> getMenuListForshopId(@Body RequestBody body);

    @POST(value = "/address/getAddressList")
    Observable<BaseResponse<List<AddressResult>>> getAddressList(@Body RequestBody body);

    @POST(value = "/address/addaddress")
    Observable<BaseResponse<Object>> createAddress(@Body RequestBody body);

    @POST(value = "/address/deleteAddress")
    Observable<BaseResponse<Object>> deleteAddress(@Body RequestBody body);

    @POST(value = "/address/getSeachAddressList")
    Observable<BaseResponse<List<BaiduResult>>> getSeachAddressList(@Body RequestBody body);

    //创建订单
    @POST(value = "/order/createOrder")
    Observable<BaseResponse<String>> createOrder(@Body RequestBody body);

    //获取商店详情　
    @POST(value = "shop/getShop")
    Observable<BaseResponse<ShopBean>> getShopIfo(@Body RequestBody body);

    @POST(value = "order/getOrderInfo")
    Observable<BaseResponse<OrderBean>> getOrderInfo(@Body RequestBody requestBody);

    @POST(value = "order/getOrderList")
    Observable<BaseResponse<List<OrderBean>>> getOrderList(@Body RequestBody requestBody);

    //获取地址
    @POST(value = "address/getaddressForId")
    Observable<BaseResponse<AddressResult>> getAddressForId(@Body RequestBody requestBody);

    @POST(value = "users/getuserInfo")
    Observable<BaseResponse<UserBean>> getuserInfo(@Body Map<String, Object> map);

    @POST(value = "outfood/getShopListForKeyWord")
    Observable<BaseResponse<List<ShopBean>>> searchShopForKeyWord(@Body RequestBody requestBody);
    @Multipart
    @POST(value = "users/uploadHeadimg")
    Observable<BaseResponse<List<String>>> upLoadHeadImg(@Part List<MultipartBody.Part> parts, @PartMap Map<String, RequestBody> requestBodyMap);

    @POST(value = "address/getAddressForIp")
    Observable<BaseResponse<IpLocationResult>> locationIp();

    @POST(value = "users/getUserPart")
    Observable<BaseResponse<List<UserBean>>> getUserList(@Body RequestBody requestBody);

    @POST(value = "msg/getmessage")
    Observable<BaseResponse<List<MessageBean>>> getMessageList(@Body RequestBody requestBody);

    @POST(value = "users/updateUserInfo")
    Observable<BaseResponse<Object>> updateUserinfo(@Body RequestBody requestBody);

    //更新订单状态
    @POST(value = "order/updateStateForOrderid")
    Observable<BaseResponse<String>> updateOrderState(@Body RequestBody body);

    //退款订单状态修改
    @POST(value = "order/reOrderState")
    Observable<BaseResponse<String>> reOrderstate(@Body RequestBody body);
}
