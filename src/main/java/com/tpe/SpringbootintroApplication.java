package com.tpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//icerisinde otomatikmen companentscan var component claslari tpe altinda olmali.
// Yoksa componentscan o clasi taramaz
public class SpringbootintroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootintroApplication.class, args);
	}
//tomcat server gomulu oldugu icin baska birseye gerek yok
	//annotationlar arkada corejava islemi yapip jvmye gonderir.
}
//run editconfiguationsda  more show ad vm option secilir istenilen proporties secilir.
//program calisirken belirttigimz ile baslar sonra onda olmayanlari diger propertiesden alir.