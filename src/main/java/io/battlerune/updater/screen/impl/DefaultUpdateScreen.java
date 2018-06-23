package io.battlerune.updater.screen.impl;

import io.battlerune.Client;
import io.battlerune.updater.screen.UpdateComponent;

import java.awt.*;

public class DefaultUpdateScreen extends UpdateComponent {

	@Override
	public void setup(Client client) {
		super.setup(client);
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		getGraphics().setFont(new Font("Tahoma", Font.PLAIN, 25));
		setLabel("Checking for updates");
	}

	@Override
	public void render(Client client) {
		drawWaitingLabel(Color.WHITE);
	}

}
