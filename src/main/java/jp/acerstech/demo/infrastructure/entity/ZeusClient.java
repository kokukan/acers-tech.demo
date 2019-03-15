package jp.co.dyn.api.repository.client;

import jp.co.dyn.api.repository.client.model.zeus.ZeusAuthRequestModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusAuthResponseModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusEnrolRequestModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusEnrolResponseModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusGetTokenRequestModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusGetTokenResponseModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusPayRequestModel;
import jp.co.dyn.api.repository.client.model.zeus.ZeusPayResponseModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Data
@RequiredArgsConstructor
public class ZeusClient {

    /**
     * GMOゲートウェイベースURL
     */
    private String baseUrl;
    private RestTemplate restTemplate;

    public ZeusClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    /**
     * トークンを取得する
     * ※テストの場合のみ利用してください。
     * 本番リリースする前に削除する予定
     * @param model トークン取得用リクエストモデル
     * @return トークン取得用レスポンスモデル
     */
    public ZeusGetTokenResponseModel getToken(ZeusGetTokenRequestModel model) {

        String apiUrl = "/cgi-bin/token/token.cgi";
        return postToZeus(model,apiUrl,ZeusGetTokenResponseModel.class);
    }

    /**
     * ZEUS取引登録を行う
     * @param model 取引登録用のリクエストモデル
     * @return 取引登録レスポンスモデル
     */
    public ZeusEnrolResponseModel execEnrol(ZeusEnrolRequestModel model) {

        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model,apiUrl,ZeusEnrolResponseModel.class);
    }

    /**
     * 認証結果を送信する
     * @param model 認証結果リクエストモデル
     * @return 認証結果レスポンスモデル
     */
    public ZeusAuthResponseModel execAuth(ZeusAuthRequestModel model) {

        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model,apiUrl,ZeusAuthResponseModel.class);
    }

    /**
     * 決済を実行する（オーソリ要求を行う）
     * @param model 決済実行用リクエストモデル
     * @return 決済実行レスポンスモデル
     */
    public ZeusPayResponseModel execPay(ZeusPayRequestModel model) {

        String apiUrl = "/cgi-bin/secure/api.cgi";
        return postToZeus(model,apiUrl,ZeusPayResponseModel.class);
    }

    /**
     * Zeusに送信共通メソッド
     * @param model リクエストモデル
     * @param apiUrl APIのURL
     * @param clazz レスポンスモデルのクラス
     * @param <R> レスポンスのジェネリック
     * @param <T> リクエストのジェネリック
     * @return レスポンスモデル
     */
    private <R,T> R postToZeus(T model,String apiUrl, Class<R> clazz) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<T> request = new HttpEntity(model, headers);
        ResponseEntity<R> response = restTemplate.postForEntity(baseUrl + apiUrl,
                request,clazz);
        return response.getBody();
    }
}
