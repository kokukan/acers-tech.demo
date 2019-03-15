package jp.co.dyn.api.repository.client.model.zeus;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
public class History {

    @XmlAttribute(name="action")
    private String action;
    @XmlElement(name="key")
    List<String> keys;

}
