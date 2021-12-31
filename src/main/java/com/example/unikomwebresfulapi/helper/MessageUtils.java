package com.example.unikomwebresfulapi.helper;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils implements MessageSourceAware {

    private static MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        MessageUtils.messageSource = messageSource;
    }

    public static String getMessage(String key, String... params) {
        return messageSource.getMessage(key, params, new Locale("vi"));
    }
}
