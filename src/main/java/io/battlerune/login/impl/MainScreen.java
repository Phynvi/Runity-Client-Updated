package io.battlerune.login.impl;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;

import io.battlerune.AccountData;
import io.battlerune.AccountManager;
import io.battlerune.Client;
import io.battlerune.Configuration;
import io.battlerune.Connection;
import io.battlerune.Raster;
import io.battlerune.Settings;
import io.battlerune.Sprite;
import io.battlerune.StringUtils;
import io.battlerune.Utility;
import io.battlerune.login.LoginComponent;
import io.battlerune.login.ScreenType;
import nl.captcha.Captcha;

/**
 * Handles the main screen of login.
 *
 * @author Daniel
 */
public class MainScreen extends LoginComponent {

	public enum Loginstate {
		LOGINSCREEN, CAPTCHA, MESSAGESCREEN, ACCOUNTSCREEN, AVATARSCREEN, SETTINGSSCREEN
	};

	public Loginstate loginstate = Loginstate.LOGINSCREEN;

	public void setLoginstate(Loginstate state) {// ik why coz u have a crap fking login screen
		this.loginstate = (state);
	}

	private static final int EMAIL_CHARACTER_LIMIT = 28;

	@Override
	public void render(Client client) {
		/*if (captcha == null) {
			try {
				captcha = new Captcha.Builder(250, 50).addText().addBackground(new GradiatedBackgroundProducer()).addNoise().gimp().addBorder().build();
				ImageIO.write(captcha.getImage(), "png", new File(Utility.findcachedir() + "Sprites/captcha.png"));
				Client.captcha = new Sprite("captcha");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("CODE: " + captcha.getAnswer());
		}*/

		int centerX = getX();
		int centerY = getY();
		refresh(client);
		load(client, 10);
		/* Announcement */
		announcement(client);

		/* Bubble */
		drawSetting(client);
		if (loginstate.equals(Loginstate.LOGINSCREEN)) {
			/* Message Check */
			if (client.loginMessage1.length() > 0 || client.loginMessage2.length() > 0) {
				setLoginstate(Loginstate.MESSAGESCREEN);
			}

			/* Background */
			Client.spriteCache.get(57).drawTransparentSprite(
					(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
					(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

			/* Login Button */
			if (client.mouseInRegion(318, 285, 452, 328)) {
				Client.spriteCache.get(59).drawSprite(318, 285, 0);
				addTooltip("Log into Runity");
			} else {
				Client.spriteCache.get(58).drawTransparentSprite(318, 285, client.loginTick);
			}

			client.newFancyFont.drawCenteredString("Remember me", 315, 268);
			/* Remember Me Button */
			Client.spriteCache.get(Settings.REMEMBER_ME ? 881 : 880).drawTransparentSprite(244, 251, client.loginTick);
			if (client.mouseInRegion(244, 254, 261, 270)) {
				addTooltip("Toggle remember account details");
			}

			/* Information */
			if (client.mouseInRegion(275, 168, 524, 197)) {
				Client.spriteCache.get(882).drawSprite(274, 169, 0);
				addTooltip("Enter your username");
			}
			if (client.mouseInRegion(275, 215, 524, 239)) {
				Client.spriteCache.get(882).drawSprite(274, 215, 0);
				addTooltip("Enter your password");
			}

			client.regularText.drawText(true, centerX - 101, 0xFFFFFF, Utility.formatName(client.myUsername)
					+ ((client.loginScreenCursorPos == 0) & (Client.tick % 40 < 20) ? "|" : ""), centerY - 60);
			client.regularText.drawText(true, centerX - 101, 0xFFFFFF, StringUtils.toAsterisks(client.myPassword)
					+ ((client.loginScreenCursorPos == 1) & (Client.tick % 40 < 20) ? "|" : ""), centerY - 13);

			/* World Button */
			if (client.mouseInRegion(671, 441, 746, 476)) {
				Client.spriteCache.get(61).drawSprite(670, 440, 0);
//	            addTooltip("Toggle world");
			} else {
				Client.spriteCache.get(60).drawSprite(670, 440, 0);
			}

			client.regularText.drawCenteredText(0x4FB533, 722, Configuration.CONNECTION.name, 466, false);

			/* Account */
			drawAccount(client);

			/* Other */
			if (Configuration.DEBUG_MODE) {
				/*
				 * client.smallFont.drawCenteredText(0xFFFFFF, centerX + 300, "MouseX: " +
				 * (client.mouseX) + " Mouse Y: " + (client.mouseY), centerY - 225, true);
				 */
			}

		/*} else if (loginstate.equals(Loginstate.CAPTCHA)) {

			/* Background */
		/*	Client.spriteCache.get(678).drawTransparentSprite(
					(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
					(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

			/* Box */
		/*	Raster.fillRectangle(225, 185, 325, 100, 0x1F1D19, 150);
			Raster.drawRectangle(225, 185, 325, 100, 0x3d3427);

			client.regularText.drawCenteredText(0xB7B7B7, centerX + 5, info, centerY - 80, true);

			Client.spriteCache.get(882).drawSprite(262, 300, 0);

			Client.captcha.drawSprite(290, 210, 0);

			client.regularText.drawText(true, centerX - 115, 0xFFFFFF, Utility.formatName(client.captchaInput)
					+ ((client.loginScreenCursorPos == 0) & (Client.tick % 40 < 20) ? "|" : ""), centerY + 70);

			/* Login Button */
			/*if (client.mouseInRegion(320, 350, 454, 393)) {
				Client.spriteCache.get(59).drawSprite(320, 350, 0);
				addTooltip("Submit Captcha");
			} else {
				Client.spriteCache.get(58).drawTransparentSprite(320, 350, client.loginTick);
			}*/

		} else if (loginstate.equals(Loginstate.ACCOUNTSCREEN)) {

			AccountData account = client.lastAccount;
			int moveY = 20;

			Sprite avatar = Client.spriteCache.get(account.avatar);

			/* Background */
			Client.spriteCache.get(678).drawTransparentSprite(
					(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
					(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

			/* Box */
			Raster.fillRectangle(175, 175 + moveY, 425, 150, 0x120f0a, 150);
			Raster.drawRectangle(175, 175 + moveY, 425, 150, 0x3d3427);

			/* Avatar */
			if (client.mouseInRegion(centerX - 181, centerY - 32, centerX - 84, centerY + 66)) {
				Raster.fillRectangle(200, 203 + moveY, 80, 80, 0x1F1D19, 85);
				avatar.drawTransparentSprite(212, 233, 85);
//				addTooltip("Select avatar");
			} else {
				Raster.fillRectangle(200, 203 + moveY, 80, 80, 0x1F1D19, 150);
				avatar.drawTransparentSprite(212, 233, 255);
			}
			Raster.drawRectangle(200, 203 + moveY, 80, 80, 0x3d3427);

			/* Messages */
			client.boldText.drawCenteredText(0xff9040, centerX + 5, "Runity", centerY - 105, true);
			client.regularText.drawCenteredText(0xB7B7B7, centerX + 5, "Account Manager", centerY - 85, true);
			client.newBoldFont.drawCenteredString(account.username == null ? "" : Utility.formatName(account.username),
					385, 210 + moveY, 0xff9040, 0);
			client.newBoldFont.drawBasicString("Created:", 320, 235 + moveY, 0xff9040, 0);
			client.newBoldFont.drawBasicString(account.created, 385, 235 + moveY, 0xffffff, 0);
			client.newBoldFont.drawBasicString("Rank:", 320, 265 + moveY, 0xff9040, 0);
			int rank = (account.rank - 1);
			client.newBoldFont.drawBasicString("<img=" + rank + "> " + Utility.getRank(rank), 385, 265 + moveY,
					0xffffff, 0);
			client.newBoldFont.drawBasicString("Uses:", 320, 295 + moveY, 0xff9040, 0);
			client.newBoldFont.drawBasicString(account.uses + "", 385, 295 + moveY, 0xffffff, 0);
			client.smallFont.drawCenteredText(0xFFFFFF, centerX + 5, "Click on your avatar to open the avatar menu",
					centerY + 88, true);
			client.boldText.drawCenteredText(0xFFFFFF, centerX + 5, "[ Click anywhere to return to the main screen ]",
					centerY + 150, true);

			/* Other */
			if (!Configuration.DEBUG_MODE) {
				/*
				 * client.smallFont.drawCenteredText(0xFFFFFF, centerX + 300, "MouseX: " +
				 * (client.mouseX - (centerX)) + " Mouse Y: " + (client.mouseY - (centerY)),
				 * centerY - 225, true);
				 */
			}
		} else if (loginstate.equals(Loginstate.MESSAGESCREEN)) {

			/* Background */
			Client.spriteCache.get(678).drawTransparentSprite(
					(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
					(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

			/* Box */
			Raster.fillRectangle(175, 215, 425, 100, 0x1F1D19, 150);
			Raster.drawRectangle(175, 215, 425, 100, 0x3d3427);

			/* Messages */
			client.boldText.drawCenteredText(0xff9040, centerX + 5, "Runity", centerY - 115, true);
			client.regularText.drawCenteredText(0xB7B7B7, centerX + 5, "Error Message", centerY - 95, true);
			if (client.loginMessage2.length() == 0) {
				client.regularText.drawCenteredText(0xE56161, centerX + 5, client.loginMessage1, centerY + 20, true);
			} else {
				client.boldText.drawCenteredText(0xE56161, centerX + 5, client.loginMessage1, centerY + 15, true);
				client.boldText.drawCenteredText(0xE56161, centerX + 5, client.loginMessage2, centerY + 35, true);
			}
			client.boldText.drawCenteredText(0xFFFFFF, centerX + 5, "[ Click anywhere to return to the main screen ]",
					centerY + 150, true);

		} else if (loginstate.equals(Loginstate.AVATARSCREEN)) {

			int offset = 29;
			int frameColor = 0x3d3427;
			int frameHoverColor = 0x1F1D19;
			int avatarHoverAlpha = 70;

			/* Background */
			Client.spriteCache.get(678).drawTransparentSprite(
					(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
					(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

			// Darth Vader
			if (client.mouseInRegion(centerX - 215, centerY - 136, centerX - 115, centerY - 62)) {
				Client.spriteCache.get(534).drawARGBSprite(165 + offset, 115, avatarHoverAlpha);
				Raster.drawRectangle(165 + offset, 115, Client.spriteCache.get(534).width,
						Client.spriteCache.get(534).height, frameHoverColor);
			} else {
				Client.spriteCache.get(534).drawARGBSprite(165 + offset, 115);
				Raster.drawRectangle(165 + offset, 115, Client.spriteCache.get(534).width,
						Client.spriteCache.get(534).height, frameColor);
			}
			// Skeleton
			if (client.mouseInRegion(centerX - 91, centerY - 136, centerX - 14, centerY - 62)) {
				Client.spriteCache.get(535).drawARGBSprite(265 + offset, 115, avatarHoverAlpha);
				Raster.drawRectangle(265 + offset, 115, Client.spriteCache.get(535).width,
						Client.spriteCache.get(535).height, frameHoverColor);
			} else {
				Client.spriteCache.get(535).drawARGBSprite(265 + offset, 115);
				Raster.drawRectangle(265 + offset, 115, Client.spriteCache.get(535).width,
						Client.spriteCache.get(535).height, frameColor);
			}
			// Cool dude
			if (client.mouseInRegion(centerX + 9, centerY - 136, centerX + 85, centerY - 62)) {
				Client.spriteCache.get(536).drawARGBSprite(365 + offset, 115, avatarHoverAlpha);
				Raster.drawRectangle(365 + offset, 115, Client.spriteCache.get(536).width,
						Client.spriteCache.get(536).height, frameHoverColor);
			} else {
				Client.spriteCache.get(536).drawARGBSprite(365 + offset, 115);
				Raster.drawRectangle(365 + offset, 115, Client.spriteCache.get(536).width,
						Client.spriteCache.get(536).height, frameColor);
			}
			// Gas mask
			if (client.mouseInRegion(centerX + 112, centerY - 136, centerX + 185, centerY - 62)) {
				Client.spriteCache.get(537).drawARGBSprite(465 + offset, 115, avatarHoverAlpha);
				Raster.drawRectangle(465 + offset, 115, Client.spriteCache.get(537).width,
						Client.spriteCache.get(537).height, frameHoverColor);
			} else {
				Client.spriteCache.get(537).drawARGBSprite(465 + offset, 115);
				Raster.drawRectangle(465 + offset, 115, Client.spriteCache.get(537).width,
						Client.spriteCache.get(537).height, frameColor);
			}
			// KFC
			if (client.mouseInRegion(centerX - 188, centerY - 46, centerX - 114, centerY + 30)) {
				Client.spriteCache.get(538).drawARGBSprite(165 + offset, 205, avatarHoverAlpha);
				Raster.drawRectangle(165 + offset, 205, Client.spriteCache.get(538).width,
						Client.spriteCache.get(538).height, frameHoverColor);
			} else {
				Client.spriteCache.get(538).drawARGBSprite(165 + offset, 205);
				Raster.drawRectangle(165 + offset, 205, Client.spriteCache.get(538).width,
						Client.spriteCache.get(538).height, frameColor);
			}
			// Dog
			if (client.mouseInRegion(centerX - 91, centerY - 46, centerX - 14, centerY + 30)) {
				Client.spriteCache.get(539).drawARGBSprite(265 + offset, 205, avatarHoverAlpha);
				Raster.drawRectangle(265 + offset, 205, Client.spriteCache.get(539).width,
						Client.spriteCache.get(539).height, frameHoverColor);
			} else {
				Client.spriteCache.get(539).drawARGBSprite(265 + offset, 205);
				Raster.drawRectangle(265 + offset, 205, Client.spriteCache.get(539).width,
						Client.spriteCache.get(539).height, frameColor);
			}
			// Monkey
			if (client.mouseInRegion(centerX + 9, centerY - 46, centerX + 85, centerY + 30)) {
				Client.spriteCache.get(540).drawARGBSprite(365 + offset, 205, avatarHoverAlpha);
				Raster.drawRectangle(365 + offset, 205, Client.spriteCache.get(540).width,
						Client.spriteCache.get(540).height, frameHoverColor);
			} else {
				Client.spriteCache.get(540).drawARGBSprite(365 + offset, 205);
				Raster.drawRectangle(365 + offset, 205, Client.spriteCache.get(540).width,
						Client.spriteCache.get(540).height, frameColor);
			}
			// Wolf
			if (client.mouseInRegion(centerX + 112, centerY - 46, centerX + 185, centerY + 30)) {
				Client.spriteCache.get(541).drawARGBSprite(465 + offset, 205, avatarHoverAlpha);
				Raster.drawRectangle(465 + offset, 205, Client.spriteCache.get(541).width,
						Client.spriteCache.get(541).height, frameHoverColor);
			} else {
				Client.spriteCache.get(541).drawARGBSprite(465 + offset, 205);
				Raster.drawRectangle(465 + offset, 205, Client.spriteCache.get(541).width,
						Client.spriteCache.get(541).height, frameColor);
			}
			// Joker
			if (client.mouseInRegion(centerX - 189, centerY + 45, centerX - 114, centerY + 122)) {
				Client.spriteCache.get(680).drawARGBSprite(165 + offset, 295, avatarHoverAlpha);
				Raster.drawRectangle(165 + offset, 295, Client.spriteCache.get(680).width,
						Client.spriteCache.get(680).height, frameHoverColor);
			} else {
				Client.spriteCache.get(680).drawARGBSprite(165 + offset, 295);
				Raster.drawRectangle(165 + offset, 295, Client.spriteCache.get(680).width,
						Client.spriteCache.get(680).height, frameColor);
			}
			// Demon
			if (client.mouseInRegion(centerX - 91, centerY + 45, centerX - 14, centerY + 122)) {
				Client.spriteCache.get(681).drawARGBSprite(265 + offset, 295, avatarHoverAlpha);
				Raster.drawRectangle(265 + offset, 295, Client.spriteCache.get(681).width,
						Client.spriteCache.get(681).height, frameHoverColor);
			} else {
				Client.spriteCache.get(681).drawARGBSprite(265 + offset, 295);
				Raster.drawRectangle(265 + offset, 295, Client.spriteCache.get(681).width,
						Client.spriteCache.get(681).height, frameColor);
			}
			// Hot girl 1
			if (client.mouseInRegion(centerX + 9, centerY + 45, centerX + 85, centerY + 122)) {
				Client.spriteCache.get(682).drawARGBSprite(365 + offset, 295, avatarHoverAlpha);
				Raster.drawRectangle(365 + offset, 295, Client.spriteCache.get(682).width,
						Client.spriteCache.get(682).height, frameHoverColor);
			} else {
				Client.spriteCache.get(682).drawARGBSprite(365 + offset, 295);
				Raster.drawRectangle(365 + offset, 295, Client.spriteCache.get(682).width,
						Client.spriteCache.get(682).height, frameColor);
			}
			// Hot girl 2
			if (client.mouseInRegion(centerX + 112, centerY + 45, centerX + 185, centerY + 122)) {
				Client.spriteCache.get(683).drawARGBSprite(465 + offset, 295, avatarHoverAlpha);
				Raster.drawRectangle(465 + offset, 295, Client.spriteCache.get(683).width,
						Client.spriteCache.get(683).height, frameHoverColor);
			} else {
				Client.spriteCache.get(683).drawARGBSprite(465 + offset, 295);
				Raster.drawRectangle(465 + offset, 295, Client.spriteCache.get(683).width,
						Client.spriteCache.get(683).height, frameColor);
			}

			client.boldText.drawCenteredText(0xFFFFFF, centerX + 5, "[ Click on the avatar you wish to select ]",
					centerY + 150, true);

			/* Other */

			if (Configuration.DEBUG_MODE) {
				/*
				 * client.smallFont.drawCenteredText(0xFFFFFF, centerX + 300, "MouseX: " +
				 * (client.mouseX - (centerX)) + " Mouse Y: " + (client.mouseY - (centerY)),
				 * centerY - 225, true);
				 */
			} else if (loginstate.equals(Loginstate.SETTINGSSCREEN)) {

				/* Background */
				Client.spriteCache.get(678).drawTransparentSprite(
						(Client.frameWidth / 2) - (Client.spriteCache.get(57).width / 2),
						(Client.frameHeight / 2) - (Client.spriteCache.get(57).height / 2), client.loginTick);

				client.boldText.drawCenteredText(0xff9040, centerX + 5, "Runity", centerY - 105, true);
				client.regularText.drawCenteredText(0xB7B7B7, centerX + 5, "Settings", centerY - 85, true);

				// announcement
				client.regularText.drawCenteredText(0xFFFFFF, centerX + 5, "Announcement:", centerY - 50, true);
				if (client.mouseInRegion(centerX - 47, centerY - 37, centerX + 51, centerY - 9)) {
					Raster.fillRectangle(336, 215, 100, 30, 0x1F1D19, 105);
					Raster.drawRectangle(336, 215, 100, 30, 0x3d3427);
				} else {
//					Raster.fillRectangle(336, 215, 100, 30, 0x2D1F1F, 105);
					Raster.drawRectangle(336, 215, 100, 30, 0x3d3427);
				}
				client.smallFont.drawCenteredText(Utility.getPrefix(Settings.DRAW_ANNOUNCEMENT), centerX + 5,
						(Settings.DRAW_ANNOUNCEMENT ? "Enabled" : "Disabled"), centerY - 16, true);

				// bubbles
				client.regularText.drawCenteredText(0xFFFFFF, centerX + 5, "Bubbles:", centerY + 20, true);
				if (client.mouseInRegion(centerX - 47, centerY + 24, centerX + 58, centerY + 64)) {
					Raster.fillRectangle(336, 280, 100, 30, 0x1F1D19, 105);
					Raster.drawRectangle(336, 280, 100, 30, 0x3d3427);
				} else {
//					Raster.fillRectangle(336, 280, 100, 30, 0x2D1F1F, 105);
					Raster.drawRectangle(336, 280, 100, 30, 0x3d3427);
				}
				client.smallFont.drawCenteredText(Utility.getPrefix(Settings.DRAW_BUBBLE), centerX + 5,
						(Settings.DRAW_BUBBLE ? "Enabled" : "Disabled"), centerY + 49, true);

				if (Configuration.DEBUG_MODE) {
					/*
					 * client.smallFont.drawCenteredText(0xFFFFFF, centerX + 300, "MouseX: " +
					 * (client.mouseX - (centerX)) + " Mouse Y: " + (client.mouseY - (centerY)),
					 * centerY - 225, true);
					 */
				}
			}
		}

		/* Drawing */
		Client.loginScreenIP.drawGraphics(client.graphics, 0, 0);
		Raster.reset();
	}

	@Override
	public void click(Client client) {
		int centerX = getX();
		int centerY = getY();
		
		/* Bubble */
		settingButton(client);

		if (loginstate.equals(Loginstate.LOGINSCREEN)) {
			/* Username */
			if (client.lastMetaModifier == 1 && client.mouseInRegion(275, 168, 524, 197))
				client.loginScreenCursorPos = 0;

			/* Password */
			if (client.lastMetaModifier == 1 && client.mouseInRegion(275, 215, 524, 239))
				client.loginScreenCursorPos = 1;

			/* Account */
			int xPos = 270;
			int yPos = centerY - 70;
			if (AccountManager.ACCOUNTS != null) {
				for (int index = 0; index < AccountManager.ACCOUNTS.size(); index++, xPos += 150) {
					AccountData accountData = AccountManager.ACCOUNTS.get(index);
					if (client.lastMetaModifier == 1 && client.mouseInRegion(xPos, 374, xPos + 55, 431)) {
						client.lastAccount = accountData;
						client.myUsername = Utility.formatName(accountData.username.toLowerCase());
						client.myPassword = accountData.password;
						setLoginstate(Loginstate.CAPTCHA);
						if (Client.loggedIn) {
							return;
						}
					}
					if (client.lastMetaModifier == 1 && client.mouseInRegion(xPos - 25, 435, xPos + 136, 458)) {
						if (client.lastAccount == null) {
							client.loginMessage1 = "There was an issue loading your account.";
							return;
						}
						client.lastAccount = accountData;
						setLoginstate(Loginstate.ACCOUNTSCREEN);
						return;
					}
					if (client.lastMetaModifier == 1 && client.mouseInRegion(xPos + 67, 413, xPos + 78, 428)) {
						AccountManager.removeAccount(accountData);
					}
				}
			}

			/* World Button */
			if (client.lastMetaModifier == 1 && client.mouseInRegion(671, 441, 746, 476)) {
				switch (Configuration.CONNECTION) {
				case ECONOMY:
					Configuration.CONNECTION = Connection.MANAGEMENT;
					break;
				case MANAGEMENT:
					Configuration.CONNECTION = Connection.DEVELOPMENT;
					break;
				case DEVELOPMENT:
					Configuration.CONNECTION = Connection.ECONOMY;
					break;
				}
				Client.server = Configuration.CONNECTION.address;
			}

			/* Login Buttons */
			if (client.lastMetaModifier == 1 && client.mouseInRegion(318, 285, 452, 328)) {
				if (client.myUsername.isEmpty() || client.myPassword.isEmpty()) {
					client.loginMessage1 = "Please enter your account credentials before you log in!";
				} else {
					setLoginstate(Loginstate.CAPTCHA);
				}
			}
			/* Writing */
			handleWriting(client);

		} else if (loginstate.equals(Loginstate.CAPTCHA)) {

			/* Submit Button */
			//if (client.lastMetaModifier == 1 && client.mouseInRegion(320, 350, 454, 393)) {
				if (!Client.loggedIn) {
					//if (captcha.isCorrect(client.captchaInput)) {
					//	captcha = null;
						//client.captchaInput = "";
					    setLoginstate(null);
						client.login(client.myUsername, client.myPassword, false);
					//} else {
					//	info = "Invalid captcha please retry";
					//}
				}
			//}
			//handleCaptchaWritting(client);
		} else if (loginstate.equals(Loginstate.ACCOUNTSCREEN)) {
			/* Bubble */
			settingButton(client);

			/* Avatar */
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 181, centerY - 32, centerX - 84, centerY + 66)) {
				client.loginMessage1 = "";
				client.loginMessage2 = "";
				setLoginstate(Loginstate.AVATARSCREEN);
				return;
			}

			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 381, centerY - 249, centerX + 381, centerY + 245)) {
				client.loginMessage1 = "";
				client.loginMessage2 = "";
				setLoginstate(Loginstate.LOGINSCREEN);
			}
		} else if (loginstate.equals(Loginstate.MESSAGESCREEN)) {
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 381, centerY - 249, centerX + 381, centerY + 245)) {
				client.loginMessage1 = "";
				client.loginMessage2 = "";
				setLoginstate(Loginstate.LOGINSCREEN);
			}
		} else if (loginstate.equals(Loginstate.AVATARSCREEN)) {

			/* Bubble */
			settingButton(client);

			// Darth Vader
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 215, centerY - 136, centerX - 115, centerY - 62)) {
				select(client, 534);
			}
			// Skeleton
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 91, centerY - 136, centerX - 14, centerY - 62)) {
				select(client, 535);
			}
			// Cool dude
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX + 9, centerY - 136, centerX + 85, centerY - 62)) {
				select(client, 536);
			}
			// Gas mask
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX + 112, centerY - 136, centerX + 185, centerY - 62)) {
				select(client, 537);
			}
			// KFC
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 188, centerY - 46, centerX - 114, centerY + 30)) {
				select(client, 538);
			}
			// Dog
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 91, centerY - 46, centerX - 14, centerY + 30)) {
				select(client, 539);
			}
			// Monkey
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX + 9, centerY - 46, centerX + 85, centerY + 30)) {
				select(client, 540);
			}
			// Wolf
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX + 112, centerY - 46, centerX + 185, centerY + 30)) {
				select(client, 541);
			}
			// Joker
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 189, centerY + 45, centerX - 114, centerY + 122)) {
				select(client, 680);
			}
			// Demon
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 91, centerY + 45, centerX - 14, centerY + 122)) {
				select(client, 681);
			}
			// Hot girl 1
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX + 9, centerY + 45, centerX + 85, centerY + 122)) {
				select(client, 682);
			}
			// Hot girl 2
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX + 112, centerY + 45, centerX + 185, centerY + 122)) {
				select(client, 683);
			}
		} else if (loginstate.equals(Loginstate.SETTINGSSCREEN)) {
			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 47, centerY - 37, centerX + 51, centerY - 9)) {
				Settings.DRAW_ANNOUNCEMENT = !Settings.DRAW_ANNOUNCEMENT;
			}

			if (client.lastMetaModifier == 1
					&& client.mouseInRegion(centerX - 47, centerY + 24, centerX + 58, centerY + 64)) {
				Settings.DRAW_BUBBLE = !Settings.DRAW_BUBBLE;
			}

			/* Bubble */
			settingButton(client);
		}

	}

	private void select(Client client, int avatar) {
		AccountData account = client.lastAccount;

		if (account == null) {
			return;
		}

		account.avatar = avatar;
		AccountManager.setAvatar(account.username, avatar);
		AccountManager.saveAccount();
		client.loginMessage1 = "";
		client.loginMessage2 = "";
		setLoginstate(Loginstate.ACCOUNTSCREEN);
	}

	public String GeneratingRandomString() {
		int length = 9;
		boolean useLetters = true;
		boolean useNumbers = false;
		return RandomStringUtils.random(length, useLetters, useNumbers);

	}

	/**
	 * Handles drawing the accounts on the login screen.
	 */
	@SuppressWarnings("ConstantConditions")
	private void drawAccount(Client client) {
//        Raster.fillRectangle(155, 100, 313, 164, 0x2E2827, 150);

		int centerX = getX();
		int centerY = getY();
		int xPos = 270;
		int yPos = centerY - 70;
		int frameColor = 0x3d3427;
		for (int index = 0; index < 2; index++, xPos += 150) {
			AccountData accountData = AccountManager.get(index);

			if (accountData == null) {
				Client.spriteCache.get(73).drawARGBSprite(xPos, 374);
				Client.spriteCache.get(676).drawARGBSprite(xPos - 30, 438);
			} else {
				if (client.mouseInRegion(xPos + 67, 413, xPos + 78, 428)) {
					Client.spriteCache.get(675).drawARGBSprite(xPos + 65, 410);
					addTooltip("Delete profile");
				} else {
					Client.spriteCache.get(674).drawARGBSprite(xPos + 65, 410);
				}
				Client.spriteCache.get(accountData.avatar).drawARGBSprite(xPos, 374);
				Client.spriteCache.get(676).drawARGBSprite(xPos - 30, 438);
				Raster.drawRectangle(xPos, 374, Client.spriteCache.get(accountData.avatar).width,
						Client.spriteCache.get(accountData.avatar).height, frameColor);
				int rank = (accountData.rank - 1);
				String name = accountData.username;
//                if (rank <= -1) {
//                    client.newSmallFont.drawCenteredString(Utility.formatName(name), xPos + 30, 455);
//                } else {
//                    client.newSmallFont.drawCenteredString((rank <= -1 ? Utility.formatName(name) : "<img=" + rank + ">"), centerX - 145, yPos + 13);
				client.newSmallFont.drawCenteredString((rank <= -1 ? "" : "<img=" + rank + ">") + "<col=ffffff>"
						+ Utility.formatName(name.toLowerCase()), xPos + 30, 455);
//                }
				if (client.mouseInRegion(xPos - 25, 435, xPos + 136, 458)) {
					Raster.drawRectangle(xPos - 30, 438, Client.spriteCache.get(676).width,
							Client.spriteCache.get(676).height, 0x1F1D19);
					Raster.fillRectangle(xPos - 30, 438, Client.spriteCache.get(676).width,
							Client.spriteCache.get(676).height, 0x1F1D19, 50);
					addTooltip("Manage profile details");
				}
				if (client.mouseInRegion(xPos, 374, xPos + 55, 431)) {
					Raster.drawRectangle(xPos, 374, Client.spriteCache.get(accountData.avatar).getWidth(),
							Client.spriteCache.get(accountData.avatar).getHeight(), 0x1F1D19);
					Raster.fillRectangle(xPos, 374, Client.spriteCache.get(accountData.avatar).getWidth(),
							Client.spriteCache.get(accountData.avatar).getHeight(), 0x1F1D19, 50);
					addTooltip("Login profile");
				}
			}
		}
	}

	/**
	 * Handles writing in the client.
	 */
	/*private void handleCaptchaWritting(Client client) {
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
	}*/

	/**
	 * Handles writing in the client.
	 */
	private void handleWriting(Client client) {
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

			// Main account username
			if (client.loginScreenCursorPos == 0) {
				if (line == 8 && client.myUsername.length() > 0)
					client.myUsername = client.myUsername.substring(0, client.myUsername.length() - 1);
				if (line == 9 || line == 10 || line == 13) {
					client.loginScreenCursorPos = 1;
				}
				if (flag) {
					client.myUsername += (char) line;
				}

				if (client.myUsername.length() > EMAIL_CHARACTER_LIMIT) {
					client.myUsername = client.myUsername.substring(0, EMAIL_CHARACTER_LIMIT);
				}

				// Main account password
			} else if (client.loginScreenCursorPos == 1) {
				if (line == 8 && client.myPassword.length() > 0)
					client.myPassword = client.myPassword.substring(0, client.myPassword.length() - 1);
				if (line == 9 || line == 10 || line == 13) {
					client.attemptLogin(client.myUsername, client.myPassword, false);
				}
				if (flag) {
					client.myPassword += (char) line;
				}
				if (client.myPassword.length() > 20) {
					client.myPassword = client.myPassword.substring(0, 20);
				}
			}
		} while (true);
		return;
	}

	@Override
	public ScreenType type() {
		return ScreenType.MAIN;
	}
}