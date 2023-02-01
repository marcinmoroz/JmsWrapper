package com.orange.jmswrapper.messagewrapper.resource;

import com.orange.jmswrapper.messagewrapper.dto.MessageWrapDTO;
import com.orange.jmswrapper.messagewrapper.model.ChannelType;
import com.orange.jmswrapper.messagewrapper.service.MessageWrapperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/messagewrapper/")
@Log4j2
@RequiredArgsConstructor
public class MessageWrapperResource {
    private final MessageWrapperService service;
    @Operation(summary = "Process message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping(value = "processMessage")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<Void> processMessage(@RequestBody MessageWrapDTO message) {
        return service.processMessage(message, ChannelType.HTTP);
    }
}
