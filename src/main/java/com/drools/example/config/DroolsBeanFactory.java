package com.drools.example.config;


import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
/*import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.KnowledgeBuilderFactory;*/


public class DroolsBeanFactory {

    public KieSession getKieSession(Resource dt) {
/*        DecisionTableConfiguration configuration =
                KnowledgeBuilderFactory.newDecisionTableConfiguration();
        DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();
        String drl = decisionTableProvider.loadFromResource(dt, configuration);
        System.out.println("*********Drools file start************");
        System.out.println(drl);
        System.out.println("*********Drools file end************");*/
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem()
                .write(dt);
        kieServices.newKieBuilder(kieFileSystem)
                .buildAll();
        KieRepository kieRepository = kieServices.getRepository();
        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
        return kieContainer.newKieSession();
    }

}
