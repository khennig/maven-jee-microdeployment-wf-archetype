package de.erik.jee;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class WhateverIT {

	@ArquillianResource
	private URL deploymentUrl;

	@Inject
	Whatever whatever;

	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml")
				.resolve("org.assertj:assertj-core").withTransitivity().asFile();
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Whatever.class.getPackage())
				.addAsLibraries(dependencies)
				.addAsResource("persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void addition() {
		// setup
		// test, assert
		assertThat(whatever.helloWorld()).isEqualTo("Hello World!");
	}
}