package com.explicit.redditCloneBackend.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Configuration
public class AppConfig {
    @Bean
    public String myKey(){
        return "Your-Secret-Key";

    }
    @Bean
    public PrivateKey privateKey() throws Exception {
        // Assuming your private key is in a file named "private.key"
        byte[] keyBytes = Files.readAllBytes(Paths.get("/home/explicit/Documents/myFOlder/redditBackend/src/main/resources/app.key"));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }
}
