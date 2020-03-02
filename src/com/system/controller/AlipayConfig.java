package com.system.controller;


import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092500590318";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHZYrl1D6iBTS2Sw8VB0/t7fyFk5Efx7s7LrrTKQ6aQp1tFF3cJUBLieFE8bXIfjf5fWc6bTk63zpY5BOddIOJDr3cSSfo2dbFXOATS7qGzlHatsMmNq0b64SBTOiaEt2GQHL5t0piBBkcEaEryuRT84x1rdwcwvaHtphFQjgxbSDGuF7rJghYmaJeKNUKAtxBe4MtcX+tv0hczMG5OP2BpAgg/UHOMhLT5muA5KCq6m+I2vxGBGZTcmhGIwlNJer0za1M/7CX1ikYCklJuLhSCuE3YcrQebvuhRLUGQDEtXKoxobsDlRKECPrd0ISbh7tSEa8UHR0NDW8pi7AQ5eLAgMBAAECggEAeUz1pXYDLvBvCyovtu04JLdj1uAf0lMEYL86zVcpJ/aqR0FJ3aLZ+VLrqPLLo3XA/BnnU6bq8N1wzpZ3JEdwWGgGnMZFpQ0UpvjQHzlnQMKy/rliGGg/qpXTvmuCsBZatJ4k46fcUffN7yG0PSGmcbfujLdYTcQ1Tdy+ir4KzevewR8YWYmUdPMdQJntWLzmAXAWNFtqklRImgESOZUPQRjBiC/AW8a9CTBOWy+/68Z86pIOccy1IV3HFE0QJ19hnuD/yoVAnIaEvh7jRtG1jjxq4CYd0VunCwIFXXmoLTnv8I/BfIXr/PvBjLpswkXDWCcLAdBkkC/Gq5Q6ed/L8QKBgQDbCaST7PCAZTwla1JwDi0R5icoJjYHnXn/qoxORsYSn86JQqCrj2HXtLLcYz57njf0/vgaUzyOOwiD4PG/PB4WWYD3Z8hqfuEiyo8ifiVZ19Z/LP4/9pSDxaHEDZnSYgHhKY+/dFRlatjEvp0IjnEhJfoGd4d94I1Z2kaCPr2LVQKBgQCePqJw3dk+rBRBMKZstzbpyHRDtVnWaK5v76TGuzAA5DA6FoXrHxoSA9cVOf9/BLLt2bUCfi+g2jMTJyFG6GwnCbQwzyhrFvbH0/4JRQ/L5i3AMMVf96I6kEmwvZ+1jdlCxvTqPiYvEzmTX1e8vk6SIKkGDq/xdJj4+lDJxUdXXwKBgQDD3AmIJLSheuSfaSob/PEcMjsNUa26fXwbu3see3aeLLvYTPWystGAupm9LBkxHLdYoa1kjM13OR5SHpDMuYsF3R7PG8RARhg2rf3zj+0CO1gX/DmVJH6EnfIahzbmwrB/kFRzlCfrT/FpbDCq+AP86iJ6KePEEUkT8XRe/o8qyQKBgFaR83IFnAyyPR5wzAsbrDn3RMup0az/dbadBqsFyrwiGdQdcEPxruR+H1NoeEL3wh9nA6SKfenklR58QLM3AbvVjjNJkVYMqRMnAYn2sy6cDRlgVQosQh8Bwp6jDlVXda0XhPME7wS/4rah6TDDd95vaah7rntMi0diQYIwn9fHAoGAflpIGKZpgMAyipm66bUzf6fmb3V2tIUIyU6FkO/ttwihx+GcmL8HG3dg5Fgy7ZObbJvryL1y9D/2t9mNSbDVCKKFSvOME4ecuvG8lkewiNJ6uKI0ZbRSMDGt59i2KXzQRtRbMU39GsE7AAFcV1bnA0zmwlsVjFbOSLN31bBDI1c=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq3xAPSvIuUDUAgVSDY4I8YnYmsCCSVsJJ1zxPWXI57Bn+4cvxPFeUpo4tJ1vh0HLWXxSgW3qPWf4TUAyH+pihJK1JE0smA+0v7t6o9WlMyP50UaKMu8RQ1Mr6LEfWdMmkxoRLpjjGSeo4ErcN/ONt1x4ZSHbhvOFfrx9ylqdoD+4G2+Y4s9N4sb4TVlJTL0ymTa8cSR5GQtDJSaI4eUt/1CTm8uGcpvTjww6A7WE/kQz62qDEzVbpZNYU5dj2HyqaVaU7mu41sPd9jO17nyAehqCcnwrukeH7OmThBlFftNLAUDHlXjToY0OVhwiPEou3jeKWSjCGHQYriy8YgebSQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8081/Restaurant_System/notify";
	//http://localhost:8081/Restaurant_System/OrderServlet
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8081/Restaurant_System/notify";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

