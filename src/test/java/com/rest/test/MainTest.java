//package com.rest.test;

//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import io.restassured.response.ValidatableResponse;
//import io.restassured.specification.RequestSpecification;
////import org.junit.jupiter.api.BeforeAll;
////import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//import org.junit.Test;
//
//public class MainTest {
//
//    @Test
//    public void verifyStatusCode() {
//        RestAssured.baseURI = "http://localhost:8082/books";
//        String username = "user";
//        String password = "password";
//
//        RequestSpecification requestSpecification = given()
//                .auth().preemptive().basic(username, password)
//                .log().all();
//
//        Response response = requestSpecification.get();
//
//        System.out.println("Response Status Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.prettyPrint());
//        System.out.println("Response Headers: " + response.getHeaders());
//
//        ValidatableResponse validatableResponse = response.then();
//        validatableResponse.statusCode(200);
//        validatableResponse.statusLine("HTTP/1.1 200 ");
//    }
//
//    @Test
//    public void testGetBooks() {
//        Response response = given()
//                .auth().basic("user", "password")
//                .contentType("application/json")
//                .when()
//                .get("http://localhost:8082/books")
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//        response.then().body("", hasSize(greaterThanOrEqualTo(2)));
//    }
//
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
//        response.then().body("name", equalTo("A to the Bodhisattva Way of Life"))
//                .body("author", equalTo("Santideva"))
//                .body("price", equalTo(15.41f));
//    }
//
//    @Test
//    public void testGetBookById() {
//        int bookId = 4;
//
//        Response response = given()
//                .auth().basic("admin", "password")
//                .contentType("application/json")
//                .when()
//                .get("http://localhost:8082/books/" + bookId)
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//        response.then().body("id", equalTo(bookId))
//                .body("name", equalTo("A Guide to the Bodhisattva Way of Life"))
//                .body("author", equalTo("Santideva"))
//                .body("price", equalTo(15.41f));
//    }
//
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
//
//        response.then().body("price", equalTo(20.00f));
//    }
//
//    @Test
//    public void testDeleteBook() {
//        int bookId = 1;
//
//        Response response = given()
//                .auth().basic("admin", "password")
//                .contentType("application/json")
//                .when()
//                .delete("http://localhost:8082/books/" + bookId)
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//        given()
//                .auth().basic("admin", "password")
//                .when()
//                .get("http://localhost:8082/books/" + bookId)
//                .then()
//                .statusCode(404);
//    }
//}

////
////
////}

