package com.explicit.redditCloneBackend.Controller;

import com.explicit.redditCloneBackend.Config.Dto.JwtAuthenticationResponse;
import com.explicit.redditCloneBackend.Config.Dto.LoginRequest;
import com.explicit.redditCloneBackend.Config.Dto.RefreshTokenRequest;
import com.explicit.redditCloneBackend.Config.Dto.RegisterRequest;
import com.explicit.redditCloneBackend.Jwt.*;
import com.explicit.redditCloneBackend.Service.AuthService;
import com.explicit.redditCloneBackend.Exception.RedditErrorException;
import com.explicit.redditCloneBackend.Service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest) throws RedditErrorException {
        authService.signUpUser(registerRequest);
        return new ResponseEntity<>("user registration successfully ", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyToken(@PathVariable String token) throws RedditErrorException {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated", HttpStatus.OK);
    }

    @PostMapping("/authenticated")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);

    }
    @PostMapping("/refresh/token")
    public JwtAuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
