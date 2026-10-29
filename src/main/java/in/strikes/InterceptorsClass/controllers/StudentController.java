package in.strikes.InterceptorsClass.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PostMapping
    public ResponseEntity<String> create(){
        System.out.println("studnet is created");
        return ResponseEntity.ok("student is created");
    }
}
