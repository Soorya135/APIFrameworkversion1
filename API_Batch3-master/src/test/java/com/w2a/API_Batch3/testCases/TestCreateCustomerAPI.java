package com.w2a.API_Batch3.testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.API_Batch3.setUp.APISetUp;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreateCustomerAPI extends APISetUp {

	@Test
	public void validateCreateCustomerAPIWithValidData() {

		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec = setRequestSpecification().formParam("email", "rahul.jha_rest@gmail.com")
				.formParam("description", "Testing Stripe using restASsured").formParam("account_balance", 10000).log()
				.all();

		System.out.println("====================================================");

		Response response = spec.post("customers");

		testLevelLog.get().info(response.asString());

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 200);

		/*
		 * How to fetch the fields from the response ,there are different ways,
		 * first one is path
		 */

		String emailinresponse = response.path("email");

		System.out.println("The value of the email is " + emailinresponse);

		String descriptioninresponse = response.path("description");

		System.out.println("The value of the description is " + descriptioninresponse);

		// How to fetch the value for the footer which is inside json object

		System.out.println("The value of the footer is" + response.path("invoice_settings.footer"));

		// how to fetch the subscription is

		System.out.println("The value of the url is" + response.path("subscriptions.url"));

		/// Accessing the object using the JSONPath object

		JsonPath response1 = new JsonPath(response.asString());

		System.out.println("The value of the email using the JSONPath Method is" + response1.get("email"));

	}
	
	/*@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp")
	public void fetchcustomerdata()
	{
		
	}*/

}
