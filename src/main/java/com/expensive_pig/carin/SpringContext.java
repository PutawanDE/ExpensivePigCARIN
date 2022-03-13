package com.expensive_pig.carin;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// from https://confluence.jaytaala.com/display/TKB/Super+simple+approach+
// to+accessing+Spring+beans+from+non-Spring+managed+classes+and+POJOs
@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * @param beanClass class to look for its Spring managed bean instance
     * @return the Spring managed bean instance of the given class type if it exists,
     * returns null otherwise
     */
    public static <T extends Object> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContext.context = context;
    }
}
