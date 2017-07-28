package Ruleset;




public class LoginRuleset{
	
	public boolean nameRule(String Name){
		if (Name.equalsIgnoreCase("CAHOME")){
			return true;
		}
		return false;
	}
	
	public boolean passwordRule(String password){
	if (password.equals("Passw0rd")){
		return true;
	}
	return false;
	}


}
