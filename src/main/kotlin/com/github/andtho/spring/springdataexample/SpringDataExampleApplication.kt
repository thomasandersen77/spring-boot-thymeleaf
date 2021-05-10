package com.github.andtho.spring.springdataexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringDataExampleApplication {
/*	@Bean
	fun sessionFactory(em : EntityManager) : SessionFactory {
		return em.unwrap(SessionFactory::class.java)
	}*/
}

fun main(args: Array<String>) {
	runApplication<SpringDataExampleApplication>(*args)
}
