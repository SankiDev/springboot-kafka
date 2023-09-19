package com.sankidev.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sankidev.kafka.avro.Transaction;
import com.sankidev.kafka.utils.Producer;

@RestController
public class ProducerController {

	@Autowired
	private Producer producer;

	@PostMapping("/produce-message")
	public String produceMessage(@RequestBody Transaction transaction) {

		producer.sendMessage(transaction);
		return "Message Produced Successfully";
	}

}
