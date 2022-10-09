package app;

import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class View{
    public static String render(Request req, Map<String, Object> model, String templatePath){
        model.put("WebPath", Path.Web.class);
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
