package agiledon.codekata.refactoring.duplication;

public abstract class AbstractTemplateGenerator {
	
	public String applyTemplate(String sourceTemplate, String reqId, String t){
		String template = new String(sourceTemplate);
		
		int templateSplitBegin = template.indexOf(t);
        int templateSplitEnd = templateSplitBegin + t.length();
        String templatePartOne = new String(template.substring(0, templateSplitBegin));
        String templatePartTwo = new String(template.substring(templateSplitEnd, template.length()));
       
        String code = this.getCode(reqId);
        
        template = templatePartOne + code + templatePartTwo;
        
        return template;
	}
	
	public abstract String getCode(String reqId);
}
