package com.loupiart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.loupiart.model.*;
import com.loupiart.repository.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DrawingController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DrawingRepository drawingRepo;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String username, @RequestParam String password) {
        boolean isLoggedIn = userRepo.findByUsernameAndPassword(username, password) != null;
        return ResponseEntity.ok(isLoggedIn);
    }

    @PostMapping("/drawing")
    public ResponseEntity<String> saveDrawing(@RequestParam String username, @RequestBody String content) {
        User user = userRepo.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        Drawing drawing = drawingRepo.findByUser(user);
        if (drawing == null) {
            drawing = new Drawing();
        }
        drawing.setUser(user);
        drawing.setContent(content);
        drawingRepo.save(drawing);

        return ResponseEntity.status(HttpStatus.CREATED).body("Drawing saved");
    }

    @GetMapping("/drawing")
    public ResponseEntity<String> getDrawing(@RequestParam String username) {
        User user = userRepo.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        Drawing drawing = drawingRepo.findByUser(user);
        if (drawing != null) {
            return ResponseEntity.ok(drawing.getContent());
        } else {
            return ResponseEntity.ok("");
        }
    }
}