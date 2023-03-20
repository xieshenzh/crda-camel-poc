package com.redhat.crda;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json);

        rest("/hello")
                .get()
                .param().name("test").type(RestParamType.query).dataType("String").endParam()
                .to("direct:hello");


        from("direct:hello")
                .toD("https://httpbin.org/get?bridgeEndpoint=true&test=${header.test}")
                .unmarshal()
                .json(JsonLibrary.Jackson);

    }
}
