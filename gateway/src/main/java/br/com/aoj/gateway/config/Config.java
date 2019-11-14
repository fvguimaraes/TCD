package br.com.aoj.gateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
public class Config {

    @Bean
    @Qualifier("PrettyGson")
    public Gson getPrettyGson(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
}
