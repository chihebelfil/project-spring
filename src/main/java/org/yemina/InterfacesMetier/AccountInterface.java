package org.yemina.InterfacesMetier;

import java.util.Collection;

import org.yemina.Entities.Account;


public interface AccountInterface {
	public Collection <Account> getAll();
	public Account getId(String id );
	public void delete(String id);
	public Account add(Account c);
	public Collection <Account> getone(String c);
	public Account Update(Account account);

}
