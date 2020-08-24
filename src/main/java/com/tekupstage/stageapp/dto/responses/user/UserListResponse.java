package com.tekupstage.stageapp.dto.responses.user;

import com.tekupstage.stageapp.dto.responses.shared.PageMeta;
import com.tekupstage.stageapp.dto.responses.shared.PageMetaResponse;
import com.tekupstage.stageapp.dto.responses.user.partials.UserSummaryDto;
import com.tekupstage.stageapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserListResponse extends PageMetaResponse {

    private PageMeta pageMeta;
    private List<UserSummaryDto> userList;

    public static UserListResponse build(Page<User> userPage, String basePath){
        List<UserSummaryDto> userList = userPage.getContent().stream()
                .map(UserSummaryDto::build)
                .collect(Collectors.toList());
        return  new UserListResponse(
                PageMeta.build(userPage,basePath),
                userList
        );
    }
}
