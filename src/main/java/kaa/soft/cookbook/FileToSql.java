package kaa.soft.cookbook;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Map;

/**
 * Created by user on 02.05.17.
 */
public class FileToSql {

    public String toString(@Headers Map<String, Object> header, @Body Object body) {
        StringBuilder sb = new StringBuilder();
        String filename = (String)header.get("CamelFileNameOnly");
        String escapedFileNmae = StringEscapeUtils.escapeJava(filename).replace("\'","");
        String filePath = StringEscapeUtils.escapeJava((String)header.get("CamelFilePath"));
        sb.append("insert into ");
        return sb.toString();
    }
}
