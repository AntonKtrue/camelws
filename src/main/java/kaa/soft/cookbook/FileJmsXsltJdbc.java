package kaa.soft.cookbook;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.camel.spi.Policy;
import org.apache.camel.spring.spi.SpringTransactionPolicy;

/**
 * Created by user on 02.05.17.
 */
public class FileJmsXsltJdbc extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        onException(
                com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException.class
        )
                .maximumRedeliveries(0)
                .handled(true)
                .rollback()
                .to("file:src/data/error?fileName=deadLetters.xml&fileExist=Append")
                ;
        from("file:src/data?noop=true")
                //.transacted("PROPAGATION_REQUIRED")
                .choice()
                    .when(xpath("/person"))
                        .bean(new PersonToSqlBean(), "toSql")
                        .to("jms:queue.query-simple")
                        .to("jdbc:myDataSource")
                    .endChoice()
                    .when(xpath("/persons"))
                        .split(new XPathBuilder("/persons/person"))
                        .to("jms:queue.parts")
                        .bean(new PersonToSqlBean(), "toSql")
                        .to("jms:queue.query-map")
                        .to("jdbc:myDataSource")
                    .endChoice();

    }
}
