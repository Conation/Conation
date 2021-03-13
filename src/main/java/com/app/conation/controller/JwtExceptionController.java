package com.app.conation.controller;

import com.app.conation.advice.exceptions.JWTDecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtExceptionController {

    @RequestMapping(value="/exception/entrypoint", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public void entrypointException() {
        throw new JWTDecodeException();
    }
}
