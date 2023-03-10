package com.drools.example;

import com.drools.example.config.DroolsBeanFactory;
import com.drools.example.model.Customer;
import com.drools.example.model.Customer.CustomerType;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class App {
    private KieSession kSession;

    public static void main(String[] args) {
        App app = new App();
        app.setup();
        app.smeCustomerLowBalance();
    }

    public void setup() {
        Resource resource = ResourceFactory.newClassPathResource("Balance_Nudges.xls",
                getClass());
        resource.setResourceType(ResourceType.DTABLE);
        DroolsBeanFactory factory = new DroolsBeanFactory();
        kSession = factory.getKieSession(resource);
    }

    public void smeCustomerLowBalance() {
        Customer customer = new Customer(CustomerType.SME, 3000);
        kSession.insert(customer);
        kSession.fireAllRules();
        customer.getNudges().forEach(System.out::println);
    }
}
