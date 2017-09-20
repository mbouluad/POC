package fr.helloworldrestmicroservice.dao;

import java.util.List;

import fr.helloworldrestmicroservice.model.HelloWorld;


public interface IHelloWorldDAO {

	/**
	 * @return
	 */
	List<HelloWorld> getHelloWorlds();
	
	/**
	 * @param newHelloWorld
	 */
	void addHelloWorld(HelloWorld newHelloWorld);
}
