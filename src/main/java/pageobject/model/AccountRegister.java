package pageobject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRegister {

	public String lastName;
	public String firstName;
	public String email;
	public String password;
}
