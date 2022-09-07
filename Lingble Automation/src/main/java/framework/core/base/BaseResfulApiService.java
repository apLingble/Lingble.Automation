package framework.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import framework.core.base.model.ResfulApiRequestModel;
import framework.core.helpers.JsonHelper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import javax.annotation.concurrent.NotThreadSafe;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Map;

@Component
public abstract class BaseResfulApiService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseResfulApiService.class);
    protected static final String HIGHLIGHT = "=============";
    private static final String RESFUL_RESULT_JSON_PATH = "resultInfo.success";
    private JSONObject latestResponse;
    private JSONArray latestArrayResponse;
    static String url;
    private static String httpStatus;
    public abstract void check();
    private static boolean disabledRedirect;
    private static boolean trustSSL;
    private String response;


    public JSONObject sendResfulApiRequest(ResfulApiRequestModel resfulApiRequestModel) {
        this.trustSSL=resfulApiRequestModel.getTrustSSL();
        this.disabledRedirect=resfulApiRequestModel.getdisabledRedirect();
        resfulApiRequestModel.composeRequest();
        this.url = resfulApiRequestModel.getUrl();
        LOGGER.info(HIGHLIGHT + " RESFUL POST NAME " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getFunction());
        LOGGER.info(HIGHLIGHT + " RESFUL POST URL " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getUrl());
        LOGGER.info(HIGHLIGHT + " RESFUL POST REQUEST HEADER " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getHeaders());
        LOGGER.info(HIGHLIGHT + " RESFUL POST REQUEST BODY " + HIGHLIGHT + "\n"
                +  JsonHelper.getPrettyJson(resfulApiRequestModel.getBody().toString()));

        try {
            String response = this.sendPost(
                    resfulApiRequestModel.getUrl(),
                    resfulApiRequestModel.getBody().toString(),resfulApiRequestModel.getHeaders(),resfulApiRequestModel.getUseName(),resfulApiRequestModel.getPassword()
            );

            LOGGER.info(HIGHLIGHT + " RESFUL POST RESPONSE " + HIGHLIGHT + "\n"
                    + JsonHelper.getPrettyJson(response));
            this.response = response;
            Object responseJSON = JSON.parse(JsonHelper.getPrettyJson(response));
            if (responseJSON instanceof JSONObject) {
                latestResponse = JSONObject.parseObject(JsonHelper.getPrettyJson(response));
            }else {
                LOGGER.info(HIGHLIGHT + " RESFUL POST RESPONSE " + HIGHLIGHT + "\n"
                        + JsonHelper.getPrettyJson(response));
                this.latestArrayResponse = (JSONArray) responseJSON;
            }
            return latestResponse;
        } catch (Exception e) {
            LOGGER.error(e.getStackTrace().toString());
            throw new RuntimeException("Send RESFUL request failed!");
        }
    }

    public static String sendPost(String url, String data,Map<String,String> header,String useName,String password) {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
        try {
            socketFactoryRegistry = createSocketFactoryConfigration();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        final BasicCookieStore cookieStore = new BasicCookieStore();
        String response = null;
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                if (disabledRedirect==true && trustSSL==true) {
                    httpclient = HttpClients.custom()
                            .disableRedirectHandling()
                            .setSSLContext(sslContext)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .build();
                }else if (disabledRedirect==false && trustSSL==true){
                    httpclient = HttpClients.custom()
                            .setSSLContext(sslContext)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .build();
                }else if (disabledRedirect==true && trustSSL==false){
                    httpclient = HttpClients.custom()
                            .disableRedirectHandling()
                            .build();
                }else {
                    httpclient = HttpClients.custom().build();
                }
                HttpPost httppost = new HttpPost(url);
                StringEntity stringentity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
                httppost.setEntity(stringentity);
                if(null !=useName && "Basic".equals(useName)){
                    httppost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + password);
                }else if (null != useName && !"".equals(useName) ) {
                    String encoding = Base64.getEncoder().encodeToString((useName + ":" + password).getBytes("utf-8"));
                    httppost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
                }

                for(String key : header.keySet())
                {
                    httppost.addHeader(key,header.get(key));
                }

                httpresponse = httpclient.execute(httppost);
                response = EntityUtils.toString(httpresponse.getEntity());
                httpStatus = httpresponse.getStatusLine().toString();
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    public JSONObject sendDeleteResfulApiRequest(ResfulApiRequestModel resfulApiRequestModel) {

        resfulApiRequestModel.composeRequest();
        this.url = resfulApiRequestModel.getUrl();
        LOGGER.info(HIGHLIGHT + " RESFUL Delete NAME " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getFunction());
        LOGGER.info(HIGHLIGHT + " RESFUL Delete URL " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getUrl());
        LOGGER.info(HIGHLIGHT + " RESFUL Delete REQUEST HEADER " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getHeaders());
        LOGGER.info(HIGHLIGHT + " RESFUL Delete REQUEST BODY " + HIGHLIGHT + "\n"
                +  JsonHelper.getPrettyJson(resfulApiRequestModel.getBody().toString()));

        try {
            String response = this.sendDelete(
                    resfulApiRequestModel.getUrl(),
                    resfulApiRequestModel.getBody().toString(),resfulApiRequestModel.getHeaders(),resfulApiRequestModel.getUseName(),resfulApiRequestModel.getPassword()
            );

            LOGGER.info(HIGHLIGHT + " RESFUL Delete RESPONSE " + HIGHLIGHT + "\n"
                    + JsonHelper.getPrettyJson(response));
            latestResponse = JSONObject.parseObject(JsonHelper.getPrettyJson(response));
            return latestResponse;
        } catch (Exception e) {
            LOGGER.error(e.getStackTrace().toString());
            throw new RuntimeException("Send RESFUL request failed!");
        }
    }


    public static String sendDelete(String url, String data,Map<String,String> hearder,String useName,String password) {
        String response = null;
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.custom()
                        .setSSLContext(sslContext)
                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                        .build();
                HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
                StringEntity stringentity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
                httpDelete.setEntity(stringentity);
                if(null !=useName && "Basic".equals(useName)){
                    httpDelete.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + password);
                }else if (null != useName && !"".equals(useName) ) {
                    String encoding = Base64.getEncoder().encodeToString((useName + ":" + password).getBytes("utf-8"));
                    httpDelete.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
                }

                for(String key : hearder.keySet())
                {
                    httpDelete.addHeader(key,hearder.get(key));
                }

                httpresponse = httpclient.execute(httpDelete);
                response = EntityUtils.toString(httpresponse.getEntity());
                httpStatus = httpresponse.getStatusLine().toString();
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    private static Registry<ConnectionSocketFactory> createSocketFactoryConfigration() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        Registry<ConnectionSocketFactory> socketFactoryRegistry;
        final SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
        final SSLConnectionSocketFactory cnnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register(registryHost(), cnnectionSocketFactory)
                .build();

        return socketFactoryRegistry;
    }
    public static String registryHost(){
        String host = null;
        if (url.toLowerCase().startsWith("https")){
           return host = "https";
        }else {
            return host="http";
        }

    }

    public JSONObject sendGETResfulApiRequest(ResfulApiRequestModel resfulApiRequestModel) {

        resfulApiRequestModel.composeGetRequest();

        LOGGER.info(HIGHLIGHT + " RESFUL GET NAME " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getFunction());
        LOGGER.info(HIGHLIGHT + " RESFUL GET URL " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getUrl());
        LOGGER.info(HIGHLIGHT + " RESFUL GET REQUEST HEADER " + HIGHLIGHT + "\n"
                + resfulApiRequestModel.getHeaders());

        // send request
        try {
            String response = this.sendGet(resfulApiRequestModel.getUrl()
                    ,resfulApiRequestModel.getHeaders(),resfulApiRequestModel.getUseName(),resfulApiRequestModel.getPassword()
            );
            LOGGER.info(HIGHLIGHT + " RESFUL GET RESPONSE " + HIGHLIGHT + "\n"
                    + JsonHelper.getPrettyJson(response));
            Object responseJSON = JSON.parse(JsonHelper.getPrettyJson(response));
            if (responseJSON instanceof JSONObject) {
                latestResponse = JSONObject.parseObject(JsonHelper.getPrettyJson(response));
            }else{
                this.latestArrayResponse = (JSONArray) responseJSON;
            }
        } catch (Exception e) {
            LOGGER.error(e.getStackTrace().toString());
            throw new RuntimeException("Send RESFUL request failed!");
        }
        return latestResponse;
    }


    public static String sendGet(String url,Map<String,String> hearder,String useName,String password) {
        String response = null;
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.custom()
                        .setSSLContext(sslContext)
                        .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                        .build();
                HttpGet httpGet = new HttpGet(url);
                if(null !=useName && "Basic".equals(useName)){
                    httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + password);
                }
                else if (null != useName && !"".equals(useName) ) {
                    String encoding = Base64.getEncoder().encodeToString((useName + ":" + password).getBytes("utf-8"));
                    httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
                }

                for(String key : hearder.keySet())
                {
                    httpGet.addHeader(key,hearder.get(key));
                }
                //  httpGet.addHeader();
                httpresponse = httpclient.execute(httpGet);
                response = EntityUtils.toString(httpresponse.getEntity());
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    public JSONObject getLatestResponse() {
        return latestResponse;
    }
    public JSONArray getLatestArrayResponse() {
        return latestArrayResponse;
    }
    public String getHttpStatus() {
        return httpStatus;
    }

    public final void checkResponse(Map<String, Object> checkPoints) {
        if (null == getLatestResponse()) {
            throw new AssertionError("Latest response is null!");
        }
        for (Map.Entry<String, Object> entry: checkPoints.entrySet()) {
            Assert.assertEquals(
                    JsonHelper.getValueFromJsonObject(latestResponse, entry.getKey()),
                    entry.getValue());
            LOGGER.info("Check RESFUL parameter <" + entry.getKey() + "> succeeded! Value: " + entry.getValue());
        }
    }

    public final void checkResponse(String jsonPath, String expectedValue) {
        if (null == getLatestResponse()) {
            throw new AssertionError("Latest response is null!");
        }
        if(expectedValue.isEmpty()){
            int x=1;
            try {
                x= JsonHelper.getValueFromJsonObject(latestResponse, jsonPath).length();
            }catch (Exception e){ }
            if (x==0){
                Assert.assertTrue(JsonHelper.getValueFromJsonObject(latestResponse, jsonPath).equals(expectedValue));
            }else {
                Assert.assertEquals(
                        JsonHelper.getValueFromJsonObject(latestResponse, jsonPath), null);
            }
        } else {
            Assert.assertEquals(
                    JsonHelper.getValueFromJsonObject(latestResponse, jsonPath),
                    expectedValue);
        }
        LOGGER.info("Check RESFUL api parameter <" + jsonPath + "> succeeded! Value: " + expectedValue);
    }
    public final void checkResponse(String jsonPath,int arrayIndex, String expectedValue) {
        if (null == getLatestArrayResponse()) {
            throw new AssertionError("Latest response is null!");
        }
        if(expectedValue.isEmpty()){
            int x=1;
            try {
                x= JsonHelper.getValueFromJsonObject(latestArrayResponse.getJSONObject(arrayIndex), jsonPath).length();
            }catch (Exception e){ }
            if (x==0){
                Assert.assertTrue(JsonHelper.getValueFromJsonObject(latestArrayResponse.getJSONObject(arrayIndex), jsonPath).equals(expectedValue));
            }else {
                Assert.assertEquals(
                        JsonHelper.getValueFromJsonObject(latestArrayResponse.getJSONObject(arrayIndex), jsonPath), null);
            }
        } else {
            Assert.assertEquals(
                    JsonHelper.getValueFromJsonObject(latestArrayResponse.getJSONObject(arrayIndex), jsonPath),
                    expectedValue);
        }
        LOGGER.info("Check RESFUL api parameter <" + jsonPath + "> succeeded! Value: " + expectedValue);
    }
    public void checkHttpStatu(String expectedHttpStatus){
        if(getHttpStatus() == null){
            throw new AssertionError("Latest response is null!");
        }
        Assert.assertEquals(getHttpStatus().trim(),expectedHttpStatus.trim());
    }
    public final void checkMessageResponse(String jsonPath, String expectedValue) {
        if (null == getLatestResponse()) {
            throw new AssertionError("Latest response is null!");
        }
        if(expectedValue.isEmpty()){
            if (JsonHelper.getValueFromJsonObject(latestResponse, jsonPath).length() ==0){
                Assert.assertTrue(JsonHelper.getValueFromJsonObject(latestResponse, jsonPath).equals(expectedValue));
            }else {
                Assert.assertEquals(
                        JsonHelper.getValueFromJsonObject(latestResponse, jsonPath), null);
            }
        } else {
            try {
                String actualValue = JsonHelper.getValueFromJsonObject(latestResponse, jsonPath).trim();
                Assert.assertTrue(actualValue.equals(expectedValue));
            }catch (AssertionError a){
                String actualValue = JsonHelper.getValueFromJsonObject(latestResponse, jsonPath).trim();
                Assert.assertTrue(expectedValue.contains(actualValue));
            }
        }
        LOGGER.info("Check RESFUL api parameter <" + jsonPath + "> succeeded! Value: " + expectedValue);
    }

    public String getResponseValue(String jsonPath) {
        if (null == getLatestResponse()) {
            throw new AssertionError("Latest response is null!");
        }
        return JsonHelper.getValueFromJsonObject(latestResponse, jsonPath);
    }

    @NotThreadSafe
    static
    class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public String getMethod() {
            return METHOD_NAME;
        }
        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        public HttpDeleteWithBody(final URI uri) {
            super();
            setURI(uri);
        }

        public HttpDeleteWithBody() {
            super();
        }
    }
}