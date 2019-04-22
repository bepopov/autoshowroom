package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-22T12:15:54.753+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class StatusesApiController implements StatusesApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public StatusesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
