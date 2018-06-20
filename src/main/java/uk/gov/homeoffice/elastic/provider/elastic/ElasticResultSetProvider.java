package uk.gov.homeoffice.elastic.provider.elastic;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.gov.homeoffice.elastic.dataTableModel.DataTablesSearch;
import uk.gov.homeoffice.elastic.model.Record;
import uk.gov.homeoffice.elastic.model.ResultSet;
import uk.gov.homeoffice.elastic.provider.LRUCache;
import uk.gov.homeoffice.elastic.provider.ResultSetProvider;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ElasticResultSetProvider implements ResultSetProvider<Record> {

    private LRUCache<String, Record> cache = new LRUCache<>(10000);

    @Autowired
    ElasticRequest elasticRequest;

    @Autowired
    ElasticConfig elasticConfig;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ElasticResultSetProvider() {
    }

    @Override
    public Record uniqueRecord(String key) {
        if (cache.contains(key)) {
            return cache.getCache(key);
        }
        return new Record();
    }

    @Override
    public ResultSet<Record> getResultSet(DataTablesSearch search, int offset, int length) {
        List<Record> l = new ArrayList<>();
        int size = 0;
        try {
            String key = search.getValue();
            System.out.println("Search for " + search.getValue());
            String queryJson = elasticRequest.buildPostJson(search.getValue());
            System.out.println("Search query " + queryJson);
            String response = elasticRequest.makeRequest(queryJson);
            Map<String, Record> records = getRecords(response);
            records.forEach((k, v) -> {
                cache.putCache(k, v);
            });
            List<Record> recs = new ArrayList<>(records.values());
            size = recs.size();
            System.out.println("Search found " + size);
            l.addAll(page(recs, offset, length));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //for all intents and purposes the filtered via search is the size
        return new ResultSet<>(size, size, l);
    }

    private List<Record> page(List<Record> recs, int offset, int length) {
        if (recs.isEmpty()) {
            return recs;
        }
        int size = recs.size();
        if (offset >= size) {
            return new ArrayList<>();
        }
        int toIndex = offset + length;
        return recs.subList(offset, toIndex > size ? size : toIndex);
    }

    private Map<String, Record> getRecords(String json) {
        System.out.println(json);
        Map<String, Record> l = new LinkedHashMap<>();
        List<Map<String, Object>> dataExtract = new ArrayList<>();
        if (json == null) {
            return l;
        }
        if (!json.contains("hits")) {
            return l;
        }

        JSONArray ja = JsonPath.read(json, "$.hits.hits[*]");
        int length = ja.size();
        for (int i = 0; i < length; i++) {
            String frag = "$.hits.hits[" + i + "]";
            Map<String, Object> record = new HashMap<>();
            for (Map.Entry<String, String> qm : elasticConfig.queryMap().entrySet()) {
                if(!StringUtils.isEmpty(qm.getValue())) {
                    String value = frag + qm.getValue();
                    record.put(qm.getKey(), JsonPath.read(json, value));
                }
                else{
                    record.put(qm.getKey(), "");
                }
            }
            dataExtract.add(record);
        }

        for (Map<String, Object> m : dataExtract) {
            Record record = new Record();
            record.setProperty1(convert(m.get("property1")));
            record.setProperty2(convert(m.get("property2")));
            record.setProperty3(convert(m.get("property3")));
            record.setProperty4(convert(m.get("property4")));
            record.setProperty5(convert(m.get("property5")));
            record.setProperty6(convert(m.get("property6")));
            record.setProperty7(convert(m.get("property7")));
            record.setProperty8(convert(m.get("property8")));
            record.setProperty9(convert(m.get("property9")));
            record.setProperty10(convert(m.get("property10")));
            record.setProperty11(convert(m.get("property11")));
            record.setProperty12(convert(m.get("property12")));
            l.put(record.getProperty1(), record);
        }
        return l;
    }

    //TODO cover more types
    private String convert(Object o) {
        if (o == null) {
            return "";
        }
        if (o instanceof Integer) {
            return ((Integer) o).toString();
        }
        if (o instanceof String) {
            String rec = (String) o;
            return rec;
        }
        return o.toString();
    }

    public ElasticRequest getElasticRequest() {
        return elasticRequest;
    }

    public void setElasticRequest(ElasticRequest elasticRequest) {
        this.elasticRequest = elasticRequest;
    }

    public ElasticConfig getElasticConfig() {
        return elasticConfig;
    }

    public void setElasticConfig(ElasticConfig elasticConfig) {
        this.elasticConfig = elasticConfig;
    }
}
