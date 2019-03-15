package jp.co.dyn.api.repository.client.model.zeus;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZeusPayResponseModel {

    @XmlAttribute(name="service")
    private String service;
    @XmlAttribute(name="action")
    private String action;
    @XmlElement(name="result")
    private BaseResult result;
    @XmlElement(name="order_number")
    private String orderNumber;
    @XmlElement(name="am_data")
    private AmData amData;
    @XmlElement(name="addition_value")
    private AdditionValue additionValue;
}
