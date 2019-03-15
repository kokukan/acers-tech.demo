package jp.co.dyn.api.repository.client.model;

import lombok.Data;

/**
 * GMO認証後決済リクエストモデル
 */
@Data
public class GmoSecureTranRequestModel {

    private String VerSion;
    private String PaRes;
    private String MD;

}
