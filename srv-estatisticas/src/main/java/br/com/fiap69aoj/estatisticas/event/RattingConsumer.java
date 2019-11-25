package br.com.fiap69aoj.estatisticas.event;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.fiap69aoj.estatisticas.model.ConteudoRatting;
import br.com.fiap69aoj.estatisticas.service.EstatisticasService;

@SuppressWarnings("deprecation")
@Component
public class RattingConsumer {

	@Autowired
	private EstatisticasService service;

	@KafkaListener(topics = "${ratting.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consumer(String rattingMessage) {
		rattingMessage = StringEscapeUtils.unescapeJava(rattingMessage);
		rattingMessage = rattingMessage.substring(1).substring(0, rattingMessage.substring(1).length()-1);
		Gson g = new Gson();
		ConteudoRatting ratting = g.fromJson(rattingMessage, ConteudoRatting.class);
		service.inserirRatting(ratting);
	}
}
