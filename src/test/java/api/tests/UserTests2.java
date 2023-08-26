package api.tests;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
Faker faker;
User userPayload;
public Logger logger;

    @BeforeClass
    public void setup()
{
    faker=new Faker();
    userPayload= new User();

    userPayload.setId(faker.idNumber().hashCode());
    userPayload.setuserName(faker.name().username());
    userPayload.setFirstName(faker.name().firstName());
    userPayload.setLastName(faker.name().lastName());
    userPayload.setEmail(faker.internet().safeEmailAddress());
    userPayload.setPassword(faker.internet().password(5,10));
    userPayload.setPhone(faker.phoneNumber().cellPhone());
    userPayload.setUserStatus(0);


    //logs
    logger = LogManager.getLogger(this.getClass());
}
@Test(priority = 1)
    public void testPostUser()
{
    logger.info("***************Creating User************");
    Response response = UserEndpoints2.createUser(userPayload);
    response.then().log().all();
    int statusCode = response.getStatusCode();
    if (statusCode == 200) {
        // Process successful response
        String respMessage = response.jsonPath().getString("message");
        System.out.println("resp: " + respMessage);
    }
    Assert.assertEquals(response.getStatusCode(),200);
    logger.info("***************User Created************");
}
    @Test(priority = 2)
    public void testGetUserByName()
    {
        logger.info("***************Reading User************");
        Response response = UserEndpoints2.readUser(this.userPayload.getuserName());
        //https://petstore.swagger.io/v2/user/bernard.davis
        //Response response = "https://petstore.swagger.io/v2/user/bernard.davis";
        System.out.println("response is:"+response);
        response.then().log().all();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            // Process successful response
        } else if (statusCode == 404) {
            // Handle "User not found" error
            String errorMessage = response.jsonPath().getString("message");
            System.out.println("Error: " + errorMessage);
        } else {
            // Handle other errors
        }
        //Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***************User info displayed************");
    }
    @Test(priority = 3)
    public void testUpdateUserByName()
    { logger.info("***************Updating User************");
        //update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response = UserEndpoints2.updateUser(this.userPayload.getuserName(),userPayload);
        response.then().log().all();
        //response.then().log().body().statusCode(200);//same as assert
        response.then().log().body();
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            // Process successful response
        } else if (statusCode == 404) {
            // Handle "User not found" error
            String errorMessage = response.jsonPath().getString("message");
            System.out.println("Error: " + errorMessage);
        } else {
            // Handle other errors
        }
        Assert.assertEquals(response.getStatusCode(),200);
        //checking the data after update
        Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getuserName());
        responseAfterUpdate.then().log().all();
        logger.info("**************User Updated************");
    }
    @Test(priority = 4)
    public void testDeleteUserByName()
    {
        logger.info("***************Deleting User************");
        Response response = UserEndpoints2.deleteUser(this.userPayload.getuserName());
        System.out.println("response is:"+response);

        //Assert.assertEquals(response.getStatusCode(),200);
        logger.info("***************User Deleted************");
    }

}
