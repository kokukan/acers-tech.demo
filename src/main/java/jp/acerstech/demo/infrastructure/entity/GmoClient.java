package jp.co.dyn.api.repository.client;

import jp.co.dyn.api.repository.client.model.GmoEntryTranRequestModel;
import jp.co.dyn.api.repository.client.model.GmoEntryTranResponseModel;
import jp.co.dyn.api.repository.client.model.GmoExecTranRequestModel;
import jp.co.dyn.api.repository.client.model.GmoExecTranResponseModel;
import jp.co.dyn.api.repository.client.model.GmoRepeatExecTranResponseModel;
import jp.co.dyn.api.repository.client.model.GmoSecureTranResponseModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * GMOと通信用のクライアントクラス
 */
@Data
@RequiredArgsConstructor
@Slf4j
public class GmoClient {

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

    public GmoClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * 取引登録を行う
     *
     * @param model 取引登録用モデル
     * @return 取引登録結果モデル
     */
    public GmoEntryTranResponseModel entryTran(GmoEntryTranRequestModel model) {

        String apiUrl =  "/payment/EntryTran.idPass";
        return  postToGmo(model,apiUrl,GmoEntryTranResponseModel::new);

    }

    /**
     * 決済実行（初回）
     * @param model 決済実行用モデル
     * @return 決済実行レスポンスモデル
     */
    public GmoExecTranResponseModel execTranFirstTime(GmoExecTranRequestModel model) {
        String apiUrl = "/payment/ExecTran.idPass";
        return  postToGmo(model,apiUrl,GmoExecTranResponseModel::new);

    }

    /**
     * 決済実行（リピート）
     * @param model 決済実行用モデル
     * @return 決済実行レスポンスモデル
     */
    public GmoRepeatExecTranResponseModel execTranRepeat(GmoRepeatExecTranResponseModel model) {
        String apiUrl = "/payment/ExecTran.idPass";
        return  postToGmo(model,apiUrl,GmoRepeatExecTranResponseModel::new);

    }

    /**
     * 認証後決済実行
     * @param model 決済実行用モデル
     * @return 決済実行レスポンスモデル
     */
    public GmoSecureTranResponseModel secureTran(GmoSecureTranResponseModel model) {
        String apiUrl = "/payment/SecureTran.idPass";
        return  postToGmo(model,apiUrl,GmoSecureTranResponseModel::new);

    }

    /**
     * GMOゲートウェイへ電文を送る
     * @param model 送信電文パラメタモデル
     * @param apiUrl APIのURL
     * @param supplier supplierファンクション
     * @param <R> レスポンス型のジェネリクス
     * @param <T> リクエスト型のジェネリクス
     * @return レスポンスモデル
     */
    private <R,T> R postToGmo(T model,String apiUrl, Supplier<R> supplier) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> bodyMap = convertBeanToMap(model);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + apiUrl, request, String.class);
        return convertResponseToResponseModel(response.getBody(),supplier.get() );
    }
    /**
     * リクエストのモデルからMapに変換する
     * @param model リクエストモデル
     * @return パラメタMap
     */
    private MultiValueMap convertBeanToMap(Object model) {

        Class<? extends Object> cls = model.getClass();
        Field[] fields = cls.getDeclaredFields();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value;
            try {
                value = field.get(model);
            } catch (IllegalAccessException e) {
                value = null;
            }
            if (value != null) {
                map.add(name, value);
            }
        }
        return map;
    }

    /**
     * 状況により、GMOから帰ってきた項目は動的に変わる可能性があるため、
     * GMOレスポンス（文字列）からレスポンスモデルに値を詰める
     * @param responseBody レスポンス文字列
     * @param model リクエストモデル
     * @param <T> リクエスト型のジェネリクス
     * @return リクエストモデル
     */
    private <T> T convertResponseToResponseModel(String responseBody, T model) {

        //レスポンスが空の場合、モデルに値を設定せず、そのまま返す
        if (StringUtils.isEmpty(responseBody)) {
            return model;
        }
        responseBody.replace("\"", "");
        String[] gmoRes = responseBody.split(RESPONSE_ITEM_DELIMITER);
        Map<String, Object> keyValueMap = new HashMap();
        Arrays.stream(gmoRes).forEach(line -> {
            String[] keyValueArray = line.split(KEY_VALUE_DELIMITER);
            String name = keyValueArray[0];
            Object value = (keyValueArray.length == 2 ? keyValueArray[1] : null);
            //先頭文字が大文字の場合、値が設定されないため、先頭文字を小文字に変換する
            name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
            keyValueMap.put(name, value);
        });
        try {
            BeanUtils.populate(model, keyValueMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("error occured when convert map to bean. error:{}",e);
        }
        return model;
    }


}
