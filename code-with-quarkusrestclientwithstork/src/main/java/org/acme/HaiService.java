package org.acme;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HaiService {

    public String sayHai() {
        return "Hai";
    }
}
