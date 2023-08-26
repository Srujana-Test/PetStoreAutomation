package api.tests;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTests {
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone) {
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setuserName(UserName);
        userPayload.setFirstName(FirstName);
        userPayload.setLastName(LastName);
        userPayload.setEmail(Email);
        userPayload.setPassword(Password);
        userPayload.setPhone(Phone);
        {
            Response response = UserEndpoints.createUser(userPayload);
            response.then().log().all();
            //int statusCode = response.getStatusCode();
            Assert.assertEquals(response.getStatusCode(),200);

        }
    }
    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserByName(String UserName)
    {
        Response response = UserEndpoints.readUser(UserName);
        response.then().log().all();
        //Assert.assertEquals(response.getStatusCode(),200);
    }
   @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String UserName)
    {
      Response response = UserEndpoints.deleteUser(UserName);
        response.then().log().all();
        //Assert.assertEquals(response.getStatusCode(),200);
    }
}