//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//
//public class MainTest {
//
//    @BeforeAll
//    static void setup() {
//        RestAssured.baseURI = "http://localhost:8082"; // Change this if the API runs on a different port
//    }
//
//    // ✅ Positive Test Cases
//
//    @Test
//    public void testGetBooksPagination() {
//        Response response = given()
//            .when().get("/books")
//            .then()
//            .statusCode(200)
//            .body("page", equalTo(1))
//            .body("limit", equalTo(10))
//            .body("total", greaterThan(0))
//            .extract().response();
//
//        assertNotNull(response.jsonPath().getList("books"), "Books list should not be null");
//    }
//
//    @Test
//    public void testPostBookWithSpecialCharacters() {
//        String newBook = "{ \"name\": \"The Art of War - Sun Tzu (2024 Edition)\", \"author\": \"Sun Tzu\", \"price\": 25.99 }";
//
//        Response response = given()
//            .contentType(ContentType.JSON)
//            .body(newBook)
//            .when().post("/books")
//            .then()
//            .statusCode(201)
//            .body("name", equalTo("The Art of War - Sun Tzu (2024 Edition)"))
//            .body("author", equalTo("Sun Tzu"))
//            .body("price", equalTo(25.99f))
//            .extract().response();
//
//        assertNotNull(response.jsonPath().getInt("id"), "Book ID should not be null");
//    }
//
//    @Test
//    public void testGetBookById() {
//        Response response = given()
//            .when().get("/books/2")
//            .then()
//            .statusCode(200)
//            .body("id", equalTo(2))
//            .extract().response();
//
//        assertEquals("The Alchemist", response.jsonPath().getString("name"), "Book name should be correct");
//    }
//
//    @Test
//    public void testPutBookUpdatesOnlyPrice() {
//        String updatedBook = "{ \"price\": 29.99 }";
//
//        Response response = given()
//            .contentType(ContentType.JSON)
//            .body(updatedBook)
//            .when().put("/books/2")
//            .then()
//            .statusCode(200)
//            .body("price", equalTo(29.99f))
//            .extract().response();
//
//        assertEquals(29.99f, response.jsonPath().getFloat("price"), 0.01, "Updated price should be correct");
//    }
//
//    @Test
//    public void testDeleteBookAndVerifyNotFound() {
//        int bookId = 5;
//
//        // Delete the book
//        given()
//            .when().delete("/books/" + bookId)
//            .then()
//            .statusCode(200)
//            .body("message", containsString("deleted"));
//
//        // Verify it no longer exists
//        given()
//            .when().get("/books/" + bookId)
//            .then()
//            .statusCode(404)
//            .body("error", containsString("not found"));
//    }
//
//    // ❌ Negative Test Cases
//
//    @Test
//    public void testGetNonExistentBook() {
//        given()
//            .when().get("/books/9999")
//            .then()
//            .statusCode(404)
//            .body("error", containsString("not found"));
//    }
//
//    @Test
//    public void testPostBookWithMissingFields() {
//        String incompleteBook = "{ \"author\": \"Unknown\", \"price\": 19.99 }"; // Missing "name" field
//
//        given()
//            .contentType(ContentType.JSON)
//            .body(incompleteBook)
//            .when().post("/books")
//            .then()
//            .statusCode(400)
//            .body("error", containsString("Missing required field: name"));
//    }
//
//    @Test
//    public void testPutBookWithInvalidDataType() {
//        String invalidBook = "{ \"id\": 2, \"name\": \"The Alchemist\", \"author\": \"Paulo Coelho\", \"price\": \"twenty\" }"; // price as string
//
//        given()
//            .contentType(ContentType.JSON)
//            .body(invalidBook)
//            .when().put("/books/2")
//            .then()
//            .statusCode(400)
//            .body("error", containsString("Invalid data type for price"));
//    }
//
//    @Test
//    public void testDeleteBookWithoutAuthorization() {
//        given()
//            .when().delete("/books/3") // Assuming authentication is required
//            .then()
//            .statusCode(401)
//            .body("error", containsString("Unauthorized"));
//    }
//
//    @Test
//    public void testInvalidHttpMethod() {
//        given()
//            .when().put("/books") // PUT should require an ID
//            .then()
//            .statusCode(405)
//            .body("error", containsString("Method Not Allowed"));
//    }
//}


//testcase 0 tests run

