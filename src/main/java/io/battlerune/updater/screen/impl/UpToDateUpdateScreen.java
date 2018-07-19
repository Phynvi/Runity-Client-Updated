package io.battlerune.updater.screen.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.concurrent.TimeUnit;

import io.battlerune.Client;
import io.battlerune.Stopwatch;
import io.battlerune.updater.Updater;
import io.battlerune.updater.screen.UpdateComponent;

public class UpToDateUpdateScreen extends UpdateComponent {

	/**
	 * The stopwatch.
	 */
	private final Stopwatch stopwatch = new Stopwatch();

	@Override
	public void setup(Client client) {
		super.setup(client);
		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		getGraphics().setFont(new Font("Tahoma", Font.PLAIN, 25));
		setLabel("Client is all up to date!");
		stopwatch.reset();
	}

	@Override
	public void render(Client client) {
		drawLabel(Color.WHITE);
	}

	public void process() {
		if (stopwatch.hasElapsed(750, TimeUnit.MILLISECONDS)) {
			Updater.get().finish();
		}
	}
}
