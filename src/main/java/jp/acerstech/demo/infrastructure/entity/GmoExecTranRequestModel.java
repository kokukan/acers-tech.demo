package jp.co.dyn.api.repository.client.model;

import lombok.Data;

@Data
public class GmoExecTranRequestModel {

    private String Version;
    private String AccessID;
    private String AccessPass;
    private String OrderID;
    private String Method;
    private String PayTimes;
    private String Token;
    private String TokenType;

}
