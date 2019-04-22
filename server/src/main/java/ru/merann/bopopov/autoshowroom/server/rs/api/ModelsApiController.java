package ru.merann.bopopov.autoshowroom.server.rs.api;

import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.merann.bopopov.autoshowroom.server.model.ResultListModel;
import ru.merann.bopopov.autoshowroom.server.service.ModelService;

import javax.validation.Valid;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-04-22T11:54:55.246+03:00[Europe/Moscow]")

@Controller
@RequestMapping("${openapi.autoshowroom.base-path:}")
public class ModelsApiController implements ModelsApi {

    private final NativeWebRequest request;
    private final ModelService modelService;

    @org.springframework.beans.factory.annotation.Autowired
    public ModelsApiController(NativeWebRequest request, ModelService modelService) {
        this.request = request;
        this.modelService = modelService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ResultListModel> getModelsByName(@Valid String searchText) {
        ResultListModel makeList = new ResultListModel();
        makeList.setItems(modelService.searchByText(searchText));
        logger.log(Level.TRACE, String.format(
                "Make list successfully added to response body. Returning 200 OK to client"));
        return ResponseEntity.ok().body(makeList);
    }
}
