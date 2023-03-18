package com.redhat.crda;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class Routes extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json);

        rest("/hello")
                .get()
                .to("direct:hello");


        from("direct:hello")
                .setBody().constant("Hello World");

    }
}
