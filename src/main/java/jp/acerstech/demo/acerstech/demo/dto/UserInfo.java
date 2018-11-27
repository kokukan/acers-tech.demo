package jp.acerstech.demo.acerstech.demo.dto;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@ToString
public class UserInfo {

    private String userId;
    private Integer age;
    private String name;
    private String password;
    private String department;
    private String securityCardNo;
}
