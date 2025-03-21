//
//
//package com.rest.test;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.response.ValidatableResponse;
//import io.restassured.specification.RequestSpecification;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import static org.hamcrest.core.IsEqual.equalTo;
//
//@Test
//public class MainTest {
//
//    RequestSpecification requestSpecification;
//    Response response;
//    ValidatableResponse validatableResponse;
//    private int[] books;
//
////*1*//
//@Test
//    public void verifyStatusCode() {
//
//        // Base URL of the API
//        RestAssured.baseURI = "http://localhost:8082/books";
//
//        // Username and password for Basic Authentication
//        String username = "user"; // Replace with the correct username
//        String password = "password"; // Replace with the correct password
//
//        // Create the request specification
//        RequestSpecification requestSpecification = given()
//                .auth().preemptive().basic(username, password) // Use preemptive basic auth
//                .log().all(); // Log all request details (headers, body, etc.)
//
//        // Send GET request and get the response
//        Response response = requestSpecification.get();
//
//        // Print the response details for debugging
//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.prettyPrint());
//        System.out.println("Response Headers: " + response.getHeaders());
//
//        // Perform validation on the response
//        ValidatableResponse validatableResponse = response.then();
//
//        /* Validate status code */
//        validatableResponse.statusCode(200);
//
//        // Validate status line
//        validatableResponse.statusLine("HTTP/1.1 200 ");
//    }
//
//
//
//
////*2*//
//    @Test
//    public void testCreateBook() {
//        String requestBody = "{\n" +
//                "    \"name\": \"A to the Bodhisattva Way of Life\",\n" +
//                "    \"author\": \"Santideva\",\n" +
//                "    \"price\": 15.41\n" +
//                "}";
//
//        Response response = given()
//                .auth().basic("admin", "password")
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("http://localhost:8082/books")
//                .then()
//                .statusCode(201)
//                .extract().response();
//
//        // Validate the response body
//        response.then().body("name", equalTo("A to the Bodhisattva Way of Life"))
//                .body("author", equalTo("Santideva"))
//                .body("price", equalTo(15.41f));
//    }
//
//
//
//
//
//
//
////*3*//
//    @Test
//    public void testUpdateBook() {
//        int bookId = 1;
//
//        String updatedRequestBody = "{\n" +
//                "    \"id\": 1,\n" +
//                "    \"name\": \"A to the Bodhisattva Way of Life\",\n" +
//                "    \"author\": \"Santideva\",\n" +
//                "    \"price\": 20.00\n" +
//                "}";
//
//        Response response = given()
//                .auth().basic("admin", "password")
//                .contentType("application/json")
//                .body(updatedRequestBody)
//                .when()
//                .put("http://localhost:8082/books/" + bookId)
//                .then()
//                .statusCode(200)
//                .extract().response();
//        // Validate the updated book details
//        response.then().body("price", equalTo(20.00f));
//    }
//
////*4*//
////@Test
////    public void testDeleteBook() {
////        int bookId = 2;
////
////        Response response = given()
////                .auth().basic("admin", "password")
////                .contentType("application/json")
////                .when()
////                .delete("http://localhost:8082/books/" + bookId)
////                .then()
////                .statusCode(200)
////                .extract().response();
////
////        // Optionally, check if the book is deleted by trying to get the same book
////        given().auth().basic("admin", "password")
////                .when().get("http://localhost:8082/books/" + bookId)
////                .then().statusCode(404); // Expecting 404 Not Found
////    }
////**5//
//@Test
//   public void testGetBookById() {
//        int bookId = 1;
//
//        Response response = given()
//                .auth().basic("admin", "password")
//                .contentType("application/json")
//                .when()
//                .get("http://localhost:8082/books/" + bookId)
//                .then()
//                .log().all()  // Log response details for debugging
//                .extract().response();
//
//        int statusCode = response.getStatusCode();
//        System.out.println("Response Status Code: " + statusCode);
//        System.out.println("Response Body: " + response.asString());
//
//    }
//
////*6*//
//    @Test
//    public void testGetBooksSortedByName() {
//        Response response = given()
//                .auth().basic("user", "password")
//                .queryParam("sort", "name")
//                .queryParam("order", "asc")
//                .when()
//                .get("http://localhost:8082/books")
//                .then()
//                .statusCode(200)
//                .body("books.size()", greaterThan(1)) // Ensure there are at least 2 books to compare
//                .extract().response();
//    }
////*7*//
//    public void testGetBooksByAuthor() {
//        Response response = given()
//                .auth().basic("user", "password")
//                .queryParam("author", "Santideva")
//                .when()
//                .get("http://localhost:8082/books")
//                .then()
//                .statusCode(200)
//                .body("books.size()", greaterThan(0)) // Ensure books exist
//                .extract().response();
//
//    }
////*8*//
//    @Test
//    public void testBookPriceGreaterThanZero() {
//        int bookId = 1;
//
//        given()
//                .auth().basic("user", "password")
//                .when()
//                .get("http://localhost:8082/books/" + bookId)
//                .then()
//                .statusCode(200)
//                .body("price", greaterThan(0.0f));
//    }
//
////*9*//
//    @Test
//    public void testGetBooksByPriceRange() {
//        // Define the price range
//        double minPrice = 20.0;
//        double maxPrice = 50.0;
//
//        // Send the request with the price range as query parameters
//        given()
//                .auth().basic("user", "password")
//                .queryParam("minPrice", minPrice)
//                .queryParam("maxPrice", maxPrice)
//                .when()
//                .get("http://localhost:8082/books")
//                .then()
//                .statusCode(200)
//                .body("books.size()", greaterThan(0)) // Ensure there are books returned
//                .body("books.price", everyItem(greaterThanOrEqualTo((float) minPrice))) // Validate that all books have price >= minPrice
//                .body("books.price", everyItem(lessThanOrEqualTo((float) maxPrice))); // Validate that all books have price <= maxPrice
//    }
////*10//
//    public void testGetBooksWithPagination() {
//        given()
//                .auth().basic("user", "password")
//                .queryParam("page", 1)  // Specify the page number
//                .queryParam("limit", 10) // Specify the number of books per page
//                .when()
//                .get("http://localhost:8082/books")
//                .then()
//                .statusCode(200);
//    }
////*11*//
//    @Test
//    public void testBookNotFound() {
//        given().auth().basic("user", "password")
//                .when().get("http://localhost:8082/books/9999")
//                .then().statusCode(404);
//    }
//
////*12*//
//    @Test
//    public void testCreateBookWithMissingName() {
//        String requestBody = "{ \"author\": \"Erich Gamma\", \"price\": 55.00 }";
//        given().auth().basic("admin", "password").contentType("application/json").body(requestBody)
//                .when().post("http://localhost:8082/books")
//                .then().statusCode(400);  // Expecting a bad request error
//    }
////*13*//
//    @Test
//    public void testCreateBookWithInvalidPrice() {
//        String requestBody = "{ \"name\": \"Patterns of Enterprise Application Architecture\", \"author\": \"Martin Fowler\", \"price\": -10.00 }";
//        given().auth().basic("admin", "password").contentType("application/json").body(requestBody)
//                .when().post("http://localhost:8082/books")
//                .then().statusCode(400);  // Invalid price should return 400
//    }
////*14*//
//    @Test
//    public void testGetBooksCount() {
//        given().auth().basic("user", "password")
//                .when().get("http://localhost:8082/books")
//                .then().statusCode(200).body("books.size()", greaterThan(0));
//    }
////*15*//
////    @Test
////    public void testBookPriceAfterDeletion() {
////        given().auth().basic("admin", "password").when().delete("http://localhost:8082/books/4")
////                .then().statusCode(200);
////        given().auth().basic("user", "password").when().get("http://localhost:8082/books/4")
////                .then().statusCode(404);
////    }
////*16*//
////    @Test
////    public void testCreateBookWithZeroPrice() {
////        String requestBody = "{ \"name\": \"Zero Price Book\", \"author\": \"Test Author\", \"price\": 0.00 }";
////        given().auth().basic("admin", "password").contentType("application/json").body(requestBody)
////                .when().post("http://localhost:8082/books")
////                .then().statusCode(201).body("price", equalTo(0.00f));
////    }
//
//    //*17*//
//    @Test
//    public void testGetAllBooks() {
//        given().auth().basic("user", "password")
//                .when().get("http://localhost:8082/books")
//                .then().statusCode(200)
//                .body("books", not(empty())); // Ensure books list is not empty
//    }
//    
// //Negative testcases
//    
//  //Failed//
//
//  //*1*//
//      /**
//       * Test to create a new book.
//       * Expected to FAIL.
//       */
//      @Test
//      public void testCreateBookNegative() {
//          String requestBody = "{\"name\": \"Test Book\", \"author\": \"Author A\", \"price\": 19.99}";
//          given().auth().basic("admin", "password")
//                  .contentType("application/json")
//                  .body(requestBody)
//                  .when().post("/books")
//                  .then().log().all() // Logs the response
//                  .statusCode(201)
//                  .body("name", equalTo("Test Book"))
//                  .body("author", equalTo("Author A"))
//                  .body("price", equalTo(19.99f));
//      }
//
//
//
//      //*2*//
//
//      /**
//       * Test to retrieve a book by ID.
//       * Expected to .
//       */
//      @Test
//      public void testGetBookByIdNegative() {
//          int bookId = 1;
//          given().auth().basic("admin", "password")
//                  .contentType("application/json")
//                  .when().get("/books/" + bookId)
//                  .then().log().all() // Logs the response
//                  .statusCode(200)
//                  .body("id", equalTo(bookId))
//                  .body("name", not(empty()))
//                  .body("author", not(empty()))
//                  .body("price", greaterThan(0f));
//      }
//
//
//      //*3*//
//
//      /**
//       * Test to update an existing book.
//       * Expected to FAIL.
//       */
//      @Test
//      public void testUpdateBookNegative() {
//          int bookId = 1;
//          String updatedRequestBody = "{\"id\": 1, \"name\": \"Updated Book\", \"author\": \"Author B\", \"price\": 25.99}";
//          given().auth().basic("admin", "password")
//                  .contentType("application/json")
//                  .body(updatedRequestBody)
//                  .when().put("/books/" + bookId)
//                  .then().log().all() // Logs the response
//                  .statusCode(200)
//                  .body("name", equalTo("Updated Book"))
//                  .body("author", equalTo("Author B"))
//                  .body("price", equalTo(25.99f));
//      }
//
//
//      //*4*//
//      /**
//       * Test to delete a book by ID.
//       * Expected to PASS.
//       */
//      @Test
//      public void testDeleteBookNegative() {
//          int bookId = 1;
//          given().auth().basic("admin", "password")
//                  .contentType("application/json")
//                  .when().delete("/books/" + bookId)
//                  .then().log().all() // Logs the response
//                  .statusCode(200);
//
//          // Verify deletion
//          given().auth().basic("admin", "password")
//                  .when().get("/books/" + bookId)
//                  .then().log().all() // Logs the response
//                  .statusCode(404);
//      }
//
//
//      //*5*//
//      /**
//       * Test to verify that GET /books returns a list of books.
//       * Expected to FAIL.
//       */
//      @Test
//      public void testGetBooksNegative() {
//          given().auth().basic("user", "password")
//                  .contentType("application/json")
//                  .when().get("/books")
//                  .then().log().all() // Logs the response
//                  .statusCode(200)
//                  .body("page", greaterThanOrEqualTo(1))
//                  .body("limit", greaterThanOrEqualTo(1))
//                  .body("total", greaterThan(0))
//                  .body("users", not(empty()));
//      }
//
//
//  }
//

