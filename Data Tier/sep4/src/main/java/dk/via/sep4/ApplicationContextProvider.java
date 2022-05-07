package dk.via.sep4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

@Component
public class ApplicationContextProvider implements ApplicationContextAware
{


  private static ApplicationContext context;


  public ApplicationContext getApplicationContext() {
    return context;
  }

  @Override
  public void setApplicationContext(ApplicationContext ctx) {
    context = ctx;
  }


}

