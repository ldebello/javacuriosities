package ar.com.javacuriosities.facade.api.converters;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    /**
     * Media Type constant for YAML documents.
     */
    private static final String APPLICATION_YAML = "application/x-yaml";
    
    /**
     * Default constructor for {@link YamlJackson2HttpMessageConverter}.
     */
    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper(), MediaType.parseMediaType(APPLICATION_YAML));
    }
}