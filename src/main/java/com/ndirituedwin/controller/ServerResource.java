package com.ndirituedwin.controller;

import com.ndirituedwin.Enum.Status;
import com.ndirituedwin.Service.ServerServiceimpl;
import com.ndirituedwin.model.Response;
import com.ndirituedwin.model.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static com.ndirituedwin.Enum.Status.SERVER_UP;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
@RestController
@RequestMapping("/api/server")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class ServerResource {

    private final ServerServiceimpl serverServiceimpl;

    @GetMapping("/list")
    public ResponseEntity<Response> getservers() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(3);
//        throw new InterruptedException("something ent wrong");

        return ResponseEntity.ok(
          Response.builder()
                  .timeStamp(LocalDateTime.now())
                  .data(Map.of("servers",serverServiceimpl.list(30)))
                  .status(OK)
                  .statusCode(OK.value())
                .build()
        );
    }
    @GetMapping("ping/{ipAddress}")
    public ResponseEntity<Response> pingserver(@PathVariable("ipAddress") String ipaddress) throws IOException {
        Server server=serverServiceimpl.ping(ipaddress);
       return ResponseEntity.ok(
               Response.builder()
                       .timeStamp(LocalDateTime.now())
                       .data(Map.of("server",server))
                       .message(server.getStatus() == SERVER_UP ? "ping success":"ping failed")
                       .status(OK)
                        .statusCode(OK.value())
                       .build()
       );
    }
    @PostMapping("/save")
    public ResponseEntity<Response> saveserver(@RequestBody @Valid Server server){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("server",serverServiceimpl.server(server)))
                        .message("Server created")
                        .status(OK)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getserverbyid(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                .data(Map.of("server", serverServiceimpl.findById(id)))
                .message("Server retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }
    @DeleteMapping("/deleteserverbyid/{id}")
    public ResponseEntity<Response> deleteserverbyid(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("deletedserver",serverServiceimpl.deleteById(id)))
                        .message("server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping(path = "/image/{fileName}",produces = IMAGE_PNG_VALUE)
 public byte[] getserverimages(@PathVariable("fileName") String filename) throws IOException {
        log.info("reached here {}",filename);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Downloads/images/"+filename));
    }
}
