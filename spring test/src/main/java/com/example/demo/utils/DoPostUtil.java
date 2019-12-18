package com.ebuy.cloud.service.hwc.servicehwc.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
* 功能描述:
* 〈工具类〉
* @Author: zouhg
* @Date: 2019-03-14
*/
public class DoPostUtil {

    /**
     * 功能描述:
     * 〈校验字符串是否为正确的时间格式〉
     * @Author: zouhg
     * @Date: 2019-03-14
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess=false;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=true;
        }
        return convertSuccess;
    }

    /**
    * 功能描述:
    * 〈字符串转为时间格式〉
    * @Author: zouhg
    * @Date: 2019-03-14
    */
    public static LocalDateTime getDate(String date)  {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDateTime time = LocalDateTime.now();
        // String localTime = df.format(time);
        return  LocalDateTime.parse(date, df);
        // System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        // System.out.println("String类型的时间转成LocalDateTime：" + ldt);
    }

    /**
     * 请求url
     * @param url url路径
     * @param json 参数
     * @return JSONObject
     * @throws Exception
     */
    public static JSONObject doPost(String url, JSONObject json) throws Exception{
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0");
        JSONObject response = null;
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()){
            StringEntity s = new StringEntity(json.toString(),"UTF-8");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            setTimeOut(post);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
            }
        }
        return response;
    }
    private static void setTimeOut(HttpPost post) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(30000)
                .setConnectionRequestTimeout(30000)
                .setSocketTimeout(30000)
                .setRedirectsEnabled(true)
                .setCircularRedirectsAllowed(false)
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build();
        post.setConfig(requestConfig);
    }
}
