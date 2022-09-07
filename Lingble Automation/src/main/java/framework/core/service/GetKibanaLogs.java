package framework.core.service;

import com.alibaba.fastjson.JSONObject;
import framework.core.base.BaseResfulApiService;
import framework.core.base.model.ResfulApiRequestModel;
import org.springframework.stereotype.Service;


@Service
public class GetKibanaLogs extends BaseResfulApiService {
    private static final String API_FUNCTION = "kibana";
    private static final String hostURL = "KIBANA_HOST";

    public JSONObject sendRequest() {
        ResfulApiRequestModel resfulApiRequestModel = new ResfulApiRequestModel();
        resfulApiRequestModel.setHostURL(hostURL);
        resfulApiRequestModel.setFunction(API_FUNCTION);
        resfulApiRequestModel.setUseName("kibana");
        resfulApiRequestModel.setPassword("U1Iu9jE6lc");
        resfulApiRequestModel.addHeader("kbn-xsrf","kibana");
        resfulApiRequestModel.setBodyRewrite("query.bool.filter(0).multi_match.query", "ECISTG00025901");
        return sendResfulApiRequest(resfulApiRequestModel);
    }


    public void check() {

    }


}
