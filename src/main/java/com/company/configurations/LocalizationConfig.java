package com.company.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

@Configuration
public class LocalizationConfig {

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(getMessageResource());
        return bean;
    }
    @Bean
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setDefaultLocale(new Locale("en")); // if local not found use this
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public AcceptHeaderLocaleResolver localResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setSupportedLocales(Arrays.asList(
                new Locale("en"),
                new Locale("ru"),
                new Locale("uz")));
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }



}
