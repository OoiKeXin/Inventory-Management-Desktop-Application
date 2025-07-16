package main;

public class UserInfo {
	 	private String name, id;

	 	public  UserInfo() {
	 		
	    }
	 	
	    public String getName() {
	        return name;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public void enterName() {
	        System.out.println("Please enter first name and surname: ");
	    }

	    public boolean checkName() {
	        return name != null && name.contains(" ");
	    }


	    public String generateUserId(String username) {
	        this.name = username.trim();

	        if (checkName()) {
	            int spaceIndex = name.lastIndexOf(' ');
	            if (spaceIndex != -1 && spaceIndex + 1 < name.length()) {
	                char firstChar = name.charAt(0);
	                String lastNamePart = name.substring(spaceIndex + 1);
	                this.id = firstChar + lastNamePart;
	                return this.id;
	            }
	        }

	        this.id = "guest";
	        return "guest";
	    }

}

