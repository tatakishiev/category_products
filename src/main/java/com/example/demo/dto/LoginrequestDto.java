package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginrequestDto {

    @NotNull
    public String username;
    @NotNull
    public String password;
}