//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class MainTest {
//
//    @Before
//    public void setup() {
//        RestAssured.baseURI = "http://localhost:8082"; // Change this if the API runs on a different port
//    }
//
//    // ✅ Positive Test Cases
//
//    @Test
//    public void testGetBooksPagination() {
//        Response response = given()
//            .when().get("/books")
//            .then()
//            .statusCode(200)
//            .body("page", equalTo(1))
//            .body("limit", equalTo(10))
//            .body("total", greaterThan(0))
//            .extract().response();
//
//        assertNotNull("Books list should not be null", response.jsonPath().getList("books"));
//    }
//
//    @Test
//    public void testPostBookWithSpecialCharacters() {
//        String newBook = "{ \"name\": \"The Art of War - Sun Tzu (2024 Edition)\", \"author\": \"Sun Tzu\", \"price\": 25.99 }";
//
//        Response response = given()
//            .contentType(ContentType.JSON)
//            .body(newBook)
//            .when().post("/books")
//            .then()
//            .statusCode(201)
//            .body("name", equalTo("The Art of War - Sun Tzu (2024 Edition)"))
//            .body("author", equalTo("Sun Tzu"))
//            .body("price", equalTo(25.99f))
//            .extract().response();
//
//        Integer bookId = response.jsonPath().getInt("id"); // Extract book ID
//        assertNotNull("Book ID should not be null", bookId); // Assert that the ID is not null
//        assertTrue("Book ID should be greater than 0", bookId > 0); // Ensure the ID is valid
//    }
//
//    @Test
//    public void testGetBookById() {
//        Response response = given()
//            .when().get("/books/2")
//            .then()
//            .statusCode(200)
//            .body("id", equalTo(2))
//            .extract().response();
//
//        String bookName = response.jsonPath().getString("name");
//        assertEquals("The Alchemist", bookName);
//    }
//
//    @Test
//    public void testPutBookUpdatesOnlyPrice() {
//        String updatedBook = "{ \"price\": 29.99 }";
//
//        Response response = given()
//            .contentType(ContentType.JSON)
//            .body(updatedBook)
//            .when().put("/books/2")
//            .then()
//            .statusCode(200)
//            .body("price", equalTo(29.99f))
//            .extract().response();
//
//        float updatedPrice = response.jsonPath().getFloat("price");
//        assertEquals("Updated price should be correct", 29.99f, updatedPrice, 0.01);
//    }
//
//    @Test
//    public void testDeleteBookAndVerifyNotFound() {
//        int bookId = 5;
//
//        // Delete the book
//        given()
//            .when().delete("/books/" + bookId)
//            .then()
//            .statusCode(200)
//            .body("message", containsString("deleted"));
//
//        // Verify it no longer exists
//        given()
//            .when().get("/books/" + bookId)
//            .then()
//            .statusCode(404)
//            .body("error", containsString("not found"));
//    }
//
//    // ❌ Negative Test Cases
//
//    @Test
//    public void testGetNonExistentBook() {
//        given()
//            .when().get("/books/9999")
//            .then()
//            .statusCode(404)
//            .body("error", containsString("not found"));
//    }
//
//    @Test
//    public void testPostBookWithMissingFields() {
//        String incompleteBook = "{ \"author\": \"Unknown\", \"price\": 19.99 }"; // Missing "name" field
//
//        given()
//            .contentType(ContentType.JSON)
//            .body(incompleteBook)
//            .when().post("/books")
//            .then()
//            .statusCode(400)
//            .body("error", containsString("Missing required field: name"));
//    }
//
//    @Test
//    public void testPutBookWithInvalidDataType() {
//        String invalidBook = "{ \"id\": 2, \"name\": \"The Alchemist\", \"author\": \"Paulo Coelho\", \"price\": \"twenty\" }"; // price as string
//
//        given()
//            .contentType(ContentType.JSON)
//            .body(invalidBook)
//            .when().put("/books/2")
//            .then()
//            .statusCode(400)
//            .body("error", containsString("Invalid data type for price"));
//    }
//
//    @Test
//    public void testDeleteBookWithoutAuthorization() {
//        given()
//            .when().delete("/books/3") // Assuming authentication is required
//            .then()
//            .statusCode(401)
//            .body("error", containsString("Unauthorized"));
//    }
//
//    @Test
//    public void testInvalidHttpMethod() {
//        given()
//            .when().put("/books") // PUT should require an ID
//            .then()
//            .statusCode(405)
//            .body("error", containsString("Method Not Allowed"));
//    }
//}


package com.rest.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

