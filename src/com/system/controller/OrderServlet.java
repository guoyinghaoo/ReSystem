package com.system.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	 @Override
	    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        System.out.println("��ʼ����OrderServlet�ķ���");
	        String title = request.getParameter("username");
	        String total = request.getParameter("allprice");
	        String message = "����ʲô���黶ӭ��������";
	        //���ɶ�����
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
	        //��֧������������
	        //��ó�ʼ����AlipayClient
	        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
	                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
	                AlipayConfig.sign_type);
	      
	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	        alipayRequest.setReturnUrl(AlipayConfig.return_url);
	        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
	        
	        /*
	         *  AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
	                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
	                AlipayConfig.sign_type);
	      
	        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	        alipayRequest.setReturnUrl(AlipayConfig.return_url);
	        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
	        
	        //   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	         *
	         * 
	         */
	        //�̻������ţ��̻���վ����ϵͳ��Ψһ�����ţ�����
	        String out_trade_no = orderSn;
	        //���������
	        String total_amount = total;
	        //�������ƣ�����
	        String subject = title;
	        //��Ʒ�������ɿ�
	        String body = message;
	        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
	                + total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
	                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	        //�����BizContent����������ѡ����������������Զ��峬ʱʱ�����timeout_express������˵��
	        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
	        //        + "\"total_amount\":\""+ total_amount +"\"," 
	        //        + "\"subject\":\""+ subject +"\"," 
	        //        + "\"body\":\""+ body +"\"," 
	        //        + "\"timeout_express\":\"10m\"," 
	        //        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	        //��������ɲ��ġ�������վ֧����API�ĵ�-alipay.trade.page.pay-����������½�
	        AlipayTradePagePayResponse alipayResponse = null;
	        try {
	            alipayResponse=alipayClient.pageExecute(alipayRequest);
	            System.out.println(alipayResponse.getBody());
	             System.out.println(alipayResponse.getMsg());
	        } catch (AlipayApiException e) {
	            e.printStackTrace();
	        }
	        /*
	         *  AlipayTradePagePayResponse alipayResponse = null;
	        try {
	            alipayResponse=alipayClient.pageExecute(alipayRequest);
	            System.out.println(alipayResponse.getBody());
	             System.out.println(alipayResponse.getMsg());
	        } catch (AlipayApiException e) {
	            e.printStackTrace();
	        }
	         */
	        response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write(alipayResponse.getBody());
	    }

}
