package br.com.fiap69aoj.chamados.eventos;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChamadoProducer {
	
	@Value("${chamado.topic}")
    private String chamadoTopic;
 
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public ChamadoProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public void send(String mensagem) {
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(chamadoTopic, mensageKey, mensagem);
    }

}
