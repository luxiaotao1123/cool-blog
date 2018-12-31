package com.cool.blog.common.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.cool.blog.common.constant.AlipayConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service("alipayService")
public class AlipayService {

    /**
     * 电脑网站支付
     */
    public String execute(String orderId){
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.APP_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        // 同步回调
        alipayRequest.setReturnUrl(AlipayConfig.RETURN_URL);
        // 异步回调（以异步回调为准）
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        // 唯一订单号
        String out_trade_no = orderId;
        // 付款金额
        String total_amount = "100";
        // 订单名称
        String subject = "cool";
        // 商品描述，可空
        String body = "cool-core";
        // 销售产品码
        String product_code = "FAST_INSTANT_TRADE_PAY";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"" + product_code + "\"}");
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    /**
     * 同步通知
     */
    public String alipayReturn(HttpServletRequest request){
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }

        System.out.println("同步：");
        System.out.println(JSONObject.toJSONString(params));

        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.APP_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(signVerified) {
            // 订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);;
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);;
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);
            System.out.println("同步通知");
            System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            return "ok";
        }
        System.err.println("fail");
        return "fail";
    }


    /**
     * 异步通知
     */
    public String alipayNotify(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1)
                        ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }
        System.out.println("异步：");
        System.out.println(JSONObject.toJSONString(params));
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.APP_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGN_TYPE); //调用SDK验证签名
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (signVerified){
            // 订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);;
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);;
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes(StandardCharsets.ISO_8859_1),
                    StandardCharsets.UTF_8);

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            System.out.println("异步通知");
            System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            return "ok";
        }
        System.err.println("fail");
        return "fail";
    }

    /**
     * 交易查询
     */
    public String alipayQuery() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.APP_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        // 唯一订单号
        String out_trade_no = "1234567890";
//        //支付宝交易号
//        String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
//        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");

        //请求
       return alipayClient.execute(alipayRequest).getBody();
    }

    /**
     * 关闭交易
     */
    public String closePay(){
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.APP_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);

        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();

        // 唯一订单号
        String out_trade_no = "1234567890";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 退款
     */
    public String alipayRefund(){
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.APP_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        // 唯一订单号
        String out_trade_no = "1234567890";

        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = "100";

        //退款的原因说明
        String refund_reason = "其他";

        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = "0987654321";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//                + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 退款查询
     */
    public String alipayRefundQuery(){
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.URL,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.APP_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);

        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();


        // 唯一订单号
        String out_trade_no = "1234567890";

        //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
        String out_request_no = "0987654321";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//                +"\"trade_no\":\""+ trade_no +"\","
                +"\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;


    }


}
