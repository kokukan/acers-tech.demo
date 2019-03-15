package jp.co.dyn.api.repository.client.model.zeus;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AmData {
    /**
     * 承認番号
     */
    private String syonin;
    /**
     * 伝票番号
     */
    private String denpyo;

    /**
     * 加盟店番号
     */
    private String merchantno;
}
