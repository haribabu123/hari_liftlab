package Utilites;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

	private final Properties properties;

	private static ConfigLoader configLoader;

	private ConfigLoader() {

		properties = PropertyUtils.propertyLoader(System.getProperty("user.home") + "/"
				+ "IdeaProjects/PetStoreAPIAutmation/src/main/resources/config.properties");
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}

		return configLoader;
	}

	public String getBaseUrl() {
		String baseUrl = properties.getProperty("base.url");
		if (baseUrl != null)
			return baseUrl;
		else
			throw new RuntimeException("couldn't fetch base url");
	}

	public String getBaseUrl(String value) {

		String baseUrl = properties.getProperty(value);

		if (baseUrl != null) {
			return baseUrl;
		} else {
			throw new RuntimeException("couldn't fetch url");
		}
	}
}
