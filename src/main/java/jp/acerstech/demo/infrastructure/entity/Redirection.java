package jp.co.dyn.api.repository.client.model.zeus;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class Redirection {
    @XmlElement(name = "acs_url")
    private String acsUrl;
    @XmlElement(name = "PaReq")
    private String pareq;
}
