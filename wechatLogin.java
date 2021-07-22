package cc.wdcloud.hrss.organ.onlytest.service;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class wechatLogin {

    public static void main(String[] args) {


        String code = "081PVD0w3OK6MW2Jpc0w3lOhXr4PVD0J";
        String url="https://api.weixin.qq.com/sns/jscode2session";
        String appid="wxc83de318c5a6def6";
        String secret="8f8796194182a977fa579e25985ca280";

        Map<String,String> param = new HashMap<>();

        param.put("appid",appid);
        param.put("secret",secret);
        param.put("js_code",code);
        param.put("grant_type","authorization_code");


        doGet(url,param,"UTF-8");


    }

    //通过获取手机号登陆
    public static String dologin(String phone){




        return "";
    }


    public static  String doGet(String url, Map<String, String> param, String enCoding) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            int success=200;
            if (response.getStatusLine().getStatusCode() ==success ) {
                HttpEntity entity = response.getEntity();
                resultString = EntityUtils.toString(entity, enCoding);
                //xml编码格式
//                String checkXml = checkXml(resultString);
//                if(!enCoding.equals(checkXml.toUpperCase())){
//                    resultString = doGet(url, param, checkXml);
//                }
//                System.out.println(JSON.toJSONString(resultString));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
