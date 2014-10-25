package no.ciber.regex.facade;

import no.ciber.regex.service.ServiceConfig;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

/**
 * This software is written by Michael on 21/10/2014.
 */
@Configuration
@ComponentScan
@Import(ServiceConfig.class)
public class FacadeConfig {
}
