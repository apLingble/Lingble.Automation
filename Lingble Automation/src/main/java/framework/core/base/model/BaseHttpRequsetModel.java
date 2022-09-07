package framework.core.base.model;

import org.apache.commons.httpclient.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class BaseHttpRequsetModel {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseHttpRequsetModel.class);

    protected String url;

    protected Object body;

    protected Map<String, String> headers;
    public Map<String, String> addHeader;

    protected Cookie cookie;

    public String getUrl() {
        if (null != url && !url.startsWith("http://") && !url.startsWith("https://") ) {
            setUrl("http://" + url);
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void setUrl(String hostURL, String FunctionOrPath) {
        String generateURL = null;
        if (hostURL.startsWith("http://") || hostURL.startsWith("https://") ) {
            if (FunctionOrPath.endsWith(".json")) {
                generateURL = hostURL + "/" + FunctionOrPath.replace(".", "/") + ".json";
            }else if (FunctionOrPath.endsWith(".htm")) {
                generateURL = hostURL + "/" + FunctionOrPath.replace(".", "/") + ".htm";
            }else {
                generateURL = hostURL + "/" + FunctionOrPath.replace(".", "/");
            }
            this.url = generateURL;
        } else if ((!hostURL.startsWith("http://") || !hostURL.startsWith("https://"))&& FunctionOrPath.contains("/")) {
            generateURL = System.getProperty(hostURL) + FunctionOrPath;
            this.url = generateURL;
        }else {
            if (FunctionOrPath.endsWith(".json")) {
                generateURL = System.getProperty(hostURL) + "/" + FunctionOrPath.replace(".", "/") + ".json";
            }else if (FunctionOrPath.endsWith(".htm")) {
                generateURL = System.getProperty(hostURL) + "/" + FunctionOrPath.replace(".", "/") + ".htm";
            }else if (FunctionOrPath.isEmpty()){
                generateURL = System.getProperty(hostURL);
            }else {
                generateURL = System.getProperty(hostURL) + "/" + FunctionOrPath.replace(".", "/");
            }
            this.url = generateURL;
        }
    }


    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        if(addHeader!=null){
            headers.putAll(addHeader);
        }
        return headers;
    }

    public void addHeaders(String key, String value)
    {
        if (this.headers == null) this.headers = new HashMap<>();
        this.headers.put(key, value);
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;

    }
    public void addHeader(String key, String value) {
        if (addHeader == null){
            addHeader = new HashMap<String, String>() {{ }};
        }
        addHeader.put(key, value);
    }


    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}