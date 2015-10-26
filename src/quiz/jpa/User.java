package quiz.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Account")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column (name="isAdmin")
	private byte admin;
	
	public int getID()
	{
		return id;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public boolean getAdmin() {
		if (this.admin == 0) {
			return false;
		}
		return true;
	}
	
	public void setAdmin(boolean bAdmin) {
		if (bAdmin) {
			this.admin = 1;
		}
		else {
			this.admin = 0;
		}
	}
}