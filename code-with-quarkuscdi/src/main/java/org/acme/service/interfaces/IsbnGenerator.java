package org.acme.service.interfaces;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class IsbnGenerator implements NumberGenerator{
    @Override
    public String generateISBNNumber() {
        return "15-8783434" + Math.abs(new Random().nextInt());
    }
}