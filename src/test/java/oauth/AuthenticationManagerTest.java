package oauth;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;

import oauth.AuthenticationManager;
import oauth.Credentials;
import oauth.OAuthExecption;

public class AuthenticationManagerTest {
	
	private final String clientId = "8dc64bb81e7f4d07041ca7aec97bc30e2e6d78f5d7b4bee1562cc06035cf2a4d";
	private final String clientSecret = "371db23818ee141acb0d008899b08c18a47668afbaed0199b79c8b69ca25b1ff";

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testGetCredentials() {
		AuthenticationManager authManager = new AuthenticationManager(clientId, clientSecret, "http://api.staging.trakt.tv");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter an auth code.");
			String authCode = br.readLine();
			
			Credentials cred = authManager.getCredentials(authCode);
			
			assert(cred.getAccessToken() != null);
			assert(cred.getRefrehToken() != null);
		} catch (OAuthExecption e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
