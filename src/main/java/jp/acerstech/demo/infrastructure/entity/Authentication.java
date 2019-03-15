package jp.co.dyn.api.repository.client.model.zeus;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class Authentication {
    private String clientip;
    private String key;
}
