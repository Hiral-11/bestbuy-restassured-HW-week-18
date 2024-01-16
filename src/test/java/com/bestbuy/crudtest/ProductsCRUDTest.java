package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String type = "Testing";
    static int price = 1850;
    static int shipping = 1;
    static String upc = "JAVA";
    static String description = "API";
    static String manufacturer = "4Pillar";
    static String model = "Testing";
    static String url = "prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String image = "prime.jpg";
    static int productID ;

    @BeforeTest
    public void setup(){
        RestAssured.basePath ="/products";
    }

    //Post Data
    @Test
    public void test001() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

        productID =response.then().extract().path("id");
        System.out.println("ID = " + productID);
    }
    //Put Data
    @Test
    public void test002() {

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(200);

        response.prettyPrint();
    }


    //Get Data
    @Test
    public void test003() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productPojo)
                .when()
                .patch("/" + productID);
        response.then().statusCode(200);

        response.prettyPrint();
    }

    //Delete Data
    @Test
    public void test004() {

        given()
                .when()
                .delete("/" + productID)
                .then()
                .statusCode(200);

        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(404);
    }

}

