package jp.co.dyn.api.repository.client.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="request")
public class ZeusGetTokenRequestModel {

    @XmlAttribute(name="service")
    private String service;
    @XmlAttribute(name="action")
    private String action;

    @XmlElementWrapper(name = "authentication")
    @XmlElement(name = "clientip")
    private String clientip;
}
