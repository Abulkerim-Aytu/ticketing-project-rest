package com.cydeo.service;


import com.cydeo.dto.UserDTO;

import javax.ws.rs.core.Response;

public interface KeycloakService {
    // By this userCreate we can create in the keycleak user.
    // This is the user that created in the keycloak. Response provide us the user that created in keycloak.
    Response userCreate(UserDTO dto);
    void delete(String username);

}
