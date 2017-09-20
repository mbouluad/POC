package fr.helloworldrestmicroservice.event;

import fr.helloworldrestmicroservice.model.HelloWorld;


public interface IHelloWorldEventProducer {

	void sendMessage(HelloWorld newHelloWorld);
}
