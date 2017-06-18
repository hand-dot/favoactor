package com.handdot.favoactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * アプリケーション実行クラス
 * @author hand-dot
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class FavoactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoactorApplication.class, args);
	}
}
