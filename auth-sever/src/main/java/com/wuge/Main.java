package com.wuge;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import net.minidev.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;

import java.security.interfaces.RSAPublicKey;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
        UserDetailsService bean = run.getBean(UserDetailsService.class);

        System.out.println(bean);
//        KeyStoreKeyFactory keyStoreKeyFactory=new KeyStoreKeyFactory(new ClassPathResource("youlai.jks"),"123456".toCharArray());
//        KeyPair xx =  keyStoreKeyFactory.getKeyPair("youlai");
//        System.out.println(xx.getPrivate().getAlgorithm());
//        RSAPublicKey publicKey = (RSAPublicKey) xx.getPublic();
//        RSAKey key = new RSAKey.Builder(publicKey).build();
//        JSONObject jsonObject = new JWKSet(key).toJSONObject();
//
//        System.out.println(jsonObject);
    }
}
