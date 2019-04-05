package ru.merann.bopopov.autoshowroom.soapclient.config;

import org.jline.reader.LineReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanOverrider implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lineReader")) {
            LineReader lineReader = (LineReader) bean;
            //lineReader.setOpt(LineReader.Option.COMPLETE_IN_WORD);
            return lineReader;
        }
        else {
            return bean;
        }
    }
}