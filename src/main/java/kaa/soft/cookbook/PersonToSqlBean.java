package kaa.soft.cookbook;

import org.apache.camel.language.XPath;

import java.util.Locale;

/**
 * Created by user on 02.05.17.
 */
public class PersonToSqlBean {
    public String toSql(@XPath("person/@user") String user,
                        @XPath("person/firstName") String firstName,
                        @XPath("person/lastName") String lastName,
                        @XPath("person/city") String city) {
        String sql = String.format(Locale.US,
                "insert into persons (user, first_name, last_name, city) values " +
                        "('%s', '%s', '%s', '%s')", user, firstName, lastName, city);
        return sql;
    }
}
