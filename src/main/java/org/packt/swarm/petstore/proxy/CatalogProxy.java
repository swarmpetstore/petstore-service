package org.packt.swarm.petstore.proxy;

import org.packt.swarm.petstore.model.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.NamingException;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class CatalogProxy extends ServiceProxy{


    public CatalogProxy() throws NamingException {
        super("catalog-service");
    }

    public List<Item> getAllItems(){
        return Arrays.asList(invoke("item").get(Item[].class));
    }

    public Item getItem(String name){
        return invoke("item/"+name).get(Item.class);
    }

}