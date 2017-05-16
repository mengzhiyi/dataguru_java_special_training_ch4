package agiledon.codekata.refactoring.duplication;

public class ALTCodeTemplateGenerator extends AbstractTemplateGenerator {

	@Override
	public String getCode(String code) {
		return code.substring(0, 5) + "-" + code.substring(5, 8);
	}

}
