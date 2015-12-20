package oauth;

import static org.junit.Assert.*;

import org.junit.Test;

import oauth.Credentials;

public class CredentialsTest {
	
	@Test
	public void testConstructor() {
		Credentials credentials = new Credentials("acces_token", "refresh_token");		
		assert(credentials.getAccessToken().equals("acces_token"));
		assert(credentials.getRefrehToken().equals("refresh_token"));
	}

	@Test
	public void testGetAccessToken() {
		Credentials credentials = new Credentials("acces_token", "refresh_token");		
		assert(credentials.getAccessToken().equals("acces_token"));
	}

	@Test
	public void testSetAccessToken() {
		Credentials credentials = new Credentials("", "refresh_token");		
		
		credentials.setAccessToken("access_token");
		
		assert(credentials.getAccessToken().equals("access_token"));
	}

	@Test
	public void testGetRefrehToken() {
		Credentials credentials = new Credentials("acces_token", "refresh_token");		
		assert(credentials.getRefrehToken().equals("refresh_token"));
	}

	@Test
	public void testSetRefrehToken() {
		Credentials credentials = new Credentials("access_token", "");		
		
		credentials.setRefrehToken(("refresh_token"));
		
		assert(credentials.getRefrehToken().equals("refresh_token"));
	}

}
