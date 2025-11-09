package com.sumit.tcs.CustomAnnotation.util;

import com.sumit.tcs.CustomAnnotation.annotations.MyAnnotation.ContentValue;
import com.sumit.tcs.CustomAnnotation.model.Message;
import com.sumit.tcs.CustomAnnotation.repository.MessageRepo;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Order(2)
public class AnnotationConsumer implements CommandLineRunner{

    @Autowired
    MessageRepo messageRepository;

    @Autowired
    ApplicationContext context;

    private String getDetailedMessageFromDB(String key){
        Message message = messageRepository.findById(key).orElse(new Message(key, "Message not found for "+key));
        return message.getMessageValue();
    }

    private void processClass(Class<?> c) {

        Field[] declaredFields = c.getDeclaredFields();
        for(Field f: declaredFields){
            if(f.isAnnotationPresent(ContentValue.class)){
                System.out.println("Found annotation in class " + c.getSimpleName());
                String key = f.getAnnotation(ContentValue.class).value();
                try {
                    Object obj = context.getBean(c);
                    f.setAccessible(true);
                    f.set(obj, getDetailedMessageFromDB(key));
                }
                catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
                    System.out.println("No Such bean for :: " + c.getName());
                    System.out.println(noSuchBeanDefinitionException.getMessage());
                }
                catch (BeanDefinitionStoreException beanDefinitionStoreException){
                    System.out.println("Bean isn't a prototype for :: " + c.getName());
                    System.out.println(beanDefinitionStoreException.getMessage());
                }
                catch (BeansException beansException){
                    System.out.println("bean could not be created for :: "+ c.getName());
                    System.out.println(beansException.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Exception while assigning value to custom annotation field - " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            for(String basePackage: AutoConfigurationPackages.get(context.getAutowireCapableBeanFactory())){
                Reflections reflections = new Reflections(basePackage, new SubTypesScanner(false));
                reflections.getSubTypesOf(Object.class)
                        .forEach(this::processClass);
            }

        }
        catch (Exception e){
            System.out.println("Exception while scanning package for classes :: "+ e.getMessage());
        }
    }
}
