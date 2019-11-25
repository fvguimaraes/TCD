package br.com.fiap69aoj.catalogo.events;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatalogoProducer {
	
	@Value("${ratting.topic}")
    private String rattingTopic;
 
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public CatalogoProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void send(String mensagem) {
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(rattingTopic, mensageKey, mensagem);        
    }
}
