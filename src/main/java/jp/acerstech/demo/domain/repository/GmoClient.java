package jp.co.dyn.api.repository.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.dyn.api.repository.client.model.gmo.GmoAlterTranRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoAlterTranResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoChangeTranRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoChangeTranResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoEntryTranRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoEntryTranResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoExecTranRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoExecTranResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoGetTokenRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoGetTokenResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoMemRefRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoMemRefResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoSaveCardRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoSaveCardResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoSaveMemberRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoSaveMemberResponseModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoSecureTranRequestModel;
import jp.co.dyn.api.repository.client.model.gmo.GmoSecureTranResponseModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
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
    /**
     * リトライ上限
     */
    @Value("${RestrictionDefinition.RETRY-LIMIT}")
    private int retryLimit;
    /**
     * リトライ回数
     */
    private int retryCount;

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
        String apiUrl = "/payment/EntryTran.idPass";
        return postToGmo(model, apiUrl, GmoEntryTranResponseModel::new);
    }

    /**
     * 決済実行
     *
     * @param model 決済実行用モデル
     * @return 決済実行レスポンスモデル
     */
    public GmoExecTranResponseModel execTran(GmoExecTranRequestModel model) {
        String apiUrl = "/payment/ExecTran.idPass";
        return postToGmo(model, apiUrl, GmoExecTranResponseModel::new);
    }

    /**
     * 認証後決済実行
     *
     * @param model 決済実行用モデル
     * @return 決済実行レスポンスモデル
     */
    public GmoSecureTranResponseModel secureTran(GmoSecureTranRequestModel model) {
        String apiUrl = "/payment/SecureTran.idPass";
        return postToGmo(model, apiUrl, GmoSecureTranResponseModel::new);
    }

    /**
     * 会員照会を行う
     *
     * @param model 会員照会用モデル
     * @return 会員照会レスポンスモデル
     */
    public GmoMemRefResponseModel searchMember(GmoMemRefRequestModel model) {
        String apiUrl = "/payment/SearchMember.idPass";
        return postToGmo(model, apiUrl, GmoMemRefResponseModel::new);
    }

    /**
     * 会員登録を行う
     *
     * @param model 会員登録用モデル
     * @return 会員登録レスポンスモデル
     */
    public GmoSaveMemberResponseModel saveMember(GmoSaveMemberRequestModel model) {
        String apiUrl = "/payment/SaveMember.idPass";
        return postToGmo(model, apiUrl, GmoSaveMemberResponseModel::new);
    }

    /**
     * カード登録を行う
     *
     * @param model カード登録用モデル
     * @return カード登録レスポンスモデル
     */
    public GmoSaveCardResponseModel saveCard(GmoSaveCardRequestModel model) {
        String apiUrl = "/payment/SaveCard.idPass";
        return postToGmo(model, apiUrl, GmoSaveCardResponseModel::new);
    }

    /**
     * 決済部分取消 金額変更 を行う
     *
     * @param model 決済部分取消 金額変更用モデル
     * @return 決済部分取消 金額変更 レスポンスモデル
     */
    public GmoChangeTranResponseModel changeTran(GmoChangeTranRequestModel model) {
        String apiUrl = "/payment/ChangeTran.idPass";
        return postToGmo(model, apiUrl, GmoChangeTranResponseModel::new);
    }

    /**
     * 決済変更（取消）を行う
     *
     * @param model 決済変更用モデル
     * @return 決済変更レスポンスモデル
     */
    @Retryable(value = {ResourceAccessException.class}, maxAttemptsExpression = "${RestrictionDefinition.RETRY-LIMIT}")
    public GmoAlterTranResponseModel alterTran(GmoAlterTranRequestModel model) {
        retryCount++;
        if (retryCount == retryLimit) {
            return null;
        }
        String apiUrl = "/payment/AlterTran.idPass";
        return postToGmo(model, apiUrl, GmoAlterTranResponseModel::new);
    }


    /**
     * トーケンを取得する
     * ※テストのため用意したものであり、将来削除する必要
     *
     * @return
     */
    @Deprecated
    public String getToken() {

        String apiUrl = "/ext/api/credit/getToken";
        String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlhKDXlWw4tu2JrbFITYUDT82BH2W8rLZ9" +
                "OMFifrYIjO8fz12EsP+Xo+I/xUeh1ctTjGOIQYzZHYacWQ+xjNE5NSF5Beilcn2It0ST8EuQEb0NpRcm7BnYgVP7vYR" +
                "OXUFHiyrOUlPopSW4o+31IKwmO7VtTmq4iHZspMF/25YahIbOJfFF0uMAdIJYRxpYwIS5LWQB0zMT1G70tIGU6yIe3c" +
                "iN7Le7phljZFidnM+EVSaauela9U8uL00WxTFudpybEwpDTX4zYoE5Cd1DWhZt4pSeKpqEiBMzK/siF1tkPBFifU+V" +
                "H0e8L8+3n6/v52NwMnhPkoeHdDyLgl1tJnWJwIDAQAB";

        Map<String, String> map = new HashMap<>();
        map.put("cardNo", "4111111111111111");
        map.put("expire", "201911");
        map.put("securityCode", "111");
        map.put("holderName", "test");
        map.put("tokenNumber", "1");
        ObjectMapper objectMapper = new ObjectMapper();
        String cardJson = "";
        String encrypted = "";
        try {
            cardJson = objectMapper.writeValueAsString(map);
            X509EncodedKeySpec spec =
                    new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(spec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encrypted = Base64.encodeBase64String(cipher.doFinal(cardJson.getBytes()));
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException
                | InvalidKeySpecException | InvalidKeyException | JsonProcessingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        GmoGetTokenRequestModel model = new GmoGetTokenRequestModel();
        model.setEncrypted(encrypted);
        model.setShopID("tshop00018560");
        model.setKeyHash("59e3e2708cdd699bfc9d3e8029350b4704c43591b52992f428190ee069cebcc1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> bodyMap = convertBeanToMap(model);
        HttpEntity<MultiValueMap> request = new HttpEntity(bodyMap, headers);
        ResponseEntity<GmoGetTokenResponseModel> response = restTemplate.postForEntity(baseUrl + apiUrl,
                request, GmoGetTokenResponseModel.class);
        return response.getBody().getTokenObject().getToken()[0];

    }

    /**
     * GMOゲートウェイへ電文を送る
     *
     * @param model    送信電文パラメタモデル
     * @param apiUrl   APIのURL
     * @param supplier supplierファンクション
     * @param <R>      レスポンス型のジェネリクス
     * @param <T>      リクエスト型のジェネリクス
     * @return レスポンスモデル
     */
    private <R, T> R postToGmo(T model, String apiUrl, Supplier<R> supplier) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> bodyMap = convertBeanToMap(model);
        HttpEntity<MultiValueMap> request = new HttpEntity<>(bodyMap, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + apiUrl, request, String.class);
        return convertResponseToResponseModel(response.getBody(), supplier.get());
    }

    /**
     * リクエストのモデルからMapに変換する
     *
     * @param model リクエストモデル
     * @return パラメタMap
     */
    private MultiValueMap convertBeanToMap(Object model) {

        Class<?> cls = model.getClass();
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
     *
     * @param responseBody レスポンス文字列
     * @param model        リクエストモデル
     * @param <T>          リクエスト型のジェネリクス
     * @return リクエストモデル
     */
    private <T> T convertResponseToResponseModel(String responseBody, T model) {

        //レスポンスが空の場合、モデルに値を設定せず、そのまま返す
        if (StringUtils.isBlank(responseBody)) {
            return model;
        }
        responseBody.replace("\"", "");
        String[] gmoRes = responseBody.split(RESPONSE_ITEM_DELIMITER);
        Map<String, Object> keyValueMap = new HashMap<>();
        Arrays.stream(gmoRes).forEach(line -> {
            String[] keyValueArray = line.split(KEY_VALUE_DELIMITER);
            String name = keyValueArray[0];
            Object value = (keyValueArray.length == 2 ? keyValueArray[1] : null);
            name = decaptialize(name);
            keyValueMap.put(name, value);
        });
        try {
            BeanUtils.populate(model, keyValueMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.warn("error occured when convert map to bean. error:{}", e);
        }
        return model;
    }


    /**
     * JavaBeanのプロパティ名の先頭を小文字化する
     * 先頭2文字が大文字の場合、名前はそのままとし、それ以外の場合
     * 先頭１文字が小文字にする
     *
     * @param name JavaBeanのプロパティ名
     * @return 小文字化後の文字列
     */
    private String decaptialize(String name) {

        if (StringUtils.isBlank(name)) {
            return name;
        }

        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) &&
                Character.isUpperCase(name.charAt(0))) {
            return name;
        }
        char chars[] = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
