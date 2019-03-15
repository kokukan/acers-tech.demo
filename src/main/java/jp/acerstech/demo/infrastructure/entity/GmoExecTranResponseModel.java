package jp.co.dyn.api.repository.client.model;

import lombok.Data;

@Data
public class GmoExecTranResponseModel {

    private String ACS;
    private String OrderID;
    private String Forward;
    private String Method;
    private String PayTimes;
    private String Approve;
    private String TranID;
    private String TranDate;
    private String CheckString;
    private String ClientField1;
    private String ClientField2;
    private String ClientField3;
    private String ErrCode;
    private String ErrInfo;
}
