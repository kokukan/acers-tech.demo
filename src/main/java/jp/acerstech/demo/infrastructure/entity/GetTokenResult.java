package jp.co.dyn.api.repository.client.model.zeus;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class GetTokenResult {
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "code")
    private String code;
    @XmlElement(name = "token_key")
    private String tokenKey;
    @XmlElement(name = "masked_cvv")
    private String maskedCvv;
    @XmlElement(name = "masked_card_number")
    private String maskedCardNumber;
    @XmlElement(name = "card_expires_month")
    private String cardExpiresMoth;
    @XmlElement(name = "card_expires_year")
    private String cardExpiresYear;
    @XmlElement(name = "card_name")
    private String cardName;
}
