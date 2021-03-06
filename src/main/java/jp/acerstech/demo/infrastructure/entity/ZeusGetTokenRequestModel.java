package jp.co.dyn.api.repository.client.model.zeus;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * トークン取得用リクエストモデルクラス
 * テストのため一時的なものであり、将来削除する予定
 */
@Data
@XmlRootElement(name="request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZeusGetTokenRequestModel {

    @XmlAttribute(name="service")
    private String service;
    @XmlAttribute(name="action")
    private String action;
    @XmlElement(name="authentication")
    private Authentication authentication;
    @XmlElement(name = "card")
    private Card card;


}
