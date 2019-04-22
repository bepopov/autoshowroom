package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.model.ResultListOption;
import ru.merann.bopopov.autoshowroom.server.service.OptionService;

import javax.validation.Valid;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-22T11:54:55.246+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class OptionsApiController implements OptionsApi {

    private final NativeWebRequest request;
    private final OptionService optionService;
    private static final Logger logger = LogManager.getLogger(OptionsApiController.class);

    @org.springframework.beans.factory.annotation.Autowired
    public OptionsApiController(NativeWebRequest request, OptionService optionService) {
        this.request = request;
        this.optionService = optionService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ResultListOption> getOptionsByName(@Valid String searchText) {
        ResultListOption makeList = new ResultListOption();
        makeList.setItems(optionService.searchByText(searchText));
        logger.log(Level.TRACE, String.format(
                "Option list successfully added to response body. Returning 200 OK to client"));
        return ResponseEntity.ok().body(makeList);
    }
}
