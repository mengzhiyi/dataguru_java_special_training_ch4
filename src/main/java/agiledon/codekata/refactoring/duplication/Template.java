package agiledon.codekata.refactoring.duplication;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class Template {

	public final Map<String, Class> templates = new HashMap<String, Class>() {{  
        put("%CODE%", CodeTemplateGenerator.class);  
        put("%ALTCODE%", ALTCodeTemplateGenerator.class);  
    }};
	
    public void applyTemplate(String sourceTemplate, String reqId, PrintStream out) {
    	
        try {
            String template = new String(sourceTemplate);

            for(Map.Entry<String, Class> entry :templates.entrySet()){
            	AbstractTemplateGenerator tempalteGenerator 
            		= (AbstractTemplateGenerator) entry.getValue().newInstance();
            	template = tempalteGenerator.applyTemplate(template, reqId, entry.getKey());
            }
                        
            out.print(template);
        } catch (Exception e) {
            System.out.println("Error in substitute()");
        }
    }
}