@Test
public class MainTest {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    private int[] books;

//*1*//
@Test
    public void verifyStatusCode() {

        // Base URL of the API
        RestAssured.baseURI = "http://localhost:8082/books";

        // Username and password for Basic Authentication
        String username = "user"; // Replace with the correct username
        String password = "password"; // Replace with the correct password

        // Create the request specification
        RequestSpecification requestSpecification = given()
                .auth().preemptive().basic(username, password) // Use preemptive basic auth
                .log().all(); // Log all request details (headers, body, etc.)

        // Send GET request and get the response
        Response response = requestSpecification.get();

        // Print the response details for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.prettyPrint());
        System.out.println("Response Headers: " + response.getHeaders());

        // Perform validation on the response
        ValidatableResponse validatableResponse = response.then();

        /* Validate status code */
        validatableResponse.statusCode(200);

        // Validate status line
        validatableResponse.statusLine("HTTP/1.1 200 ");
    }




//*2*//
    @Test
    public void testCreateBook() {
        String requestBody = "{\n" +
                "    \"name\": \"A to the Bodhisattva Way of Life\",\n" +
                "    \"author\": \"Santideva\",\n" +
                "    \"price\": 15.41\n" +
                "}";

        Response response = given()
                .auth().basic("admin", "password")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:8082/books")
                .then()
                .statusCode(201)
                .extract().response();

        // Validate the response body
        response.then().body("name", equalTo("A to the Bodhisattva Way of Life"))
                .body("author", equalTo("Santideva"))
                .body("price", equalTo(15.41f));
    }







//*3*//
    @Test
    public void testUpdateBook() {
        int bookId = 1;

        String updatedRequestBody = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"A to the Bodhisattva Way of Life\",\n" +
                "    \"author\": \"Santideva\",\n" +
                "    \"price\": 20.00\n" +
                "}";

        Response response = given()
                .auth().basic("admin", "password")
                .contentType("application/json")
                .body(updatedRequestBody)
                .when()
                .put("http://localhost:8082/books/" + bookId)
                .then()
                .statusCode(200)
                .extract().response();
        // Validate the updated book details
        response.then().body("price", equalTo(20.00f));
    }

//*4*//
//@Test
//    public void testDeleteBook() {
//        int bookId = 2;
//
//        Response response = given()
//                .auth().basic("admin", "password")
//                .contentType("application/json")
//                .when()
//                .delete("http://localhost:8082/books/" + bookId)
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//        // Optionally, check if the book is deleted by trying to get the same book
//        given().auth().basic("admin", "password")
//                .when().get("http://localhost:8082/books/" + bookId)
//                .then().statusCode(404); // Expecting 404 Not Found
//    }
//**5//
@Test
   public void testGetBookById() {
        int bookId = 1;

        Response response = given()
                .auth().basic("admin", "password")
                .contentType("application/json")
                .when()
                .get("http://localhost:8082/books/" + bookId)
                .then()
                .log().all()  // Log response details for debugging
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        System.out.println("Response Body: " + response.asString());

    }

//*6*//
    @Test
    public void testGetBooksSortedByName() {
        Response response = given()
                .auth().basic("user", "password")
                .queryParam("sort", "name")
                .queryParam("order", "asc")
                .when()
                .get("http://localhost:8082/books")
                .then()
                .statusCode(200)
                .body("books.size()", greaterThan(1)) // Ensure there are at least 2 books to compare
                .extract().response();
    }
//*7*//
    public void testGetBooksByAuthor() {
        Response response = given()
                .auth().basic("user", "password")
                .queryParam("author", "Santideva")
                .when()
                .get("http://localhost:8082/books")
                .then()
                .statusCode(200)
                .body("books.size()", greaterThan(0)) // Ensure books exist
                .extract().response();

    }
//*8*//
    @Test
    public void testBookPriceGreaterThanZero() {
        int bookId = 1;

        given()
                .auth().basic("user", "password")
                .when()
                .get("http://localhost:8082/books/" + bookId)
                .then()
                .statusCode(200)
                .body("price", greaterThan(0.0f));
    }

//*9*//
    @Test
    public void testGetBooksByPriceRange() {
        // Define the price range
        double minPrice = 20.0;
        double maxPrice = 50.0;

        // Send the request with the price range as query parameters
        given()
                .auth().basic("user", "password")
                .queryParam("minPrice", minPrice)
                .queryParam("maxPrice", maxPrice)
                .when()
                .get("http://localhost:8082/books")
                .then()
                .statusCode(200)
                .body("books.size()", greaterThan(0)) // Ensure there are books returned
                .body("books.price", everyItem(greaterThanOrEqualTo((float) minPrice))) // Validate that all books have price >= minPrice
                .body("books.price", everyItem(lessThanOrEqualTo((float) maxPrice))); // Validate that all books have price <= maxPrice
    }
//*10//
    public void testGetBooksWithPagination() {
        given()
                .auth().basic("user", "password")
                .queryParam("page", 1)  // Specify the page number
                .queryParam("limit", 10) // Specify the number of books per page
                .when()
                .get("http://localhost:8082/books")
                .then()
                .statusCode(200);
    }
//*11*//
    @Test
    public void testBookNotFound() {
        given().auth().basic("user", "password")
                .when().get("http://localhost:8082/books/9999")
                .then().statusCode(404);
    }

//*12*//
    @Test
    public void testCreateBookWithMissingName() {
        String requestBody = "{ \"author\": \"Erich Gamma\", \"price\": 55.00 }";
        given().auth().basic("admin", "password").contentType("application/json").body(requestBody)
                .when().post("http://localhost:8082/books")
                .then().statusCode(400);  // Expecting a bad request error
    }
//*13*//
    @Test
    public void testCreateBookWithInvalidPrice() {
        String requestBody = "{ \"name\": \"Patterns of Enterprise Application Architecture\", \"author\": \"Martin Fowler\", \"price\": -10.00 }";
        given().auth().basic("admin", "password").contentType("application/json").body(requestBody)
                .when().post("http://localhost:8082/books")
                .then().statusCode(400);  // Invalid price should return 400
    }
//*14*//
    @Test
    public void testGetBooksCount() {
        given().auth().basic("user", "password")
                .when().get("http://localhost:8082/books")
                .then().statusCode(200).body("books.size()", greaterThan(0));
    }
//*15*//
//    @Test
//    public void testBookPriceAfterDeletion() {
//        given().auth().basic("admin", "password").when().delete("http://localhost:8082/books/4")
//                .then().statusCode(200);
//        given().auth().basic("user", "password").when().get("http://localhost:8082/books/4")
//                .then().statusCode(404);
//    }
//*16*//
//    @Test
//    public void testCreateBookWithZeroPrice() {
//        String requestBody = "{ \"name\": \"Zero Price Book\", \"author\": \"Test Author\", \"price\": 0.00 }";
//        given().auth().basic("admin", "password").contentType("application/json").body(requestBody)
//                .when().post("http://localhost:8082/books")
//                .then().statusCode(201).body("price", equalTo(0.00f));
//    }

