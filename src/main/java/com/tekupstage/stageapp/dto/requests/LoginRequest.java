package com.tekupstage.stageapp.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    @Size(min = 3 , max =40)
    private String username;

    @NotBlank
    @Size(min = 3, max = 40)
    private String password;
}
