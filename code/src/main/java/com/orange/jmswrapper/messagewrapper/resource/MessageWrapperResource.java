package com.orange.jmswrapper.messagewrapper.resource;

import com.orange.jmswrapper.messagewrapper.dto.MessageWrapDTO;
import com.orange.jmswrapper.messagewrapper.model.ChannelType;
import com.orange.jmswrapper.messagewrapper.service.MessageWrapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/messagewrapper/")
@Log4j2
@RequiredArgsConstructor
public class MessageWrapperResource {
    private final MessageWrapperService service;
    @ApiOperation(value = "Process message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation completed successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "ProductDTO not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    @GetMapping(value = "processMessage")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> processMessage(@RequestBody MessageWrapDTO message) {
        return service.processMessage(message, ChannelType.HTTP);
    }
}
