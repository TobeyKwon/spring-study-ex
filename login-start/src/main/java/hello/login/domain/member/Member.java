package hello.login.domain.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @Builder
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
