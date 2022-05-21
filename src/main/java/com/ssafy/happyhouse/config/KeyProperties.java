package com.ssafy.happyhouse.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("key")
public final class KeyProperties {
    private final String openApiKey;
    private final String secretKey;
}
