package jp.acerstech.demo.acerstech.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchConditonDto {

    private String department;
    private String userId;
    private String age;
}
