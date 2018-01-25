package com.zilker.rssfeed.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import org.json.*;

import com.zilker.rssfeed.bean.RssFeedBean;

public class RssFeedData {
	Logger logger = Logger.getLogger(RssFeedData.class.getName());

	public JSONArray fetchRssFeedData(String userUrl) {
		String jsonData = "";
		String temp = "";
		try {
			URL url = new URL(userUrl);
			InputStream inputStream = url.openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((temp = bufferedReader.readLine()) != null) {
				jsonData += temp;
			}
			logger.info(jsonData);
			JSONObject jsonObject = XML.toJSONObject(jsonData);
			jsonObject = (JSONObject) jsonObject.get("rss");
			jsonObject = (JSONObject) jsonObject.get("channel");
			JSONArray jsonArray = (JSONArray) jsonObject.get("item");
			return jsonArray;
		} catch (Exception e) {
			logger.warning("Problem occurred while fetching the data");
		}
		return null;
	}
}
