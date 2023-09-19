package com.sankidev.kafka.utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sankidev.kafka.avro.Transaction;

@Service
public class Consumer {
	Logger log = LoggerFactory.getLogger(Consumer.class);


    @KafkaListener(topics = "#{'${topic.transaction.req}'}", groupId = "group_id")
    public void listen(ConsumerRecord<String, Transaction> record) {
    	log.info("Consumed message" );
        
    	Transaction transaction = record.value();
    	log.info("got record");
    	//log.info(String.format("Consumed message -> %s",transaction ));
		
		log.info("getting record");
		log.info(transaction.getTransactionId().toString());
		log.info(transaction.getSender().toString());
		log.info(transaction.getReceiver().toString());
		log.info(transaction.getAmount().toString());
		log.info("message received ok");
		
      
    }
}