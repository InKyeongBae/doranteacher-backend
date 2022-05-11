package org.triathlongirls.doranssam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.triathlongirls.doranssam.dto.ApiResponse;
import org.triathlongirls.doranssam.dto.UserRequestDto;
import org.triathlongirls.doranssam.dto.UserResponseDto;
import org.triathlongirls.doranssam.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserResponseDto> getMyUserinfo() {
        return new ApiResponse<UserResponseDto>().ok(List.of(userService.getMyInfo()));
    }

    @PatchMapping("/me")
    public ApiResponse<UserResponseDto> updateWritingStep(@Valid @RequestBody UserRequestDto userRequestDto) {
        return new ApiResponse<UserResponseDto>().ok(List.of(userService.patchUser(userRequestDto)));
    }
}
