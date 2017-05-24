package kaa.soft.cookbook;

import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by user on 04.05.17.
 */
public class Loader2 {
    public static void main(String[] args) throws Exception {
        ApplicationContext spring = new ClassPathXmlApplicationContext("application.xml");
        CamelContext camelContext = (CamelContext) spring.getBean("FIRST-CAMEL-CONTEXT");
        camelContext.start();
        Thread.sleep(3000);
        camelContext.stop();

    }
}
