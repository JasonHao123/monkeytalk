package com.gorillalogic.monkeyconsole.server;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.core.har.HarPage;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

import org.eclipse.jface.preference.IPreferenceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gorillalogic.monkeyconsole.plugin.FoneMonkeyPlugin;
import com.gorillalogic.monkeyconsole.preferences.PreferenceConstants;

public class ProxyServer {
	private BrowserMobProxy serverSocket;
	private static Logger logger = LoggerFactory.getLogger(ProxyServer.class);
	public ProxyServer() throws IOException {
		IPreferenceStore store = FoneMonkeyPlugin.getDefault()
				.getPreferenceStore();
		int port = store.getInt(PreferenceConstants.P_PROXY_SERVER_PORT);
		logger.info("server started at " + port);
		System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation());
		serverSocket = new BrowserMobProxyServer();
//		Har har = serverSocket.newHar();	
		
		serverSocket.setTrustAllServers(true);
		serverSocket.setRequestTimeout(5, TimeUnit.SECONDS);
		serverSocket.start(port);
		
//		serverSocket.addRequestFilter(new RequestFilter() {
//	            @Override
//	            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
//	                logger.info("request:"+messageInfo.getUrl()+" "+request.hashCode()+" "+messageInfo.hashCode());
////	                logger.info("request:"+request.hashCode());
////	                logger.info("request:"+messageInfo.toString());
//
//	                // in the request filter, you can return an HttpResponse object to "short-circuit" the request
//	                return null;
//	            }
//	        });
//
//	        // responses are equally as simple:
//		serverSocket.addResponseFilter(new ResponseFilter() {
//	            @Override
//	            public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
//	            	
//	                logger.info("response:"+messageInfo.getUrl()+" "+messageInfo.getOriginalRequest().hashCode()+ " "+messageInfo.hashCode());
////	                logger.info("request:"+messageInfo.toString());	            	
////	            	logger.info("response:"+messageInfo.getUrl());
//	            	
//	            	
//	            }
//	        });

	}

	public void stop() {
		serverSocket.stop();
	}

}
