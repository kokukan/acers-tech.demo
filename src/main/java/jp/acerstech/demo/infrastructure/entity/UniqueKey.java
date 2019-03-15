package jp.co.dyn.api.repository.client.model.zeus;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class UniqueKey {
    private String sendid;
    private String sendpoint;
}
