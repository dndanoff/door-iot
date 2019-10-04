package io.github.dndanoff.doormonitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;

@ConfigurationProperties(prefix = "config")
@Component
@Getter
public class AppConfig {

}
