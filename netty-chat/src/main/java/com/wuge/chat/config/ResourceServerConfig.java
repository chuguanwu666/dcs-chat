package com.wuge.chat.config;

import com.sun.tools.javac.util.Convert;
import com.wuge.chat.Local.LocalUser;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.io.IOException;
import java.util.Map;

@Configuration
// 标识为资源服务器, 所有发往当前服务的请求，都会去请求头里找token，找不到或验证不通过不允许访问
@EnableResourceServer
//开启方法级别权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String SOURCE_ID = "order";
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                //不创建session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //资源授权规则
                .authorizeRequests().anyRequest().authenticated();


    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 配置当前资源服务器的ID, 会在认证服务器验证(客户端表的resources配置了就可以访问这个服务)
        resources.resourceId(SOURCE_ID);
                // 实现令牌服务, ResourceServerTokenServices实例

    }
    @Bean
    public TokenStore tokenStore(){
        //jwt管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    // JWT 签名秘钥
    private static final String SIGNING_KEY = "wj-key";

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter(){
            @Override
            protected Map<String, Object> decode(String token) {
                Map<String, Object> map = super.decode(token);
                LocalUser.setThreadLocal(map);
                return super.decode(token);
            }
        };
        ClassPathResource classPathResource = new ClassPathResource("public.txt");
        String publicKey = null;
        try {
            publicKey= IOUtils.toString(classPathResource.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }
}