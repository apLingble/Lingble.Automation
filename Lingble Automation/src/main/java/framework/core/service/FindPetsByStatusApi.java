package framework.core.service;

import com.alibaba.fastjson.JSONObject;
import framework.core.base.BaseResfulApiService;
import framework.core.base.model.ResfulApiRequestModel;
import org.springframework.stereotype.Service;

/**
 * Author alexander.pangilinan
 */

@Service
public class FindPetsByStatusApi extends BaseResfulApiService {
    private static final String API_FUNCTION = "api.v3.pet.findByStatus";
    private static final String hostURL = "PETSTORE_HOST";

    public JSONObject sendRequest(String status) {
        ResfulApiRequestModel resfulApiRequestModel = new ResfulApiRequestModel();
        resfulApiRequestModel.setUrl(hostURL,API_FUNCTION+"?status="+status);
        return sendGETResfulApiRequest(resfulApiRequestModel);
    }


    public void check() {

    }


}
