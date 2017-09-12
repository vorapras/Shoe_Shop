package M;

public class CustomerDB // In 1Row
{
	public int id;
	public String name;
	public String surname;
	public String phone;

	public CustomerDB() {
	}

	public CustomerDB(int xid, String xname, String xsurname, String xphone) {
		id = xid;
		name = xname;
		surname = xsurname;
		phone = xphone;
	}
}
