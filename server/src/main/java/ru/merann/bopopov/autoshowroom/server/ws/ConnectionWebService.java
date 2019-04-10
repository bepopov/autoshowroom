package ru.merann.bopopov.autoshowroom.server.ws;

import javax.jws.WebService;

@WebService
public interface ConnectionWebService {

    Long connect(String username);

}
