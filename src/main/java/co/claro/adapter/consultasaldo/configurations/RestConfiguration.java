package co.claro.adapter.consultasaldo.configurations;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * adapter-consulta-saldo
 * RestConfiguration.java
 * Oct 31, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@Component
public class RestConfiguration extends RouteBuilder {

    @Value("${camelComponent:servlet}")
    private String camelComponent;

    @Value("${swagger.api.path:'/api'}")
    private String apiPath;

    @Value("${swagger.api.enableCors:true}")
    private Boolean apiEnableCors;

    @Value("${swagger.api-docs.path:'/api-docs'}")
    private String apiDocsPath;

    @Value("${swagger.api-docs.version:'0.0.1'}")
    private String apiDocsVersion;

    @Value("${info.app.name:api}")
    private String apiDocsTitle;

    @Override
    public void configure() throws Exception {

        // Rest Configuration
        restConfiguration()
            .component(camelComponent)
            .contextPath(apiPath)
            .enableCORS(apiEnableCors)
            .corsHeaderProperty("Access-Control-Allow-Methods", "POST")
                .apiContextPath(apiDocsPath)
                .apiProperty("api.title", apiDocsTitle)
                .apiProperty("api.version", apiDocsVersion)
                .apiProperty("cors", apiEnableCors.toString())
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint","true")
                .apiVendorExtension(true);
    }
}