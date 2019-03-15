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
public class ZeusPayRequestModel {

    @XmlAttribute(name="service")
    private String service;
    @XmlAttribute(name="action")
    private String action;
    @XmlElement(name="xid")
    private String xid;
    @XmlElement(name="print_am")
    private String printAm;
    @XmlElement(name="print_addition_value")
    private String printAdditionValue;
}
