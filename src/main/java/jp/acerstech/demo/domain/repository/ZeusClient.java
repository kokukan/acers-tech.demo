
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * ZEUSと通信用のクライアントクラス
 */
@Data
@RequiredArgsConstructor
public class ZeusClient {

    /**
     * GMOゲートウェイベースURL
     */
    private String baseUrl;
    private RestTemplate restTemplate;
    /**
     * リトライ上限
     */
    @Value("${RestrictionDefinition.RETRY-LIMIT}")
    private int retryLimit;
    /**
     * リトライ回数
     */
    private int retryCount;

    public ZeusClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * トークンを取得する
     * ※テストの場合のみ利用してください。
     * 本番リリースする前に削除する予定
     *
     * @param model トークン取得用リクエストモデル
     * @return トークン取得用レスポンスモデル
     */
    public ZeusGetTokenResponseModel getToken(ZeusGetTokenRequestModel model) {

        String apiUrl = "/cgi-bin/token/token.cgi";
        return postToZeus(model, apiUrl, ZeusGetTokenResponseModel.class);
    }

    /**
     * ZEUS取引登録を行う
     *
     * @param model 取引登録用のリクエストモデル
     * @return 取引登録レスポンスモデル
     */
    public ZeusEnrolResponseModel execEnrol(ZeusEnrolRequestModel model) {

        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model, apiUrl, ZeusEnrolResponseModel.class);
    }

    /**
     * 認証結果を送信する
     *
     * @param model 認証結果リクエストモデル
     * @return 認証結果レスポンスモデル
     */
    public ZeusAuthResponseModel execAuth(ZeusAuthRequestModel model) {

        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model, apiUrl, ZeusAuthResponseModel.class);
    }

    /**
     * 決済を実行する（オーソリ要求を行う）
     *
     * @param model 決済実行用リクエストモデル
     * @return 決済実行レスポンスモデル
     */
    public ZeusPayResponseModel execPay(ZeusPayRequestModel model) {

        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model, apiUrl, ZeusPayResponseModel.class);
    }

    /**
     * 決済部分取消 金額変更（オーソリ要求を行う）
     *
     * @param model 決済部分取消実行用リクエストモデル
     * @return 決済部分取消実行レスポンスモデル
     */
    public ZeusChangeTranResponseModel changeTran(ZeusChangeTranRequestModel model) {
        String apiUrl = "/cgi-bin/credit/price_change/reflect/index.cgi";
        return postToZeus(model, apiUrl, ZeusChangeTranResponseModel.class);
    }

    /**
     * 取消を実行する
     *
     * @param model 取消用リクエストモデル
     * @return 取消実行レスポンスモデル
     */
    @Retryable(value = {ResourceAccessException.class}, maxAttemptsExpression = "${RestrictionDefinition.RETRY-LIMIT}")
    public ZeusRefundResponseModel execRefund(ZeusRefundRequestModel model) {
        retryCount++;
        if (retryCount == retryLimit) {
            return null;
        }
        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model, apiUrl, ZeusRefundResponseModel.class);
    }

    /**
     * Zeusに送信共通メソッド
     *
     * @param model  リクエストモデル
     * @param apiUrl APIのURL
     * @param clazz  レスポンスモデルのクラス
     * @param <R>    レスポンスのジェネリック
     * @param <T>    リクエストのジェネリック
     * @return レスポンスモデル
     */
    private <R, T> R postToZeus(T model, String apiUrl, Class<R> clazz) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<T> request = new HttpEntity(model, headers);
        ResponseEntity<R> response = restTemplate.postForEntity(baseUrl + apiUrl,
                request, clazz);
        return response.getBody();
    }
}
