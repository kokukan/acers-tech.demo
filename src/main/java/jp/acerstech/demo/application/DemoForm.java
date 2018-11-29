package jp.acerstech.demo.application;

import java.util.List;
import jp.acerstech.demo.domain.model.SearchConditionDto;
import jp.acerstech.demo.domain.model.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DemoForm {

    private SearchConditionDto searchConditionDto;

    private List<UserInfo> userList;
}
