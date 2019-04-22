package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.model.ResultListStatus;
import ru.merann.bopopov.autoshowroom.server.model.Status;

import java.util.Arrays;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-22T12:15:54.753+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class StatusesApiController implements StatusesApi {

    private final NativeWebRequest request;
    private static final Logger logger = LogManager.getLogger(StatusesApiController.class);

    @org.springframework.beans.factory.annotation.Autowired
    public StatusesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ResultListStatus> getStatuses() {
        ResultListStatus statuses = new ResultListStatus();
        statuses.setItems(Arrays.asList(Status.values()));
        logger.log(Level.TRACE, String.format(
                "Status list successfully added to response body. Returning 200 OK to client"));
        return ResponseEntity.ok().body(statuses);
    }
}
