package com.drools.example;

import com.drools.example.config.DroolsBeanFactory;
import com.drools.example.model.Customer;
import com.drools.example.model.Customer.CustomerType;

import java.util.Objects;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class App {
    private static final String FILEPATH = "com/drools/example/rules/Balance_Nudges.xls";
    private KieSession kSession;

    public static void main(String[] args) {
        App app = new App();
        app.setup();
        app.smeCustomerLowBalance();
    }

    public void setup() {
        Resource resource = ResourceFactory.newClassPathResource(FILEPATH, getClass());
        resource.setResourceType(ResourceType.DTABLE);
        DroolsBeanFactory factory = new DroolsBeanFactory();
        kSession = factory.getKieSession(resource);
    }

    public void smeCustomerLowBalance() {
        Customer customer = new Customer(CustomerType.SME,
                3000);
        kSession.insert(customer);
        kSession.fireAllRules();
        System.out.println(!Objects.equals("Nudge text " + null, customer.getNudge()) ? customer.getNudge() : "No Nudge generated");
    }
}
