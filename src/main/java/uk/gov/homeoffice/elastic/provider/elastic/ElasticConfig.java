package uk.gov.homeoffice.elastic.provider.elastic;

import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticConfig {

    @Value( "${property1}" )
    private String property1;

    @Value( "${property2}" )
    private String property2;

    @Value( "${property3}" )
    private String property3;

    @Value( "${property4}" )
    private String property4;

    @Value( "${property5}" )
    private String property5;

    @Value( "${property6}" )
    private String property6;

    @Value( "${property7}" )
    private String property7;

    @Value( "${property8}" )
    private String property8;

    @Value( "${property9}" )
    private String property9;

    @Value( "${property10}" )
    private String property10;

    @Value( "${property11}" )
    private String property11;

    @Value( "${property12}" )
    private String property12;

    @Value( "${column1}" )
    private String column1;

    @Value( "${column2}" )
    private String column2;

    @Value( "${column3}" )
    private String column3;

    @Value( "${column4}" )
    private String column4;

    @Value( "${column5}" )
    private String column5;

    @Value( "${column6}" )
    private String column6;

    @Value( "${column7}" )
    private String column7;

    @Value( "${column8}" )
    private String column8;

    @Value( "${column9}" )
    private String column9;

    @Value( "${column10}" )
    private String column10;

    @Value( "${column11}" )
    private String column11;

    @Value( "${column12}" )
    private String column12;

    public List<String> columns(){
        List<String> cols = new ArrayList<>();
        for(int i=1; i <=12; i++){
            String value = getField("column"+i);
            if(!StringUtils.isEmpty(value)){
                cols.add(value);
            }
        }
        return cols;
    }

    private String getField(String name){
        try {
            Field field = this.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Object o=field.get(this);
            return o==null ? null: (String)o;
        }catch (Exception e){
            return null;
        }
    }

    public Map<String, String> queryMap(){
        Map<String, String> queries = new HashMap<>();
        for(int i=1; i <=12; i++){
            String value = getField("property"+i);
            if(!StringUtils.isEmpty(value)){
                queries.put("property"+i, value);
            }
        }
        return queries;
    }

}
