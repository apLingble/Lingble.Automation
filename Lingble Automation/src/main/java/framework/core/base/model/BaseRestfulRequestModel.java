package framework.core.base.model;

import org.apache.commons.httpclient.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class BaseRestfulRequestModel {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseRestfulRequestModel.class);

    protected String url;

    protected Object body;

    protected Map<String, String> headers;

    protected Cookie cookie;

    public String getUrl() {
        if (null != url && !url.startsWith("http://")) {
            setUrl("http://" + url);
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
