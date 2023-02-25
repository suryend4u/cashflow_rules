package com.drools.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
    public void sme_custsomer_low_balance() throws Exception {
        Customer customer = new Customer(CustomerType.SME, 8000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudge() != null)
            System.out.println("Nudge text : " + customer.getNudge());
        assertNotNull(customer.getNudge());
    }

    @Test
    public void bb_custsomer_low_balance() throws Exception {
        Customer customer = new Customer(CustomerType.BB, 3000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudge() != null)
            System.out.println("Nudge text : " +
                    customer.getNudge());
        assertNotNull(customer.getNudge());
    }

    @Test
    public void sme_custsomer_high_balance() throws Exception {
        Customer customer = new Customer(CustomerType.SME, 250000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudge() != null)
            System.out.println("Nudge text : " +
                    customer.getNudge());
        assertNotNull(customer.getNudge());
    }

    @Test
    public void bb_custsomer_high_balance() throws Exception {
        Customer customer = new Customer(CustomerType.BB, 55000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudge() != null)
            System.out.println("Nudge text : " +
                    customer.getNudge());
        assertNotNull(customer.getNudge());
    }

    @Test
    public void sme_custsomer_normal_balance() throws Exception {
        Customer customer = new Customer(CustomerType.SME, 100000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudge() != null)
            System.out.println("Nudge text : " +
                    customer.getNudge());
        assertNull(customer.getNudge());
    }

    @Test
    public void bb_custsomer_normal_balance() throws Exception {
        Customer customer = new Customer(CustomerType.BB, 35000);
        kSession.insert(customer);
        kSession.fireAllRules();
        if (customer.getNudge() != null)
            System.out.println("Nudge text : " +
                    customer.getNudge());
        assertNull(customer.getNudge());
    }

}
