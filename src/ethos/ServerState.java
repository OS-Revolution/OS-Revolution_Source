package ethos;

public enum ServerState {

	PUBLIC_PRIMARY(43596), PUBLIC_SECONDARY(43596), PRIVATE(43596);

	private int port;

	ServerState(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

}
