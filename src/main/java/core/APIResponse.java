package core;

import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Represents a response from the trakt.tv API.
 * 
 * @author Jojo
 *
 */
public class APIResponse {

	private Response response;

	private JsonObject responseObject;

	public APIResponse(Response response) {
		this.response = response;

		String jsonString = response.readEntity(String.class);
		// Parse the returned jsonString to a JsonObject.
		JsonParser jsonParser = new JsonParser();
		this.responseObject = jsonParser.parse(jsonString).getAsJsonObject();
	}

	/**
	 * Gets the status code of the response.
	 * 
	 * @return the response status code.
	 */
	public int getStatus() {
		return response.getStatus();
	}

	/**
	 * Gets an attribute of the response body.
	 * 
	 * @param attributeName
	 *            The name of the attribute to get.
	 * @return The attribute value. If there is no attribute with the given name
	 *         null will be returned.
	 */
	public String getAttribute(String attributeName) {
		return responseObject.get(attributeName).getAsString();
	}

	/**
	 * Gets the value of a specific header.
	 * 
	 * @param headerName
	 *            The name of the header.
	 * @return The value of the header with the given name. If there is no
	 *         header with the given name null will be returned.
	 */
	public String getHeader(String headerName) {
		return response.getHeaderString(headerName);
	}

}
