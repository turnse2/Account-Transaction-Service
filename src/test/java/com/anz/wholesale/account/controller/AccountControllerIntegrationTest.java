
package com.anz.wholesale.account.controller;

import com.anz.wholesale.account.AccountTransactionServiceApplication;
import com.anz.wholesale.account.entities.Account;
import com.anz.wholesale.account.entities.AccountTransaction;
import com.anz.wholesale.account.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountTransactionServiceApplication.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("integration-test")
public class AccountControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	HttpHeaders headers ;


	private List<Account> accounts;

	private List<AccountTransaction> accountTransactions;

	private User user;

	@Before
	public void setup(){
		headers = new HttpHeaders();
	}

	@Test
	public void givenUser_whenGetAccount_thenStatus200() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/users/1/accounts";
		URI uri = new URI(baseUrl);
		headers.set("accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				baseUrl, HttpMethod.GET, entity, String.class);
		String expected = "[\n" +
				"      {\n" +
				"      \"id\": 1,\n" +
				"      \"accountNumber\": 900000,\n" +
				"      \"accountName\": \"test account1\",\n" +
				"      \"accountType\": \"SAVING\",\n" +
				"      \"balanceDate\": \"2018-08-01\",\n" +
				"      \"accountCurrencyType\": \"AUD\",\n" +
				"      \"openingAvailableBalance\": 50,\n" +
				"      \"updatedTime\": null\n" +
				"   },\n" +
				"      {\n" +
				"      \"id\": 2,\n" +
				"      \"accountNumber\": 900001,\n" +
				"      \"accountName\": \"test account2\",\n" +
				"      \"accountType\": \"CURRENT\",\n" +
				"      \"balanceDate\": \"2017-08-01\",\n" +
				"      \"accountCurrencyType\": \"SGD\",\n" +
				"      \"openingAvailableBalance\": 30,\n" +
				"      \"updatedTime\": null\n" +
				"   },\n" +
				"      {\n" +
				"      \"id\": 3,\n" +
				"      \"accountNumber\": 900002,\n" +
				"      \"accountName\": \"test account3\",\n" +
				"      \"accountType\": \"SAVING\",\n" +
				"      \"balanceDate\": \"2016-08-01\",\n" +
				"      \"accountCurrencyType\": \"AUD\",\n" +
				"      \"openingAvailableBalance\": 40,\n" +
				"      \"updatedTime\": null\n" +
				"   }\n" +
				"]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}


	@Test
	public void givenAccountNumber_whenGetTransaction_thenStatus200() throws Exception {
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/accounts/2/transactions";
		URI uri = new URI(baseUrl);
		headers.set("accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				baseUrl, HttpMethod.GET, entity, String.class);
		String expected = "[{\n" +
				"   \"id\": 3,\n" +
				"   \"account\":    {\n" +
				"      \"id\": 2,\n" +
				"      \"accountNumber\": 900001,\n" +
				"      \"accountName\": \"test account2\",\n" +
				"      \"accountType\": \"CURRENT\",\n" +
				"      \"balanceDate\": \"2017-08-01\",\n" +
				"      \"accountCurrencyType\": \"SGD\",\n" +
				"      \"openingAvailableBalance\": 30,\n" +
				"      \"updatedTime\": null\n" +
				"   },\n" +
				"   \"transactionNumber\": 900000003,\n" +
				"   \"accountType\": null,\n" +
				"   \"valueDate\": \"2010-08-13\",\n" +
				"   \"transactionCurrencyType\": \"AUD\",\n" +
				"   \"debitAmount\": 0,\n" +
				"   \"creditAmount\": 5003.32,\n" +
				"   \"transactionNarrative\": \"deposit 3\",\n" +
				"   \"transactionTime\": null\n" +
				"}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

}
