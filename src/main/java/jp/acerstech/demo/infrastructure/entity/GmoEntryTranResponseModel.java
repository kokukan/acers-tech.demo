package jp.co.dyn.api.repository.client.model;

import lombok.Data;

@Data
public class GmoEntryTranResponseModel {

    private String AccessID;
    private String AccessPass;
    private String ErrCode;
    private String ErrInfo;
}
