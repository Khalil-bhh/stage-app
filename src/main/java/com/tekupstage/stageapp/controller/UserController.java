package com.tekupstage.stageapp.controller;

import com.tekupstage.stageapp.dto.base.AppResponse;
import com.tekupstage.stageapp.dto.base.ErrorResponse;
import com.tekupstage.stageapp.dto.user.SingleUserResponse;
import com.tekupstage.stageapp.dto.user.UserListResponse;
import com.tekupstage.stageapp.models.User;
import com.tekupstage.stageapp.repository.UserRepository;
import com.tekupstage.stageapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  UserService userService;

    @GetMapping
    public UserListResponse getUsers(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "page_size",defaultValue = "8") int pageSize,
                                     HttpServletRequest request){
        Page<User> usersPage = userService.getUsers(page,pageSize);
        return UserListResponse.build(usersPage,request.getRequestURI());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppResponse> getUserById(@PathVariable long id){
        User user = userService.findById(id);
        ErrorResponse errors = new ErrorResponse();
        if (user == null) {
            errors.getFullMessages().add("user with id " + String.valueOf(id) + " not found");
            return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(SingleUserResponse.build(user),HttpStatus.OK);
    }

    @PostMapping
    public User register(@RequestBody User user){
        return userService.save(user);
    }
}
