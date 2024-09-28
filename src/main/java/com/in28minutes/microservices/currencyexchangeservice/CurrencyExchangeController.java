package com.in28minutes.microservices.currencyexchangeservice;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.util.Constants;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private CurrencyExchangeRepository repository;

	@Autowired
	private Environment environment;

	@GetMapping(Constants.Api.EXCHANGE_FROM_TO)
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		logger.info("retrieveExchangeValue called with {} to {}", from, to);

		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

		if (currencyExchange == null) {
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);
		}

		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);

		return currencyExchange;

	}

	@GetMapping(Constants.Api.EXCHANGE_TEST)
	public APIResponse getSystemInfo(@RequestParam String input) {

		APIResponse response = new APIResponse();
		try
		{
			String port = environment.getProperty("local.server.port");
			String hostName = InetAddress.getLocalHost().getHostName();
			
			response.setError(false);
			response.setStatus("SUCCESS");
			
			StringBuilder sb = new StringBuilder();
			sb.append("input : "+input);
			sb.append("port : "+port);
			sb.append("hostName : "+hostName);
			response.setData(sb.toString());
		}
		catch(Exception ee)
		{
			response.setError(true);
			response.setMessage(ee.getMessage());
			response.setStatus("FAILURE");
		}

		return response;

	}

}
