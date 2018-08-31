package io.battlerune.login;

import io.battlerune.Client;
import io.battlerune.login.impl.MainScreen;

/**
 * Handles the rendering of the login screen.
 */
public class LoginRenderer {

	/** The client instance. */
	private final Client client;

	/** The login screen to render. */
	public LoginComponent screen;

	/** Constructs a new <code>LoginRenderer</code>. */
	public LoginRenderer(Client client) {
		this.client = client;
	}

	/** Handles rendering the login screen. */
	public void display() {
		client.mainscreen.render(client);
	}

	/** Handles clicking on the login screen. */
	public void click() {
		if (!client.loginLoaded)
			return;
		client.mainscreen.click(client);
	}


	public boolean getScreen(ScreenType type) {
		return screen.type() == type;
	}
}
