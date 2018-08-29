package io.battlerune.login.impl;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.battlerune.Client;
import io.battlerune.Raster;
import io.battlerune.Sprite;
import io.battlerune.Utility;
import io.battlerune.login.LoginComponent;
import io.battlerune.login.ScreenType;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;

public class CaptchaScreen extends LoginComponent {

	private Captcha captcha;
	private String activity;
	
	@Override
	public void render(Client client) {
		int centerX = getX();
		int centerY = getY();
		refresh(client);
		load(client, 10);

		if (captcha == null) {
			try {
				captcha = new Captcha.Builder(200, 50).addText().addBackground(new GradiatedBackgroundProducer()).addNoise().gimp().addBorder().build();
				ImageIO.write(captcha.getImage(), "png", new File(Utility.findcachedir() + "Sprites/captcha.png"));
				Client.captcha = new Sprite("captcha");
				activity = "Please enter the captcha!";
				System.out.println("Captcha Code: " + captcha.getAnswer());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/* Background */
		Client.spriteCache.get(678).drawTransparentSprite(
				(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
				(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

		/* Box */
		Raster.fillRectangle(225, 185, 325, 100, 0x1F1D19, 150);
		Raster.drawRectangle(225, 185, 325, 100, 0x3d3427);

		client.regularText.drawCenteredText(0xB7B7B7, centerX + 5, activity,
				centerY - 80, true);

		Client.spriteCache.get(882).drawSprite(262, 300, 0);

		Client.captcha.drawSprite(290, 210, 0);

		client.regularText.drawText(true, centerX - 115, 0xFFFFFF, Utility.formatName(client.captchaInput)
				+ ((Client.tick % 40 < 20) ? "|" : ""), centerY + 70);

		/* Login Button */
		if (client.mouseInRegion(320, 350, 454, 393)) {
			Client.spriteCache.get(59).drawSprite(320, 350, 0);
			addTooltip("Submit Captcha");
		} else {
			Client.spriteCache.get(58).drawTransparentSprite(320, 350, client.loginTick);
		}

		/**
		 * Settings
		 */
		announcement(client);
		drawSetting(client);

		/* Drawing */
		Client.loginScreenIP.drawGraphics(client.graphics, 0, 0);
		Raster.reset();
	}

	@Override
	public void click(Client client) {
		
		/* Submit Button */
		if (client.lastMetaModifier == 1 && client.mouseInRegion(320, 350, 454, 393)) {
			if (!Client.loggedIn) {
				if(captcha.isCorrect(client.captchaInput)) {
					captcha = null;
					client.captchaInput = "";
					client.login(client.myUsername, client.myPassword, false);
				} else {
					activity = "Invalid captcha, please try again!";
					
				}
				
			}
		}

		settingButton(client);
		
		handleCaptchaWritting(client);
	}

	/**
	 * Handles writing in the client.
	 */
	private void handleCaptchaWritting(Client client) {
		do {
			int line = client.readChar(-796);
			if (line == -1)
				break;
			boolean flag = false;
			for (int index = 0; index < Client.validUserPassChars.length(); index++) {
				if (line != Client.validUserPassChars.charAt(index))
					continue;
				flag = true;
				break;
			}
			if (line == 8 && client.captchaInput.length() > 0)
				client.captchaInput = client.captchaInput.substring(0, client.captchaInput.length() - 1);

			if (flag) {
				client.captchaInput += (char) line;
			}

		} while (true);
		return;
	}
	
	@Override
	public ScreenType type() {
		// TODO Auto-generated method stub
		return null;
	}

}
