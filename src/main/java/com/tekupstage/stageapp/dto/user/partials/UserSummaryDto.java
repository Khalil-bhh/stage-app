package com.tekupstage.stageapp.dto.user.partials;

import com.tekupstage.stageapp.enums.UserRole;
import com.tekupstage.stageapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSummaryDto {
    protected Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private UserRole role;

    public static UserSummaryDto build(User user){
        return new UserSummaryDto(user.getId(),user.getUsername()
                ,user.getEmail(),user.getFirstName(),user.getLastName(),user.getRole());
    }

}
