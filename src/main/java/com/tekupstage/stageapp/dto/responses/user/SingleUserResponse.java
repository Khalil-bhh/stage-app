package com.tekupstage.stageapp.dto.responses.user;

import com.tekupstage.stageapp.dto.responses.base.SuccessResponse;
import com.tekupstage.stageapp.enums.UserRole;
import com.tekupstage.stageapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleUserResponse extends SuccessResponse {

    protected Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private UserRole role;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    public static SingleUserResponse build(User user){
        return new SingleUserResponse(user.getId(),user.getUsername()
                ,user.getEmail(),user.getFirstName(),user.getLastName(),user.getRole()
                ,user.isAccountNonExpired(),user.isAccountNonLocked(),user.isCredentialsNonExpired(),user.isEnabled());
    }


}
