package kaa.soft.cookbook;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

/**
 * Created by user on 01.05.17.
 */
public class Loader {
    public static void main(String[] args) throws Exception {
        ApplicationContext spring = new ClassPathXmlApplicationContext("application.xml");
        ConnectionFactory connectionFactory = (ConnectionFactory) spring.getBean("jmsConnectionFactory");
        PlatformTransactionManager transactionManager = (PlatformTransactionManager) spring.getBean("jmsTransactionManager");
        JmsComponent component = JmsComponent.jmsComponentTransacted(connectionFactory, transactionManager);
        component.getConfiguration().setConcurrentConsumers(1);


        String url = "jdbc:mysql://localhost:3306/learn";
        DataSource dataSource = setupDataSource(url);
        SimpleRegistry reg = new SimpleRegistry();
        reg.put("myDataSource",dataSource);

        CamelContext context = new DefaultCamelContext(reg);
        //ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        context.addComponent("jms", component);

        context.addRoutes(new FileJmsXsltJdbc());
        context.start();
        Thread.sleep(5000);
        context.stop();

        }

    private static DataSource setupDataSource(String url) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("90559067");
        ds.setUrl(url);
        return ds;
    }
}

