package framework.core.base.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import framework.core.helpers.JsonHelper;
import framework.core.helpers.StringHelper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResfulApiRequestModel extends BaseHttpRequsetModel {
    private static final String CONTENT_TYPE_FORMDATA = "application/x-www-form-urlencoded";
    private static final String OPERATION_TYPE = "operationType";
    private static final String REQUEST_DATA = "requestData";
    private static final String CONTENT_TYPE = "application/json";
    private static final String RESFUL_BODY_JSON_PATH = "classpath:templates/";
    private String USE_NAME = "Value";
    private String PASSWORD = "Basic dXNzZDp1c3Nk";


    /** Override BaseHttpRequsetModel, change to String type **/
    private JSONObject body;
    private List<String> listValue;
    private String function;
    private String signature;
    private String template;

    private Map<String, String> bodyRewrite;
    private Map<String, Integer> bodyWriteInt;
    private Map<String, BigDecimal> bodyWriteFloat;
    private Map<String, List> bodyWriteList;

    private Map<String, String> urlRewrite;
    private JSON FormDataJSONBody;
    private Map<String, String> FormDataListbody;

    private String useName;

    private String password;

    private String hostURL;
    private String digitalSignature;
    private String privateKey;
    private String digitalSignatureURL;

    private List<NameValuePair> formDataBody;
    private List<NameValuePair> xWwwwUrlEncodedBody;

    private Map<String, Object> FormDataJSONbodyRewrite;

    public String getFunction() {
        return function;
    }
    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }


    public void setFunction(String function) {
        this.function = function;
    }

    public Map<String, String> getUrlRewrite() {
        return urlRewrite;
    }

    public void setUrlRewrite(Map<String, String> urlRewrite) {
        this.urlRewrite = urlRewrite;
    }


    public void setUrlRewrite(String key, String value) {
        if (null == urlRewrite) {
            urlRewrite = new HashMap<>();
        }
        urlRewrite.put(key, value);
    }

    private BigDecimal bigDecimal;
    private boolean bodyStatus;
    private Boolean trustSSL;
    private Boolean disabledRedirect;

    public void setTrustSSL(boolean res){
        this.trustSSL = res;
    }
    public void setDisabledRedirect(boolean res){
        this.disabledRedirect = res;
    }
    public boolean getTrustSSL()
    {
       trustSSL = (trustSSL==null) ? false : trustSSL;
        return trustSSL;
    }public boolean getdisabledRedirect()
    {
        disabledRedirect = (disabledRedirect==null) ? false : disabledRedirect;
        return disabledRedirect;
    }
    public void  setDigitalSignURL(String hostURL, String FunctionOrPath){
        this.digitalSignatureURL = System.getProperty(hostURL) + FunctionOrPath;
    }
    public String getDigitalSignURL(){
        return digitalSignatureURL;
    }
    public String getDigitalSignature(){
        return digitalSignature;
    }
    public void setDigitalSignature(String signature){
        this.digitalSignature = signature;
    }


    public void composeRequest() {
        // set request headers
        setHeaders(new HashMap<String, String>() {{ put("Content-Type", CONTENT_TYPE);}});

        // set url body
        setUrl(this.getUrl());
       // set http body
        JSONObject rootObj = new JSONObject();
        rootObj.putAll(getBody());
        if(privateKey!=null){
            rootObj.put("signature",generateSignature(JSONObject.toJSONString(rootObj.get("request"))));
        }else if(digitalSignatureURL!=null){
            rootObj.put("signature",getDigitalSignature());
        }
        setBody(rootObj);
    }
    public void composeDigitalSignatureRequest() {
        // set request headers
        setHeaders(new HashMap<String, String>() {{ put("Content-Type", CONTENT_TYPE);}});

        // set http body
        JSONObject rootObj = new JSONObject();
        rootObj.putAll(getBody());
        setBody(rootObj);
    }

    public List<NameValuePair> getFormDataBody(){
        return formDataBody;
    }

    public void addFormDataBody(String key, String value){
        if (formDataBody ==null){
            formDataBody = new ArrayList<>();
        }
        formDataBody.add(new BasicNameValuePair(key, value));
    }


    public void composeGetRequest() {
        // set request headers
        setHeaders(new HashMap<String, String>() {{}});

        // set url body
        setUrl(this.getUrl());
    }
     private String generateSignature(String request) {
        if (!StringUtils.isEmpty(getSignature())) {
            return getSignature();
        }
        String generatedSignature = "";
        setSignature(generatedSignature);
        return generatedSignature;

    }

    @Override
    public JSONObject getBody() {
        if (null == body) {
            try {
                File file = ResourceUtils.getFile(RESFUL_BODY_JSON_PATH + getFunction() + ".content.json");
                String objectStr = StringHelper.readToString(file.getPath()).trim();
                JSONObject bodyObj;
                if(objectStr.startsWith("[")== true && objectStr.endsWith("]") == true ){
                    bodyStatus = true;
                    String newobjStr = objectStr.substring(1,objectStr.length()-1);
                    bodyObj = JSONObject.parseObject(newobjStr);
                }else {
                    bodyStatus =false;
                    bodyObj = JSONObject.parseObject(StringHelper.readToString(file.getPath()));
                }
                if (null != getBodyRewrite()) {
                    for (Map.Entry<String, String> entry: getBodyRewrite().entrySet()) {
                        JsonHelper.setStringByJsonPath(bodyObj, entry.getKey(), entry.getValue());
                    }
                }
                if (null != getBodyWriteInt()) {
                    for (Map.Entry<String, Integer> entry: getBodyWriteInt().entrySet()) {
                        JsonHelper.setStringByJsonPath(bodyObj, entry.getKey(), entry.getValue());
                    }
                }
                if (null != getBodyWriteFloat()) {
                    for (Map.Entry<String, BigDecimal> entry: getBodyWriteFloat().entrySet()) {
                        JsonHelper.setStringByJsonPath(bodyObj, entry.getKey(), entry.getValue());
                    }
                }
                if (null != getBodyWriteList()) {
                    for (Map.Entry<String, List> entry: getBodyWriteList().entrySet()) {
                        JsonHelper.setStringByJsonPath(bodyObj, entry.getKey(), entry.getValue());
                    }
                }
                    body = bodyObj;
            } catch (FileNotFoundException e) {
                LOGGER.error("Cannot find resful api body template in resources! " + getFunction());
                return null;
            } catch (Exception e) {
                LOGGER.error(e.getStackTrace().toString());
                return null;
            }
        }
        return body;
    }

    @Override
    public String getUrl() {
        if (null == url) {
            try {
                File file = ResourceUtils.getFile(RESFUL_BODY_JSON_PATH + getFunction() + ".url.txt");
                String urlObj = StringHelper.readToString(file.getPath());
                if (null != urlRewrite) {
                    for (Map.Entry<String, String> entry: getBodyRewrite().entrySet()) {
                        if (urlObj.contains("{"+entry.getKey()+"}")){
                            urlObj.replace("{"+entry.getKey()+"}", entry.getValue());
                        }
                    }
                }
                String urlDomain = System.getProperty(getHostURL());
                url = urlDomain+"/"+urlObj;
            }
            catch (FileNotFoundException e) {
                LOGGER.error("Cannot find resful api body template in resources! " + getFunction());
                return null;
            }
            catch (Exception e) {
                LOGGER.error(e.getStackTrace().toString());
                return null;
            }
        }
        return this.url;
    }
    public Map<String, BigDecimal> getBodyWriteFloat() {
        return bodyWriteFloat;
    }
    public Map<String, Integer> getBodyWriteInt() {
        return bodyWriteInt;
    }
    public Map<String, List> getBodyWriteList() {
        return bodyWriteList;
    }
     public Map<String, String> getBodyRewrite() {
        return bodyRewrite;
    }

    public void setBodyRewrite(Map<String, String> bodyRewrite) {
        this.bodyRewrite = bodyRewrite;
    }
    public void setListValue(String... value){
        listValue = new ArrayList<>();
        for (String arrValue: value) {
            listValue.add(arrValue);
        }
    }
    public void setBodyRewriteList(String key) {
        if (null == bodyWriteList) {
            bodyWriteList = new HashMap<>();
        }

        bodyWriteList.put(key, listValue);
    }
    public void setBodyRewrite(String key, String value) {
        if (null == bodyRewrite) {
            bodyRewrite = new HashMap<>();
        }
        bodyRewrite.put(key, value);
    }

    public void setBodyWriteInt(String key, Integer value) {
        if (null == bodyWriteInt) {
            bodyWriteInt = new HashMap<>();
        }
        bodyWriteInt.put(key, value);
    }
    public void setBodyWriteFloat(String key, String value) {
        if (null == bodyWriteFloat) {
            bodyWriteFloat = new HashMap<>();
        }
        bodyWriteFloat.put(key, bigDecimal = new BigDecimal(value));
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }

    public String getUseName() {
        return USE_NAME;
    }

    public void setUseName(String useName) {
        this.USE_NAME = useName;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public void setPassword(String password) {
        this.PASSWORD = password;
    }

    public void setHostURL(String hostURL){ this.hostURL = hostURL;}

    public String getHostURL(){
        return hostURL;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getSignature() {
        return signature;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public String getPrivateKey() {
        return privateKey;
    }
}