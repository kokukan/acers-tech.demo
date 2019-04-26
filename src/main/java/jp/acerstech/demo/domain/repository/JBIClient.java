
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * JBIと通信用のクライアントクラス
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class JBIClient {
    /**
     * JBIゲートウェイベースURL
     */
    private String baseUrl;

    public JBIClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 取引登録を行う
     *
     * @param model 取引登録用モデル
     * @return 取引登録結果モデル
     */
    public JBIResponseModel ReserveData(JBIRequestModel model) {
        //TODO
        String soapAction = "http://www.jtbbookandpay.com/BP/DATA/IReserveData/EntryReserveData";
        /*RequestEntryReserveData requestEntryReserveData = new RequestEntryReserveData();
        ObjectFactory factory = new ObjectFactory();
        requestEntryReserveData.setBESystemCode(factory.createRequestEntryReserveDataBESystemCode(model.getBE_SystemCode()));
        requestEntryReserveData.setLoginID(factory.createRequestEntryReserveDataLoginID(model.getLoginID()));
        requestEntryReserveData.setLoginPassword(factory.createRequestEntryReserveDataLoginPassword(model.getLoginPassword()));
        requestEntryReserveData.setRequestID(factory.createRequestEntryReserveDataRequestID(model.getRequestID()));

        EntryReserveData reserveData = new EntryReserveData();
        reserveData.setReserveData(factory.createRequestEntryReserveData(requestEntryReserveData));
        return postToJbi(reserveData, soapAction);*/
        return null;
    }

    /**
     * Zeusに送信共通メソッド
     *
     * @param reserveData リクエストデータ
     * @param soapAction  APIのsoapAction URL
     * @return レスポンスモデル
     */
   /* private JBIResponseModel postToJbi(EntryReserveData reserveData, String soapAction) {
        EntryReserveDataResponse res = entryReserve(reserveData, soapAction);
        JBIResponseModel jbiResponseModel = new JBIResponseModel();

        BeanUtils.copyProperties(res, jbiResponseModel);

        return jbiResponseModel;
    }*/

    /**
     * SOAPリクエストを作成する
     *
     * @param request    HTTPエンティティ
     * @param soapAction APIのURL
     * @return レスポンス ストリング
     */
    /*public EntryReserveDataResponse entryReserve(EntryReserveData request, String soapAction) {

        EntryReserveDataResponse res = (EntryReserveDataResponse) getWebServiceTemplate().marshalSendAndReceive(request,
                new SoapActionCallback(soapAction));
        return res;
    }*/
}
