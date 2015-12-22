package core;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.*;

import oauth.Credentials;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

/**
 * Represents a request to the trakt.tv API. If it is send it will return a
 * {@link APIResponse} object that contains the response.
 * 
 * @author Jojo
 *
 */
public class APIRequest {

	private Client client;

	private APISettings apiSettings;

	private String route;

	private Credentials credentials;

	private String httpMethod;

	private Entity payload;

	/**
	 * Creates a new APIRequest with the given parameters.
	 * 
	 * @param apiSettings
	 *            The {@link APISettings} that the request will use to make the
	 *            call.
	 * @param route
	 *            The route the request should be send to. This should not
	 *            contain the base URL of the API. For example '/movies/{id}'
	 *            instead of 'https://api-v2launch.trakt.tv/movies/{id}'.
	 * @param httpMethod
	 *            The HTTP method that will be used to make the request. Valid
	 *            values are 'GET', 'POST', 'DELETE' and 'PUT'. If PUT or POST
	 *            are used a payload needs to be passed as well.
	 */
	public APIRequest(APISettings apiSettings, String route, String httpMethod) {
		this.apiSettings = apiSettings;
		this.route = route;
		this.httpMethod = httpMethod;

		this.client = ClientBuilder.newClient();
	}

	/**
	 * Creates a new APIRequest with the given parameters.
	 * 
	 * @param apiSettings
	 *            The {@link APISettings} that the request will use to make the
	 *            call.
	 * @param route
	 *            The route the request should be send to. This should not
	 *            contain the base URL of the API. For example '/movies/{id}'
	 *            instead of 'https://api-v2launch.trakt.tv/movies/{id}'.
	 * @param httpMethod
	 *            The HTTP method that will be used to make the request. Valid
	 *            values are 'GET', 'POST', 'DELETE' and 'PUT'. If PUT or POST
	 *            are used a payload needs to be passed as well.
	 * @param credentials
	 *            The {@link Credentials} that will be used to authorize the
	 *            request.
	 */
	public APIRequest(APISettings apiSettings, String route, String httpMethod, Credentials credentials) {
		this.apiSettings = apiSettings;
		this.route = route;
		this.httpMethod = httpMethod;
		this.credentials = credentials;

		this.client = ClientBuilder.newClient();
	}

	/**
	 * Creates a new APIRequest with the given parameters.
	 * 
	 * @param apiSettings
	 *            The {@link APISettings} that the request will use to make the
	 *            call.
	 * @param route
	 *            The route the request should be send to. This should not
	 *            contain the base URL of the API. For example '/movies/{id}'
	 *            instead of 'https://api-v2launch.trakt.tv/movies/{id}'.
	 * @param httpMethod
	 *            The HTTP method that will be used to make the request. Valid
	 *            values are 'GET', 'POST', 'DELETE' and 'PUT'. If PUT or POST
	 *            are used a payload needs to be passed as well.
	 * @param payload
	 *            The body for the request.
	 */
	public APIRequest(APISettings apiSettings, String route, String httpMethod, Entity payload) {
		this.apiSettings = apiSettings;
		this.route = route;
		this.httpMethod = httpMethod;
		this.payload = payload;

		this.client = ClientBuilder.newClient();
	}

	/**
	 * Creates a new APIRequest with the given parameters.
	 * 
	 * @param apiSettings
	 *            The {@link APISettings} that the request will use to make the
	 *            call.
	 * @param route
	 *            The route the request should be send to. This should not
	 *            contain the base URL of the API. For example '/movies/{id}'
	 *            instead of 'https://api-v2launch.trakt.tv/movies/{id}'.
	 * @param httpMethod
	 *            The HTTP method that will be used to make the request. Valid
	 *            values are 'GET', 'POST', 'DELETE' and 'PUT'. If PUT or POST
	 *            are used a payload needs to be passed as well.
	 * @param payload
	 *            The body for the request.
	 * @param credentials
	 *            The {@link Credentials} that will be used to authorize the
	 *            request.
	 */
	public APIRequest(APISettings apiSettings, String route, String httpMethod, Entity payload,
			Credentials credentials) {
		this.apiSettings = apiSettings;
		this.route = route;
		this.httpMethod = httpMethod;
		this.payload = payload;
		this.credentials = credentials;

		this.client = ClientBuilder.newClient();
	}

	/**
	 * Sends the request to the trak.tv API. If credentials were passed in the
	 * constructor they will be used to authorize the request.
	 * 
	 * @return A {@link APIResponse} that contains the response from the API.
	 */
	public APIResponse send() {
		// Build the request URL using the API route + the route for this
		// specific request.
		String requestURL = apiSettings.getApiRoute() + route;

		Builder request = client.target(requestURL).request(MediaType.APPLICATION_JSON_TYPE)
				.header("trakt-api-version", apiSettings.getApiVersion())
				.header("trakt-api-key", apiSettings.getClientId());

		// If there were credentials passed use them to authorize this request.
		if (credentials != null) {
			request.header("Authorization", "Bearer " + credentials.getAccessToken());
		}

		Response response = this.invokeMethod(request);

		APIResponse apiResponse = new APIResponse(response);

		return apiResponse;
	}

	/**
	 * Invoke the given {@link Builder} request using this requests method. The
	 * Builder has to be build completeley as this method just invokes a certain
	 * httpMethod.
	 * 
	 * @param request
	 *            The Builder request to invoke.
	 * @return The response that was send by the server.
	 */
	private Response invokeMethod(Builder request) {
		if (httpMethod.equals("GET")) {
			return request.get();
		} else if (httpMethod.equals("POST")) {
			return request.post(payload);
		} else if (httpMethod.equals("DELETE")) {
			return request.delete();
		} else if (httpMethod.equals("PUT")) {
			return request.put(payload);
		} else {
			throw new IllegalArgumentException("The http request method " + httpMethod + " is not supported.");
		}
	}

}