    //*17*//
    @Test
    public void testGetAllBooks() {
        given().auth().basic("user", "password")
                .when().get("http://localhost:8082/books")
                .then().statusCode(200)
                .body("books", not(empty())); // Ensure books list is not empty
    }
    
 //Negative testcases
    
  //Failed//

  //*1*//
      /**
       * Test to create a new book.
       * Expected to FAIL.
       */
      @Test
      public void testCreateBookNegative() {
          String requestBody = "{\"name\": \"Test Book\", \"author\": \"Author A\", \"price\": 19.99}";
          given().auth().basic("admin", "password")
                  .contentType("application/json")
                  .body(requestBody)
                  .when().post("/books")
                  .then().log().all() // Logs the response
                  .statusCode(201)
                  .body("name", equalTo("Test Book"))
                  .body("author", equalTo("Author A"))
                  .body("price", equalTo(19.99f));
      }



      //*2*//

      /**
       * Test to retrieve a book by ID.
       * Expected to .
       */
      @Test
      public void testGetBookByIdNegative() {
          int bookId = 1;
          given().auth().basic("admin", "password")
                  .contentType("application/json")
                  .when().get("/books/" + bookId)
                  .then().log().all() // Logs the response
                  .statusCode(200)
                  .body("id", equalTo(bookId))
                  .body("name", not(empty()))
                  .body("author", not(empty()))
                  .body("price", greaterThan(0f));
      }


      //*3*//

      /**
       * Test to update an existing book.
       * Expected to FAIL.
       */
      @Test
      public void testUpdateBookNegative() {
          int bookId = 1;
          String updatedRequestBody = "{\"id\": 1, \"name\": \"Updated Book\", \"author\": \"Author B\", \"price\": 25.99}";
          given().auth().basic("admin", "password")
                  .contentType("application/json")
                  .body(updatedRequestBody)
                  .when().put("/books/" + bookId)
                  .then().log().all() // Logs the response
                  .statusCode(200)
                  .body("name", equalTo("Updated Book"))
                  .body("author", equalTo("Author B"))
                  .body("price", equalTo(25.99f));
      }


      //*4*//
      /**
       * Test to delete a book by ID.
       * Expected to PASS.
       */
      @Test
      public void testDeleteBookNegative() {
          int bookId = 1;
          given().auth().basic("admin", "password")
                  .contentType("application/json")
                  .when().delete("/books/" + bookId)
                  .then().log().all() // Logs the response
                  .statusCode(200);

          // Verify deletion
          given().auth().basic("admin", "password")
                  .when().get("/books/" + bookId)
                  .then().log().all() // Logs the response
                  .statusCode(404);
      }


      //*5*//
      /**
       * Test to verify that GET /books returns a list of books.
       * Expected to FAIL.
       */
      @Test
      public void testGetBooksNegative() {
          given().auth().basic("user", "password")
                  .contentType("application/json")
                  .when().get("/books")
                  .then().log().all() // Logs the response
                  .statusCode(200)
                  .body("page", greaterThanOrEqualTo(1))
                  .body("limit", greaterThanOrEqualTo(1))
                  .body("total", greaterThan(0))
                  .body("users", not(empty()));
      }


  }




