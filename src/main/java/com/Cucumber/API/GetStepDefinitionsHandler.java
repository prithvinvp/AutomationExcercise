package com.Cucumber.API;

import java.io.IOException;

import com.Utils.Configs.Core;
import com.Utils.Reports.Log;

public class GetStepDefinitionsHandler {
	
	private String baseUrl;
	
	public static void getUrl(String baseUrl) throws IOException {
		try {
			baseUrl = Core.readProperty(baseUrl);
			Log.message("Base url is: " + baseUrl);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	
}
