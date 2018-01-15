package org.packt.swarm.petstore;

import org.jboss.shrinkwrap.api.Archive;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.keycloak.Secured;

public class Main {

    public static void main(String[] args) throws Exception {



        Swarm swarm = new Swarm();
        swarm.start();

        Archive<?> deployment = swarm.createDefaultDeployment();

        deployment.as(Secured.class)
                .protect( "/pet" )
                .withMethod( "GET" )
                .withRole( "pies" );

        swarm.deploy(deployment);
    }
}