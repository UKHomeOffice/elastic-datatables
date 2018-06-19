package uk.gov.homeoffice.elastic.provider.elastic;

import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ElasticConfig {

    @Value( "${property1}" )
    private String property1="";

    @Value( "${property2}" )
    private String property2="";

    @Value( "${property3}" )
    private String property3="";

    @Value( "${property4}" )
    private String property4="";

    @Value( "${property5}" )
    private String property5="";

    @Value( "${property6}" )
    private String property6="";

    @Value( "${property7}" )
    private String property7="";

    @Value( "${property8}" )
    private String property8="";

    @Value( "${property9}" )
    private String property9="";

    @Value( "${property10}" )
    private String property10="";

    @Value( "${property11}" )
    private String property11="";

    @Value( "${property12}" )
    private String property12="";

    //TODO a better way
    public Map<String, String> queryMap(){
        Map<String, String> queries = new HashMap<>();
        queries.put("property1", property1);
        queries.put("property2", property2);
        queries.put("property3", property3);
        queries.put("property4", property4);
        queries.put("property5", property5);
        queries.put("property6", property6);
        queries.put("property7", property7);
        queries.put("property8", property8);
        queries.put("property9", property9);
        queries.put("property10", property10);
        queries.put("property11", property11);
        queries.put("property12", property12);
        return queries;
    }

}
