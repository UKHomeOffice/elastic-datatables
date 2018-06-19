package uk.gov.homeoffice.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import uk.gov.homeoffice.elastic.provider.elastic.ElasticConfig;
import uk.gov.homeoffice.elastic.provider.elastic.ElasticRequest;
import uk.gov.homeoffice.elastic.provider.elastic.ElasticResultSetProvider;
import uk.gov.homeoffice.elastic.provider.ResultSetProvider;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public ElasticConfig elasticConfig() {
        return new ElasticConfig();

    }

    @Bean
    public ResultSetProvider elasticResultSetProvider() {
        return new ElasticResultSetProvider();
    }

    @Bean
    public ElasticRequest elasticRequest() {
        return new ElasticRequest();
    }


}
