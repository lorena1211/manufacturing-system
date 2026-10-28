package com.system.billing.backend.client;

import java.time.LocalDateTime;

public record ClientResponse(
    Long id, String name, String nit, String email, String tel, String address, Boolean active, LocalDateTime creationDate)
{
    public static ClientResponse from(Client c) {
        return new ClientResponse(c.getId(), c.getName(), c.getNit(), c.getEmail(), c.getTel(), c.getAddress(), c.getActive(), c.getCreationDate());
    }
}
