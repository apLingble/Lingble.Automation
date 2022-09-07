package framework.core.service;

import com.alibaba.fastjson.JSONObject;
import framework.core.base.BaseResfulApiService;
import framework.core.base.model.ResfulApiRequestModel;
import framework.core.helpers.commonHelper;
import org.springframework.stereotype.Service;


@Service
public class GetOrderNumberApi extends BaseResfulApiService {
    private static final String API_FUNCTION = "api.v3.pet";
    private static final String hostURL = "LL_HOST";

    public JSONObject sendRequest(String orderNumber) {
        ResfulApiRequestModel resfulApiRequestModel = new ResfulApiRequestModel();
        resfulApiRequestModel.setUrl(hostURL,"/order-api/v1/tenants/"+System.getProperty("tenant_id")+"/orders/number/"+orderNumber);
        return sendGETResfulApiRequest(resfulApiRequestModel);
    }

    public JSONObject sendRequest() {
        ResfulApiRequestModel resfulApiRequestModel = new ResfulApiRequestModel();
        resfulApiRequestModel.setUrl(hostURL,"/order-api/v1/tenants/"+System.getProperty("tenant_id")+"/orders?fulfillment_status=new&page=1&sort_direction=desc&sort_by=created_at&status=Submitted&per_page=1&first_name=alexander");
        return sendGETResfulApiRequest(resfulApiRequestModel);
    }

    public void check() {

    }


}
