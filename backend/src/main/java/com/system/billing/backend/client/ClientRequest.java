package com.system.billing.backend.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRequest (
    @NotBlank String name,
    @NotBlank String nit,
    @NotBlank @Email String email,
    String tel,
    String address
)
{} 
