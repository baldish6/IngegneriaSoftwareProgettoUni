package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Auth.JwtUtil;
import com.unicam.hackhub.Auth.RespInfo;
import com.unicam.hackhub.Model.Utente;
import com.unicam.hackhub.Service.GestoreUtente;
import com.unicam.hackhub.Util.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {



    private final JwtUtil jwtService;

    private final GestoreUtente gestoreUtente;

    public AuthController(JwtUtil jwtService, GestoreUtente gestoreUtente) {
        this.jwtService = jwtService;
        this.gestoreUtente = gestoreUtente;
    }

    @PostMapping("/register")
    public ResponseEntity<RespInfo> register(@RequestBody UserInfo input) {
        Utente registeredUser = gestoreUtente.register(input);
        String jwtToken = jwtService.generateToken(registeredUser);
        RespInfo registerResponse = new RespInfo(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<RespInfo> authenticate(@RequestBody UserInfo  input){
        Utente authenticatedUser = gestoreUtente.login(input);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        RespInfo loginResponse = new RespInfo(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
