package io.github.dndanoff.doormonitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;

@ConfigurationProperties(prefix = "config")
@Component
@Data
public class AppConfig {

	private final Db db = new Db();
	private final Jooq jooq = new Jooq();

	@ConfigurationProperties(prefix = "jooq")
	@Data
	public class Jooq {
		private String sqlDialect;
	}

	@ConfigurationProperties(prefix = "db")
	@Data
	public class Db {
		private String schema;
		private String schemaDefault;
	}
}