package com.rest.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MainTest {

    private static final String BASE_URI = "http://localhost:8082/books";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";
    private RequestSpecification requestSpecification;
    private Response response;

    @BeforeClass
    public void setup() {
        // Set base URI for RestAssured
        RestAssured.baseURI = BASE_URI;

        // Initialize RequestSpecification
        requestSpecification = given().auth().basic(USERNAME, PASSWORD).contentType("application/json");
    }

    @Test
    public void verifyStatusCode() {
        response = requestSpecification.get();
        response.then().statusCode(200).log().all();
    }

    @Test
    public void testCreateBook() {
        String requestBody = "{\n" +
                "    \"name\": \"A to the Bodhisattva Way of Life\",\n" +
                "    \"author\": \"Santideva\",\n" +
                "    \"price\": 15.41\n" +
                "}";

        response = given()
                .auth().basic(USERNAME, PASSWORD)
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URI);

        response.then().statusCode(201)
                .body("name", equalTo("A to the Bodhisattva Way of Life"))
                .body("author", equalTo("Santideva"))
                .body("price", equalTo(15.41f))
                .log().all();
    }

    @Test
    public void testUpdateBook() {
        int bookId = 1;

        String updatedRequestBody = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"A to the Bodhisattva Way of Life\",\n" +
                "    \"author\": \"Santideva\",\n" +
                "    \"price\": 20.00\n" +
                "}";

        response = given()
                .auth().basic(USERNAME, PASSWORD)
                .contentType("application/json")
                .body(updatedRequestBody)
                .put(BASE_URI + "/" + bookId);

        response.then().statusCode(200)
                .body("price", equalTo(20.00f))
                .log().all();
    }

    @Test
    public void testGetBookById() {
        int bookId = 1;
        response = given()
                .auth().basic(USERNAME, PASSWORD)
                .contentType("application/json")
                .get(BASE_URI + "/" + bookId);

        response.then().statusCode(200)
                .log().all();
    }

    @Test
    public void testGetBooksSortedByName() {
        response = given()
                .auth().basic("user", "password")
                .queryParam("sort", "name")
                .queryParam("order", "asc")
                .get(BASE_URI);

        response.then().statusCode(200)
                .body("books.size()", greaterThan(1))
                .log().all();
    }

    @Test
    public void testGetBooksByPriceRange() {
        double minPrice = 20.0;
        double maxPrice = 50.0;

        response = given()
                .auth().basic("user", "password")
                .queryParam("minPrice", minPrice)
                .queryParam("maxPrice", maxPrice)
                .get(BASE_URI);

        response.then().statusCode(200)
                .body("books.size()", greaterThan(0))
                .body("books.price", everyItem(greaterThanOrEqualTo((float) minPrice)))
                .body("books.price", everyItem(lessThanOrEqualTo((float) maxPrice)))
                .log().all();
    }

    @Test
    public void testBookNotFound() {
        int invalidBookId = 9999;
        response = given()
                .auth().basic("user", "password")
                .get(BASE_URI + "/" + invalidBookId);

        response.then().statusCode(404)
                .log().all();
    }

    @Test
    public void testCreateBookWithMissingName() {
        String requestBody = "{ \"author\": \"Erich Gamma\", \"price\": 55.00 }";
        response = given()
                .auth().basic("admin", "password")
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URI);

        response.then().statusCode(400).log().all();
    }

    @Test
    public void testCreateBookWithInvalidPrice() {
        String requestBody = "{ \"name\": \"Patterns of Enterprise Application Architecture\", \"author\": \"Martin Fowler\", \"price\": -10.00 }";
        response = given()
                .auth().basic("admin", "password")
                .contentType("application/json")
                .body(requestBody)
                .post(BASE_URI);

        response.then().statusCode(400).log().all();
    }

    @Test
    public void testGetAllBooks() {
        response = given()
                .auth().basic("user", "password")
                .get(BASE_URI);

        response.then().statusCode(200)
                .body("books", not(empty()))
                .log().all();
    }
}

//
//
//
