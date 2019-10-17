package ma.tc.projects.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter  @Setter
public class UserForm{

    private String username;
    private String password;
    private String confirmedPassword;
}
