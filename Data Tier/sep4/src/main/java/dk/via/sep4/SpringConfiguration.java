package dk.via.sep4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Spring configuration.
 */
@Configuration
public class SpringConfiguration {

    /**
     * Context provider application context provider.
     *
     * @return the application context provider
     */
    @Bean
    public static ApplicationContextProvider contextProvider() {
        return new ApplicationContextProvider();
    }

}