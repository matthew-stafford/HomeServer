package com.github.matthewstafford.homeserver.business;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.github.matthewstafford.homeserver.Main;
import com.github.matthewstafford.homeserver.beans.ServerServiceBean;

@Component
public class AutomatedServerServices {

	public AutomatedServerServices() {

	}

	public ArrayList<ServerServiceBean> getServerServiceBeans() {
		return new ArrayList<ServerServiceBean>(Main.ssd.getPorts().values());

	}

}
