package com.stussy.stussyclone20220930donghwan.api;

import com.stussy.stussyclone20220930donghwan.dto.CMRespDto;
import com.stussy.stussyclone20220930donghwan.dto.RegisterReqDto;
import com.stussy.stussyclone20220930donghwan.dto.validation.ValidationSequence;
import exception.repository.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto,
                                      BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(registerReqDto);
        accountService.register(registerReqDto);

        return ResponseEntity.created(URL.create("/account/login")).body(new CMRespDto<>("회원가입 성공", registerReqDto.getEmail()));
    }

}
