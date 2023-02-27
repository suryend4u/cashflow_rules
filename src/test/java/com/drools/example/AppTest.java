package com.drools.example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import com.drools.example.config.DroolsBeanFactory;
import com.drools.example.model.Customer;
import com.drools.example.model.Customer.CustomerType;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private KieSession kSession;

    @Before
    public void setup() {
        Resource resource =
                ResourceFactory.newClassPathResource("Balance_Nudges.xls",
                        getClass());
        resource.setResourceType(ResourceType.DTABLE);
        kSession = new DroolsBeanFactory().getKieSession(resource);
    }

    @Test
    public void smeCustomerLowBalance() {
        Customer customer = new Customer(CustomerType.SME, 3000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudges() != null) {
            System.out.println("Nudge texts for smeCustomerLowBalance: ");
            customer.getNudges().forEach(System.out::println);
        }
        assertEquals(2,customer.getNudges().size());
    }

    @Test
    public void bbCustomerLowBalance() {
        Customer customer = new Customer(CustomerType.BB, 3000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudges() != null) {
            System.out.println("Nudge texts for bbCustomerLowBalance: ");
            customer.getNudges().forEach(System.out::println);
        }
        assertEquals(1,customer.getNudges().size());
    }

    @Test
    public void smeCustomerHighBalance() {
        Customer customer = new Customer(CustomerType.SME, 250000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudges() != null) {
            System.out.println("Nudge texts for smeCustomerHighBalance: ");
            customer.getNudges().forEach(System.out::println);
        }
        assertEquals(1,customer.getNudges().size());
    }

    @Test
    public void bbCustomerHighBalance() {
        Customer customer = new Customer(CustomerType.BB, 55000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudges() != null) {
            System.out.println("Nudge texts for bbCustomerHighBalance: ");
            customer.getNudges().forEach(System.out::println);
        }
        assertEquals(1,customer.getNudges().size());
    }

    @Test
    public void smeCustomerNormalBalance() {
        Customer customer = new Customer(CustomerType.SME, 100000);
        kSession.insert(customer);
        kSession.fireAllRules();
        assertEquals(0,customer.getNudges().size());
    }

    @Test
    public void bbCustomerNormalBalance() {
        Customer customer = new Customer(CustomerType.BB, 35000);
        kSession.insert(customer);
        kSession.fireAllRules();
        assertEquals(0,customer.getNudges().size());
    }

}
