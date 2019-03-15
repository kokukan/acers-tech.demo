package jp.co.dyn.api.repository.client.model;

import lombok.Data;

@Data
public class GmoEntryTranRequestModel {

    private String VerSion;
    private String ShopID;
    private String ShopPass;
    private String OrderID;
    private String JobCd;
    private String ItemCode;
    private Integer Amount;
    private Integer Tax;
    private String TdFlag;
    private String TdTenantName;

}
