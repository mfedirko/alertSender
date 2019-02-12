package com.example.service.email.util;

import model.TemplateConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public class TemplateHelper {
    public TemplateHelper(ITemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    private  ITemplateEngine templateEngine;

    public Map<TemplateConstant, String> getTemplateLocations() {
        return templateLocations;
    }

    private Map<TemplateConstant, String> templateLocations = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(TemplateHelper.class);


    public void setTemplateLocations(Map<TemplateConstant,String> locations){
        templateLocations = locations;
        if (templateLocations.keySet().size() < TemplateConstant.values().length){
            logger.warn("Some template locations are not mapped");
        }
    }


    public String createBodyFromTemplate(TemplateConstant templateConstant,Map<String,Object> params){
        Context context = new Context();
        context.setVariables(params);
        String location = templateLocations.get(templateConstant);
        return templateEngine.process(location,context);
    }
}
