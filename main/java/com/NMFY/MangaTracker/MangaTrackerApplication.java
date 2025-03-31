package com.NMFY.MangaTracker;

import com.NMFY.MangaTracker.util.DiscordWebHook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MangaTrackerApplication {

	public static void main(String[] args) {
		DiscordWebHook.webhookUrl = args[0];
		SpringApplication.run(MangaTrackerApplication.class, args);
	}

}
