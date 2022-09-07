package framework.keyword;

import framework.core.service.GetKibanaLogs;
import framework.core.service.GetOrderNumberApi;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author alexander.v.pangilinan
 */
public class ApiRequestKeyword {

    private GetOrderNumberApi getOrderNumberApi;
    private GetKibanaLogs getKibanaLogs;

    public void validateOrderNumberInLL(String orderNumber){
        getOrderNumberApi = new GetOrderNumberApi();
        getOrderNumberApi.sendRequest(orderNumber);
        getOrderNumberApi.checkResponse("data.channel_order_id",orderNumber);
    }

    public String getOrderNumberFulfillment(){
        getOrderNumberApi = new GetOrderNumberApi();
        getOrderNumberApi.sendRequest();
        return getOrderNumberApi.getResponseValue("data[0].order_number");
    }

    public void ValidateFulfillmentSMS(){
        getKibanaLogs = new GetKibanaLogs();
        getKibanaLogs.sendRequest();
//        System.out.println(getOrderNumberApi.getResponseValue("data[0].order_number"));
    }

}
