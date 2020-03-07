package ethos.net.discord;

import java.awt.Color;
import java.net.URI;

import org.json.JSONObject;

import ethos.net.discord.Misc;

import ethos.net.discord.DiscordMessage;
import ethos.net.discord.WebhookClient;
import ethos.net.discord.WebhookClientBuilder;
import ethos.net.discord.DiscordEmbed;


public class DiscordMessager extends JSONObject { 
	
	public static boolean active = false;
	
	private static String announcementhook = "https://discordapp.com/api/webhooks/554751664129835008/crgd-fjaGYoidU2xS2r87HKJsKdL4vQxs_2C8RkmkrHYAdiWpiNAjP7h9q9g0iddYS2K";
	private static String newplayers = "https://discordapp.com/api/webhooks/554751274957013023/qeRhGGo5NnTXEGnBs4szt6QbD-fH2jxrlnjPmqA8wbTPy2lTgitOz6joCLzz5s98lr-J";
	private static String lootations = "https://discordapp.com/api/webhooks/554751057092149263/C6IcUZBjVwe6_4Ev5Is8986yetjUhmu-ie4u-RjD8Db3yRQPPQvPNb-spuobDB28vuFc"; 
	private static String staffhook = "https://discordapp.com/api/webhooks/529734675501940766/C5p05SBtdN9KS9v9kzeI2Btv4v2KAuMg79Lg77rJPXyU0kuKCzeqBsgivMsTc1oL55iv"; 
	private static String ingamehook = "https://discordapp.com/api/webhooks/529735554896494596/6VUxtedwiANRDBrG3ggDSTteMaA8D_Z4egX7JQRadQEEeikniyE7JI_nMItwlcpifQxF";
	private static String debughook = "https://discordapp.com/api/webhooks/529734675501940766/C5p05SBtdN9KS9v9kzeI2Btv4v2KAuMg79Lg77rJPXyU0kuKCzeqBsgivMsTc1oL55iv";
	private static String prestige = "https://discordapp.com/api/webhooks/554751912096956507/JaJ02NYlHY4y3nbJEE2R0C6kN3t9Os7oiirWfYZpGt-djSkacMwCjizS4RRWK1L33y1K";
	
	
	public static void sendAnnouncement(String msg) {
		try {
			
			String webhook = announcementhook;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    .withTitle("OS-Revolution") // The title of the embed element
				    .withURL("http://OS-Revolution.com/") // The URL of the embed element
				    .withColor(Color.GREEN) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("Announcement Bot") // Override the username of the bot
				    .build(); // Build the message
			
			client.sendPayload(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendPrestige(String msg) {
		try {
			
			String webhook = prestige;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    .withTitle("OS-Revolution") // The title of the embed element
				    .withURL("http://OS-Revolution.com/") // The URL of the embed element
				    .withColor(Color.GREEN) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("Leveling Bot") // Override the username of the bot
				    .build(); // Build the message
			
			client.sendPayload(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendLootations(String msg) {
		try {
			
			String webhook = lootations;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    .withTitle("OS-Revolution") // The title of the embed element
				    .withURL("http://OS-Revolution.com/") // The URL of the embed element
				    .withColor(Color.GREEN) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("Lootations Bot") // Override the username of the bot
				    .build(); // Build the message
			
			client.sendPayload(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendNewPlayers(String msg) {
		try {
			
			String webhook = newplayers;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    .withTitle("OS-Revolution") // The title of the embed element
				    .withURL("http://OS-Revolution.com/") // The URL of the embed element
				    .withColor(Color.GREEN) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("New Players Bot") // Override the username of the bot
				    .build(); // Build the message
			
			client.sendPayload(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendStaffMessage(String msg) {
		try {
			
			String webhook = staffhook;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    //.withTitle("Necrotic - RSPS") // The title of the embed element
				    //.withURL("http://necrotic.org/") // The URL of the embed element
				    .withColor(Color.ORANGE) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("Staff Bot") // Override the username of the bot
				    .build(); // Build the message
			
			client.sendPayload(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendInGameMessage(String msg) {
		try {
			
			
			String webhook = ingamehook;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    //.withTitle("Necrotic - RSPS") // The title of the embed element
				    //.withURL("http://necrotic.org/") // The URL of the embed element
				    .withColor(Color.BLUE) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			String msgToSend = Misc.stripIngameFormat(msg);
			
			DiscordMessage message = new DiscordMessage.Builder(msgToSend) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("In-Game Bot") // Override the username of the bot
				    .build(); // Build the message
			
			if (msgToSend.equalsIgnoreCase(":information_source:!")) {
				sendDebugMessage("Bad message from sendInGameMessage, \n"+msgToSend);
			} else {
				client.sendPayload(message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendDebugMessage(String msg) {
		try {
			
			String webhook = debughook;
			
			WebhookClient client = new WebhookClientBuilder()
				    .withURI(new URI(webhook))
				    .build(); // Create the webhook client
			
			new DiscordEmbed.Builder()
				    .withTitle("OS-Revolution") // The title of the embed element
				    .withURL("http://OS-Revolution.net/") // The URL of the embed element
				    .withColor(Color.MAGENTA) // The color of the embed. You can leave this at null for no color
				    .withDescription("Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The description of the embed object
				    .build();
			
			//DiscordMessage message = new DiscordMessage.Builder(msg) 
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the message
				    //.withEmbed(embed) // Add our embed object
				    .withUsername("Debug Bot") // Override the username of the bot
				    .build(); // Build the message
			
			client.sendPayload(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
