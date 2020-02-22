package com.example.demo.entity;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Product {

    @NotBlank
    private String sku;

    @Min(10)
    private int page;

    @Max(1000)
    private int pageSize;

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @Email(message = "邮箱格式错误")
    private String email;
}
