package com.racproject.model;

import java.net.UnknownHostException;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.racproject.config.WebConfig;

public class QueryApp {

	public static MongoOperations getMongoOperation(String args[]) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(WebConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		return mongoOperation;
	}
}
