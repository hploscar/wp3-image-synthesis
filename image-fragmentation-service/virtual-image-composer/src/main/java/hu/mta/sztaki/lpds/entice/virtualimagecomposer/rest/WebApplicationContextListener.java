package hu.mta.sztaki.lpds.entice.virtualimagecomposer.rest;

import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
//import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;

public class WebApplicationContextListener implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(VirtualImageComposer.class); 

	private ScheduledExecutorService scheduler = null;
	
	public void contextInitialized(ServletContextEvent sce) {
		log.info("Starting up Virtual Image Composer service...");
//		try {
//			scheduler = Executors.newSingleThreadScheduledExecutor();
//			scheduler.scheduleAtFixedRate(new TimedTask(), 0, 60 * 60, TimeUnit.SECONDS);
//		} catch (Exception e) {
//			log.error("Cannot start scheduler: " + e.getMessage());
//			e.printStackTrace();
//		}
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Shutting down Virtual Image Composer service...");
		if (scheduler != null) scheduler.shutdownNow();
	}
	
	class TimedTask extends TimerTask {
		public void run() {
			log.debug("Timed task...");
		}
	}
}
