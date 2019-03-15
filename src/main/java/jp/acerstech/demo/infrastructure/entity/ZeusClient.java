package jp.co.dyn.api.repository.client;

import jp.co.dyn.api.repository.client.model.ZeusGetTokenResponseModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Data
@RequiredArgsConstructor
public class ZeusClient {

    /**
     * GMOゲートウェイベースURL
     */
    private String baseUrl;
    private RestTemplate restTemplate;
    /**
     * レスポンス項目区切り文字
     */
    private static final String RESPONSE_ITEM_DELIMITER = "&";
    /**
     * キー、バリュー区切り
     */
    private static final String KEY_VALUE_DELIMITER = "=";

    public ZeusClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

//    public ZeusGetTokenResponseModel getToken(){
//
//    }
}
