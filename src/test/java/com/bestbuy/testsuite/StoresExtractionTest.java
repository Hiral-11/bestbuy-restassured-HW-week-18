package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost:3030";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }


    //    1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {

        List<String> namesOfTheStore = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all stores are : " + namesOfTheStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {

        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> listOfData = response.extract().path("data");
        System.out.println("The size of the data list is : " + listOfData.size());
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<Integer> valuesOfTheStore = response.extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("Values of the store : " + valuesOfTheStore);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("The address of the store where store name = Rochester : " + address);
    }
//9. Get all the services of 8th store
@Test
public void test09(){
    List<List<String>> services = response.extract().path("data[7].services");
    System.out.println("List of all the services of 8th store " + services);
}
//10. Get storeservices of the store where service name = Windows Store
@Test
public void test010(){
    //List<List<String>> storeServices = response.extract().path("data.findAll{it.name == 'Windows Store'}.services.name");
    List<HashMap<String, ?>> windowsStoreServices = response.extract().path("data.findAll { it.services.find { it.name == 'Windows Store' } != null }.services.storeservices");
    System.out.println("Storeservices of the store where service name = Windows Store is " + windowsStoreServices);
}
//11. Get all the storeId of all the store
    @Test
public void test011() {
    List<Integer> allStoreID = response.extract().path("data.services.storeservices.storeId");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of StorId is : " + allStoreID);
    System.out.println("------------------End of Test---------------------------");
}

//12. Get id of all the store
@Test
public void test012() {
    List<Integer> allStoreID = response.extract().path("data.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of StorId is : " + allStoreID);
    System.out.println("------------------End of Test---------------------------");
}

//13. Find the store names Where state = ND
    @Test
public void test013() {
        List<String> storeNames =  response.extract().path("data.findAll{it.state == 'ND'}.services.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Where state = ND : " + storeNames);
    System.out.println("------------------End of Test---------------------------");
}
@Test
//14. Find the Total number of services for the store where store name = Rochester
public void test014() {
    List<Integer> totalRochester = response.extract().path("data.findAll{it.name=='Rochester'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("the store where store name = Rochester : " + totalRochester.size());
    System.out.println("------------------End of Test---------------------------");
}
@Test
//15. Find the createdAt for all services whose name = “Windows Store”
public void test015() {
    List<?> createdAtWS = response.extract().path("data.findAll{it.services.findAll{it.name=='Windows Store'}}.services.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Services the store where service name = Windows Store : " + createdAtWS);
    System.out.println("------------------End of Test---------------------------");
}


// 16. Find the name of all services Where store name = “Fargo”
@Test
public void test016() {
    List<String> totalFargo = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Services the store where service name = Fargo : " + totalFargo);
    System.out.println("------------------End of Test---------------------------");
}

    @Test
    // Find the zip of store name = Roseville
    public void test018() {
        List<String> zipRoseville = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store : " + zipRoseville);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    // Find the storeservices details of the service name = Magnolia Home Theater
    public void test019() {
        List<HashMap<String, ?>> magnoliaHomename = response.extract().path("data.findAll { it.services.find { it.name == 'Magnolia Home Theater'} != null }.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices details of the service name = Magnolia Home Theater" + magnoliaHomename);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test020() {
        // Find the lat of all the stores
        List<Integer> latAllStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the store where store name = Rochester : " + latAllStore);
        System.out.println("------------------End of Test---------------------------");

    }
}

