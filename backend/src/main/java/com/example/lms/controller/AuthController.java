package com.example.lms.controller;

import com.example.lms.model.User;
import com.example.lms.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UserRepository users;
    private final BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

    // 8+ chars, one lowercase, one uppercase, one digit, one special character
    private static final String PASSWORD_RULE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";

    public AuthController(UserRepository users) { this.users = users; }

    private ResponseEntity<?> bad(String msg) {
        return ResponseEntity.badRequest().body(Map.of("error", msg));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> b) {
        String name = b.getOrDefault("name", "").trim();
        String email = b.getOrDefault("email", "").trim().toLowerCase();
        String mobile = b.getOrDefault("mobile", "").trim();
        String pw = b.getOrDefault("password", "");

        LocalDate dob;
        try { dob = LocalDate.parse(b.getOrDefault("dob", "")); }
        catch (Exception e) { return bad("Enter a valid date of birth"); }

        if (name.isEmpty()) return bad("Enter your name");
        if (!dob.isBefore(LocalDate.now())) return bad("Date of birth must be in the past");
        if (!mobile.matches("[6-9]\\d{9}")) return bad("Mobile must be a 10-digit number");
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) return bad("Enter a valid email");
        if (!pw.matches(PASSWORD_RULE))
            return bad("Password needs 8+ characters with uppercase, lowercase, number and special character");
        if (users.findByEmail(email).isPresent())
            return ResponseEntity.status(409).body(Map.of("error", "Email already registered"));

        User u = new User();
        u.name = name; u.dob = dob; u.mobile = mobile;
        u.email = email; u.password = enc.encode(pw);
        users.save(u);
        return ResponseEntity.ok(Map.of("userId", u.id, "email", u.email));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> b) {
        String email = b.getOrDefault("email", "").trim().toLowerCase();
        String pw = b.getOrDefault("password", "");
        return users.findByEmail(email)
                .filter(u -> enc.matches(pw, u.password))
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(Map.of(
                        "userId", u.id, "email", u.email, "name", u.name == null ? "" : u.name)))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid email or password")));
    }
}
