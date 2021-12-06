package com.wuge.config;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    // 资源ID
    private static final String SOURCE_ID = "order";
    private static final int ACCESS_TOKEN_TIMER = 60*60 ;
    private static final int REFRESH_TOKEN_TIMER = 60 ;

@Autowired
KeyPair keyPair;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("myapp").resourceIds(SOURCE_ID).authorizedGrantTypes("password", "refresh_token")
                .scopes("all").authorities("ADMIN").secret(bCryptPasswordEncoder().encode("lxapp")).accessTokenValiditySeconds(ACCESS_TOKEN_TIMER)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_TIMER);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.getTokenGranter();
        endpoints.tokenEnhancer(accessTokenConverter());
        endpoints.accessTokenConverter(accessTokenConverter());
//        endpoints.tokenStore(tokenStore());
        endpoints.authenticationManager(authenticationManager).reuseRefreshTokens(true);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        // 允许表单认证
        oauthServer.allowFormAuthenticationForClients();
    }
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("youlai.jks"), "123456".toCharArray());
        KeyPair keyPair = factory.getKeyPair(
                "youlai", "123456".toCharArray());
        return keyPair;
    }
    // JWT
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
            /***
             * 重写增强token方法,用于自定义一些token总需要封装的信息
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

                String userName = authentication.getUserAuthentication().getName();
                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
                final Map<String, Object> additionalInformation = new HashMap<>();
                // Map假装用户实体
                Map<String, String> userinfo = new HashMap<>();
                userinfo.put("id", "1");
                userinfo.put("username", "LiaoXiang");
                userinfo.put("qqnum", "438944209");
                userinfo.put("userFlag", "1");
                additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
                return enhancedToken;
            }
        };
        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setKeyPair(keyPair);
        return accessTokenConverter;
    }

//    @Bean
//    public TokenStore tokenStore() {
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        return tokenStore;
//    }


}
