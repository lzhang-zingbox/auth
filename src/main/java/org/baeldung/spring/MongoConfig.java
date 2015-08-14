package org.baeldung.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration{
	
	@Bean
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		MappingMongoConverter converter = super.mappingMongoConverter();
		converter.setMapKeyDotReplacement("~");
		return converter;
	}
	
	@Override
	protected String getDatabaseName() {
		return "test";
	}
	
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost:27017");
	}
}
