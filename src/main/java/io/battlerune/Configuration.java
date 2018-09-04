package io.battlerune;

/**
 * Handles all configuration settings for the client.
 *
 * @author Daniel
 */
public class Configuration {

	public static final String DISCORD_LINK = "https://discord.gg/vVmujW4";
	public static final String WEBSITE_LINK = "http://runity.io/";

	static boolean reporterror = true;

	public static boolean USING_OSBUDDY = false;

	static String errorname = "";

	public static final String SPRITE_FILE_NAME = "main_file_sprites";

	/** The current NPC bits. */
	static final int NPC_BITS = 16;

	/** State of client dumping it's resources. */
	public static boolean DUMP_RESOURCES = false;

	/** Display client data. */
	static boolean CLIENT_DATA = false;

	/** State of client being in debug mode. */
	public static boolean DEBUG_MODE = true;
	//KEEP THIS FALSE

	/** Debug the interfaces. */
	static boolean DEBUG_INTERFACES = false;

	/** State of client enabling RSA encryption. */
	static boolean ENABLE_RSA = true;

	/** The name of the client. */
	public final static String NAME = "Runity";

	/** The prefix of the client. */
	final static String PREFIX = "";

	/** The character folder path. */
	static final String CHAR_PATH = Utility.findcachedir() + "Character";

	/** All the announcements which will be displayed on the loginscreen. */
	public final static String[] ANNOUNCEMENT = { "Welcome to Runity", "We are currently in beta mode.",
			"Developers are Adam, and Nerik." };

	/** The current displayed loading message. */
	static String LOADING_MESSAGE;

	/** The loading screen messages. */
	final static String[] LOADING_MESSAGES = { "You can teleport around the map by clicking on the world map.",
			"Voting will be very rewarding to your account's progression.",
			"Need help? Ask any staff member or join the 'help' chan channel.",
			"Being part of a clan will make your progress a lot faster!", "Donating helps us keep the lights on!" };

	/** The left nav bar links. */
	public static final String[] LEFT_NAV_LINKS = { "https://www.runity.io", "https://www.runity.io/community",
			"https://discord.me/runity" };

	/** The right nav bar links. */
	public static final String[] RIGHT_NAV_LINKS = { "https://www.runity.io/vote", "https://www.runity.io/store/",
			"https://www.runity.io/highscores/" };

	/** The IP address client will be connecting to. */
	public static Connection CONNECTION = Connection.ECONOMY;

	/** The current client version. */
	public static final int CLIENT_VERSION = 4;
	
	/** The current game version. */
	public static final int GAME_VERSION = 5;

}
