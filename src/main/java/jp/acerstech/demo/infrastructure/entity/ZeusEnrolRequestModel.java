package jp.co.dyn.api.repository.client.model.zeus;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZeusEnrolRequestModel {

    @XmlAttribute(name="service")
    private String service;
    @XmlAttribute(name="action")
    private String action;
    @XmlElement(name="authentication")
    private Authentication authentication;
    @XmlElement(name="token_key")
    private String tokenKey;
    @XmlElement(name="payment")
    private Payment payment;
    @XmlElement(name="user")
    private User user;
    @XmlElement(name="uniq_key")
    private UniqueKey uniqueKey;

}
