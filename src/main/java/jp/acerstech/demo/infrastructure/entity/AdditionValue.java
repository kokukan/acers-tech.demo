package jp.co.dyn.api.repository.client.model.zeus;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AdditionValue {
    /**
     * PayReqで送信した支払回数
     */
    private String div;
    /**
     * カードブランド
     */
    private String ctype;
    /**
     * 仕向け先カード会社コード
     */
    private String cardsend;
    /**
     * EnrolReqで送信したsendid
     */
    private String sendid;
    /**
     * EnrolReqで送信したsendpoint
     */
    private String sendpoint;
}
