package oauth;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

import core.APIRequest;
import core.APIResponse;
import core.APISettings;

public class AuthenticationManager {

	private APISettings apiSettings;

	public AuthenticationManager(APISettings apiSettings) {
		this.apiSettings = apiSettings;
	}
	
	/**
	 * Creates new {@link Credentials} using the given autherizationCode. The
	 * code should be obtained by the OAuth standard or PIN method.
	 * 
	 * @param autherizationCode
	 *            The code that was obtained through the OAuth process.
	 * @return New {@link Credentials} that can now be used to make API calls.
	 * @throws OAuthExecption
	 *             is thrown if the authentication failed.
	 */
	public Credentials getCredentials(String autherizationCode) throws OAuthExecption {
		String jsonRequest = "{  'code': '" + autherizationCode + "',  'client_id': '" + apiSettings.getClientId()
				+ "',  'client_secret': '" + apiSettings.getClientSecret() + "',  'redirect_uri': '" + apiSettings.getRedirectUri()
				+ "',  'grant_type': 'authorization_code'}";
		// We need to use " instead of '.
		jsonRequest = jsonRequest.replace("'", "\"");

		Entity payload = Entity.json(jsonRequest);
		
		
		APIResponse response = new APIRequest(apiSettings, "/oauth/token", "POST", payload).send();
			

		// Check if the request was successful.
		if (response.getStatus() == 200) {
			// Create dummy credentials.
			Credentials credentials = new Credentials("", "");
			// Fill the dummy credentials with the token from the response.
			updateCredentials(credentials, response);

			return credentials;
		} else {
			// If the request failed throw an OAuthException.
			throw new OAuthExecption();
		}
	}

	/**
	 * Refreshes the given credentials using its refresh token. The new
	 * Credentials will be valid for 3 Months.
	 * 
	 * @param credentials
	 *            The {@link Credentials} to refresh.
	 * @throws OAuthExecption
	 *             Is thrown if the refresh failed. This will be the case if the
	 *             refresh token is older than 3 Months.
	 */
	public void refreshCredentials(Credentials credentials) throws OAuthExecption {
		String jsonRequest = "{  'refresh_token': '" + credentials.getRefrehToken() + "',  'client_id': '" + apiSettings.getClientId()
				+ "',  'client_secret': '" + apiSettings.getClientSecret() + "',  'redirect_uri': '" + apiSettings.getRedirectUri()
				+ "',  'grant_type': 'refresh_token'}";
		// We need to use " instead of '.
		jsonRequest = jsonRequest.replace("'", "\"");
		
		
		Entity payload = Entity.json(jsonRequest);
			
		APIResponse response = new APIRequest(apiSettings, "/oauth/token", "POST", payload).send();

		// Check if the request was successful.
		if (response.getStatus() == 200) {
			updateCredentials(credentials, response);
		} else {
			// If the request failed throw an OAuthException.
			throw new OAuthExecption();
		}
	}

	/**
	 * Changes the values for the access and refresh token of the given
	 * {@link Credentials} to the ones that are present in the respone.
	 * 
	 * @param credentials
	 *            The {@link Credentials} to update.
	 * @param response
	 *            The Response from the trakt API that contains the access and
	 *            refresh token.
	 */
	private void updateCredentials(Credentials credentials, APIResponse response) {
		// Read the access and refresh token from the response.
		String accessToken = response.getAttribute("access_token");
		String refreshToken = response.getAttribute("refresh_token");

		// Update the credentials with the new tokens.
		credentials.setAccessToken(accessToken);
		credentials.setRefrehToken(refreshToken);
	}
}
