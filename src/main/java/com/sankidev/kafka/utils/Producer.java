package com.sankidev.kafka.utils;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sankidev.kafka.avro.Transaction;

@Service
public class Producer {

	Logger log = LoggerFactory.getLogger(Producer.class);

	@Value("${topic.transaction.req}")
	private String TOPIC;

	private final KafkaTemplate<String, Transaction> kafkaTemplate;

	@Autowired
	public Producer(KafkaTemplate<String, Transaction> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;

	}

	public void sendMessage(Transaction transaction) {
		ProducerRecord<String, Transaction> producerRecord = new ProducerRecord<String, Transaction>(TOPIC,
				transaction.getTransactionId().toString(), transaction);
		this.kafkaTemplate.send(producerRecord);
		log.info(String.format("Produced user -> %s", transaction));
	}

}