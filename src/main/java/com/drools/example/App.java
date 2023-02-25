package com.drools.example;

import com.drools.example.config.DroolsBeanFactory;
import com.drools.example.model.Customer;
import com.drools.example.model.Customer.CustomerType;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class App {
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.setup();
        app.sme_custsomer_low_balance();
    }

    private KieSession kSession;

    public void setup() {
        Resource resource = ResourceFactory.newClassPathResource("com/drools/example/rules/ Balance_Nudges.xls",
                getClass());
        resource.setResourceType(ResourceType.DTABLE);
        DroolsBeanFactory factory = new DroolsBeanFactory();
        String drl = factory.getDrlFromExcel("com/drools/example/rules/Balance_Nudges.xls");
        System.out.println(drl);
        kSession = factory.getKieSession(resource);
    }

    public void sme_custsomer_low_balance() throws Exception {
        Customer customer = new Customer(CustomerType.SME,
                3000);
        kSession.insert(customer);
        kSession.fireAllRules();
        System.out.println("Nudge text " + null != customer.getNudge() ? customer.getNudge() : "No Nudge generated");
    }
}