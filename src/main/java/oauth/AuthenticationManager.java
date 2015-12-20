package oauth;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

public class AuthenticationManager {

	private String clientId;

	private String clientSecret;

	private String apiRoute;

	private String redirectUri;

	public AuthenticationManager(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = "https://api-v2launch.trakt.tv";
		this.redirectUri = "urn:ietf:wg:oauth:2.0:oob";
	}

	public AuthenticationManager(String clientId, String clientSecret, String apiRoute) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = apiRoute;
		this.redirectUri = "urn:ietf:wg:oauth:2.0:oob";
	}

	public AuthenticationManager(String clientId, String clientSecret, String apiRoute, String redirectUri) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = apiRoute;
		this.redirectUri = redirectUri;
	}

	public Credentials getCredentials(String autherizationCode) throws OAuthExecption {
		Client client = ClientBuilder.newClient();

		String jsonRequest = "{  'code': '" + autherizationCode + "',  'client_id': '" + clientId
				+ "',  'client_secret': '" + clientSecret + "',  'redirect_uri': '" + redirectUri
				+ "',  'grant_type': 'authorization_code'}";
		// We need to use " instead of '.
		jsonRequest = jsonRequest.replace("'", "\"");

		Entity payload = Entity.json(jsonRequest);
		Response response = client.target(apiRoute + "/oauth/token").request(MediaType.APPLICATION_JSON_TYPE)
				.post(payload);

		// Check if the request was successful.
		if (response.getStatus() == 200) {
			String jsonString = response.readEntity(String.class);
			// Parse the returned jsonString to a JsonObject.
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonResponseObject = jsonParser.parse(jsonString).getAsJsonObject();

			// Read the access and refresh token from the response.
			String accessToken = jsonResponseObject.get("access_token").getAsString();
			String refreshToken = jsonResponseObject.get("refresh_token").getAsString();

			Credentials credentials = new Credentials(accessToken, refreshToken);

			return credentials;
		} else {
			// If the request failed throw an OAuthException.
			throw new OAuthExecption();
		}
	}
}
