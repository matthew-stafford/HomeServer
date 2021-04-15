package com.github.matthewstafford.homeserver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.github.matthewstafford.homeserver.beans.ServerServiceBean;

public class ServerServiceDetector {

	public TreeMap<Integer, ServerServiceBean> ports = new TreeMap<Integer, ServerServiceBean>();

	public void setPorts() {
		final int min = 1, max = 65535;
		// ports below 1024 are not allowed to be bound by a user on Linux 
		for (int i = min; i < max; i++) {
			ServerServiceBean bean = checkWebService("http://localhost", i);
			if (bean != null) {
				ports.put(i, bean);
			}
		}
	}

	public TreeMap<Integer, ServerServiceBean> getPorts() {
		return ports;
	}

	private ServerServiceBean checkWebService(String url, int port) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url + ":" + port).openConnection();
			connection.setReadTimeout(300);
			connection.setConnectTimeout(300);
			connection.setRequestMethod("HEAD");

			int responseCode = connection.getResponseCode();

			// is a web service, create bean for it
			if (responseCode > -1) {
				return generateServerServiceBean(url, port);
			} else {
				// open port but not a web service
				return null;
			}
		} catch (IOException exception) {
			return null;
		}
	}

	private ServerServiceBean generateServerServiceBean(String url, int port) {
		ServerServiceBean sb = new ServerServiceBean(port);

		try {

			// check body for title and favicon
			Document doc = Jsoup.connect(url + ":" + port).get();
			sb.setName(doc.title());
			sb.setPort(port);
			sb.setUrl(url + ":" + port);
			Element element = doc.head().select("link[href~=.*\\.(ico|png|svg)]").first();
			if (element == null) {
				element = doc.head().select("meta[itemprop=image]").first();
			}

			if (element == null) {
				// favicon either doesnt exist or is declared in JS.
				element = doc.head().select("link[rel='icon']").first();
				if (element != null && element.id() != null && element.id().length() > 0) {
					// look for nearest .png or .ico after you find this in text
					int index = doc.html().lastIndexOf(element.id());

					// put into sorted map to find closest image (hopefully it is the correct one)
					TreeMap<Integer, String> arr = new TreeMap<Integer, String>();
					arr.put(doc.html().indexOf(".png", index), ".png");
					arr.put(doc.html().indexOf(".svg", index), ".svg");
					arr.put(doc.html().indexOf(".ico", index), ".ico");

					// find the first one which isnt -1 (not found)
					for (Entry<Integer, String> val : arr.entrySet()) {
						if (val.getKey() > -1) {
							sb.setFavicon(fixUrl(doc.html().substring(index, val.getKey()) + "" + val.getValue()));
						}
					}

				}
			}

			if (element != null && element.attr("href") != null && element.attr("href").length() > 0) {
				sb.setFavicon(element.attr("href"));
			}
		} catch (IOException exception) {
			return null;
		}

		return sb;
	}

	private String fixUrl(String s) {
		HashSet<Character> invalidCharacters = new HashSet<Character>();
		invalidCharacters.add('=');
		invalidCharacters.add(' ');
		invalidCharacters.add('\'');
		invalidCharacters.add('\"');
		String str = "";
		for (int i = s.length() - 1; i > 0; i--) {
			char c = s.charAt(i);
			if (invalidCharacters.contains(c)) {
				return str;
			} else {
				str = c + str;
			}
		}
		return str;
	}
}
