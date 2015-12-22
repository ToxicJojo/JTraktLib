package oauth;

/**
 * Represents the credentials needed to access the trakt API.
 * Credentials are valid for 3 months and should be refreshed prior to that.
 * 
 * @author Jojo
 *
 */
public class Credentials {

	private String accessToken;

	private String refreshToken;

	/**
	 * Creates a new entity of Credentials.
	 * 
	 * @param access_token
	 *            The access token that will be used.
	 * @param refresh_token
	 *            The refresh token that will be used.
	 */
	public Credentials(String accessToken, String refreshToken) {
		this.setAccessToken(accessToken);
		this.setRefrehToken(refreshToken);
	}

	/**
	 * Get the access token.
	 * 
	 * @return A String that contains the access token.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the access token. The token may not be null.
	 * 
	 * @param accessToken
	 *            The String that will be the new access token.
	 */
	public void setAccessToken(String accessToken) {
		if (accessToken == null) {
			throw new IllegalArgumentException("Tried to set the acces token to null.");
		}
		this.accessToken = accessToken;
	}

	/**
	 * Returns the refresh token.
	 * 
	 * @return A String that contains the refresh token.
	 */
	public String getRefrehToken() {
		return refreshToken;
	}

	/**
	 * Sets the refresh token. The token may not be null.
	 * 
	 * @param refreshToken
	 *            The String that will be the new refresh token.
	 */
	public void setRefrehToken(String refreshToken) {
		if (refreshToken == null) {
			throw new IllegalArgumentException("Tried to set the refresh token to null.");
		}
		this.refreshToken = refreshToken;
	}

}
