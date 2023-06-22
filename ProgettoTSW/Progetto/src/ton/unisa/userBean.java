package ton.unisa;

/*JavaBean per l'utente con i suoi getter e setter*/
public class userBean {
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}



	int id = -1;
	String username = "";
	String surname = "";
	String password="";
	int isadmin=0;
	
	public userBean() {
		id=-1;
		username="";
		surname="";
		password="";
		isadmin=0;
	}
}
	
	