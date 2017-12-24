package org.packt.swarm.petstore;

import org.jboss.shrinkwrap.api.Archive;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.keycloak.Secured;

public class Main {

    public static void main(String[] args) throws Exception {



        Swarm swarm = new Swarm();

        Archive<?> deployment = swarm.createDefaultDeployment();

        swarm.start();

        deployment.as(Secured.class);

        swarm.deploy(deployment);
    }
}