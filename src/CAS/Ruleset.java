package CAS;

import java.awt.Component;

import Ruleset.RuleException;

public class Ruleset {
	public interface RuleSet {
		public void validate(Component ob) throws RuleException;
	}
}
