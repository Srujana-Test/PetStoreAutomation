package api.endpoints;
/*
swagger URI -- https://petstore.swagger.io
URL = url + end points
Uniform resource locator- URL
Create User-               POST-- https://petstore.swagger.io/v2/user
Get User ---               GET----https://petstore.swagger.io/v2/user/{username}
Update User---         PUT/PATCH--https://petstore.swagger.io/v2/user/{username}
Delete User-------         DELETE-https://petstore.swagger.io/v2/user/{username}
*
* */
public class Routes {
    //Just maintain the URLS in routes. Don't implement anything here
public static String base_url="https://petstore.swagger.io/v2";

//User module
    public static String post_url=base_url+"/user";
    public static String get_url=base_url+"/user/{userName}";
    public static String update_url=base_url+"/user/{userName}";
    public static String delete_url=base_url+"/user/{userName}";
    //above {userName} -- path parameter

    //Store module
    //here you add store module urls

    //Pet Module
    //here you create module urls


}
