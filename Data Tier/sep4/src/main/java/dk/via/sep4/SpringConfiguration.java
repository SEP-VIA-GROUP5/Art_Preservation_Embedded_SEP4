package dk.via.sep4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author $(Alina Chelmus)
 */
@Configuration
public class SpringConfiguration
{
@Bean
  public static ApplicationContextProvider contextProvider (){
    return new ApplicationContextProvider();

  }
}
