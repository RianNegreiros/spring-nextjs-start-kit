package xyz.riannegreiros.spring_nextjs_start_kit.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    
    private static ApplicationContext context;
    
    public static <T> T bean(Class<T> beanType) {
        return context.getBean(beanType);
    }
    
    public static Object bean(String name) { return context.getBean(name); }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
