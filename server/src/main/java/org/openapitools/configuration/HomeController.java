package org.openapitools.configuration;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Home redirection to OpenAPI controller documentation
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }


    @RequestMapping(path = "/api-contract", method = RequestMethod.GET)
    public ResponseEntity<Resource> contractApi() throws IOException {
        File file = new ClassPathResource("autoshowroom.yaml").getFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }

}
