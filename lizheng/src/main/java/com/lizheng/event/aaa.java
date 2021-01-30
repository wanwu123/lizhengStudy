package com.lizheng.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface aaa {
    public void test(String a);

    public static void main(String[] args) {
        String a = "{hits=[{totalPrice=788.43, ecouponApplied=, productTypes=0, subTotal=998, storeId=0e49db5e-0834-41ba-84fe-f796497ba9fc, _score=1.0, userId=117, quoteId=4290009466, countryId=4b69c70f-cd09-459a-a25f-39682c2a606c, repId=null, itemModels=[{productImageUrl=//p1-ofp.static.pub/fes/cms/2020/12/14/rhrbu8kzjcki866arjwxjyq5d7ksxp452037.jpg, priceUnit=394.21, couponSource=-1, coupon=, totalPrice=788.42, classification={}, quoteId=4290009466, productName=, convenienceBundleItems={}, webPriceUnit=499, instantSaving=209.58, createTime=null, qty=2, parentGuid=, guid=9868910f-9e35-49d0-8fc3-be17b68e69fe, productTypeName=MTMproductModel, partNumber=20KNSH2N00, id=null, couponSaving=0, productTypeCode=0}], productNumbers=20KNSH2N00, expireTime=2021-01-28 16:34:12, shippingFee=0, instantSaveAmount=209.58, createTime=2021-01-27 16:34:12, storeName=America public store, currency=USD, id=173, businessType=fbcdf62e-9ecf-4f22-ae20-1c59319b93f1, uniqueId=null, email=null, status=null, couponSaveAmount=0}], aggs={}, aggsRange=null, total=1, correct=null, time_cost=4}";

        JSONObject o = JSON.parseObject(JSON.toJSONString(a), JSONObject.class);
        System.out.println(o);
    }
}
