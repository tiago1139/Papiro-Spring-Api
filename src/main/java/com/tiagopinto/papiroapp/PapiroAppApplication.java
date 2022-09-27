package com.tiagopinto.papiroapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PapiroAppApplication implements CommandLineRunner {

	@Value("${origin}")
	private String origin;

	private static String origin_static;

	@Value("${images}")
	private String images;

	private static String images_static;

	private static Logger LOG = LoggerFactory
      .getLogger(PapiroAppApplication.class);

	public static void main(String[] args) {
		
		LOG.info(origin_static);
		LOG.info(images_static);
		SpringApplication.run(PapiroAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info(origin);
		LOG.info(images);
		
	}

	@Value("${origin}")
    public void setOriginStatic(String origin){
        PapiroAppApplication.origin_static = origin;
    }

	@Value("${images}")
    public void setImagesStatic(String images){
        PapiroAppApplication.images_static = images;
    }



}
