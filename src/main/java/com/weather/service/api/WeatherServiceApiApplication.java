package com.weather.service.api;

import com.weather.service.api.config.cache.CachingProperties;
import com.weather.service.api.constants.ApplicationConstants;
import com.weather.service.api.util.DefaultProfileUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@EnableConfigurationProperties({CachingProperties.class})
public class WeatherServiceApiApplication   implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(WeatherServiceApiApplication.class);
    private final Environment env;

    public WeatherServiceApiApplication(Environment env) {
        this.env = env;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(ApplicationConstants.SPRING_PROFILE_DEVELOPMENT)
                && activeProfiles.contains(ApplicationConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have wrong configs on your application! It should not run " +
                    "with both the 'dev' and 'prod' profiles at the same time.");
        }

        if (activeProfiles.contains(ApplicationConstants.SPRING_PROFILE_PRODUCTION)
                && activeProfiles.contains(ApplicationConstants.SPRING_PROFILE_TEST)) {
            log.error("You have wrong configs on your application! It should not " +
                    "run with both the 'prod' and 'test' profiles at the same time.");
        }

    }
    private static void logApplicationStartup(Environment env) {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port") != null ? env.getProperty("server.port") : "8080";
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}{}\n\t" +
                        "External: \t{}://{}:{}{}\n\t" +
                        "Swagger: \t{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                activeProfiles.contains(ApplicationConstants.SPRING_PROFILE_SWAGGER) ? protocol + "://" + hostAddress + ":" + serverPort + contextPath + "swagger-ui/index.html" : "No Swagger Deployed",
                protocol,
                hostAddress,
                serverPort,
                env.getActiveProfiles());
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WeatherServiceApiApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

}
