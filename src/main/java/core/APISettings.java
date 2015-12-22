package core;

public class APISettings {
	
	
	private String clientId;

	private String clientSecret;

	private String apiRoute;

	private String redirectUri;
	
	private String apiVersion;
	
	
	public APISettings(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = "https://api-v2launch.trakt.tv";
		this.redirectUri = "urn:ietf:wg:oauth:2.0:oob";
		this.apiVersion = "2";
	}

	public APISettings(String clientId, String clientSecret, String apiRoute) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = apiRoute;
		this.redirectUri = "urn:ietf:wg:oauth:2.0:oob";
		this.apiVersion = "2";
	}

	public APISettings(String clientId, String clientSecret, String apiRoute, String redirectUri) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = apiRoute;
		this.redirectUri = redirectUri;
		this.apiVersion = "2";
	}
	
	public APISettings(String clientId, String clientSecret, String apiRoute, String redirectUri, String apiVersion) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.apiRoute = apiRoute;
		this.redirectUri = redirectUri;
		this.apiVersion = apiVersion;
	}

	
	
	

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getApiRoute() {
		return apiRoute;
	}

	public void setApiRoute(String apiRoute) {
		this.apiRoute = apiRoute;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	
	

}
