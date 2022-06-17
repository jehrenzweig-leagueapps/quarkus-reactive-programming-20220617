# quarkus-reactive-programming-20220617
Quarkus playground application with reactive APIs using RESTEasy Reactive

----

When this Quarkus application starts up, it:
1. Exposes a Swagger UI page @ http://localhost:7332/q/swagger-ui/
   - Use this page to view all available API endpoints.
2. Creates two MySQL database instances, running on different ports.
   - By default, the port each database is created on is dynamically chosen at runtime.
   - You will need to check the console log for ports the app creates the databases on.
   - Edit `/src/main/resources/application.properties` if you want to use static database ports.

----

The problem I'm running into is when the following API endpoint is called:
   - http://localhost:7332/potato-type/name/{name}/reactive

For example, if I submit this `GET` request:
```
http://localhost:7332/potato-type/name/Adirondack Blue
```

The `PotatoTypeReadOnlyRepository` class' [getPotatoTypeByName()](https://github.com/jehrenzweig-leagueapps/quarkus-reactive-programming-20220617/blob/main/src/main/kotlin/org/example/dataaccess/PotatoTypeReadOnlyRepository.kt#L37-L71) method successfully submits a SQL query & 
returns a `PotatoType` object, which is then returned as the API response body.

However, if I submit this `GET` request:
```
http://localhost:7332/potato-type/name/Adirondack Blue/reactive
```

The API endpoint returns a HTTP 500 response, because the `PotatoTypeReadOnlyRepository` class' [getPotatoTypeByNameReactive()](https://github.com/jehrenzweig-leagueapps/quarkus-reactive-programming-20220617/blob/main/src/main/kotlin/org/example/dataaccess/PotatoTypeReadOnlyRepository.kt#L73-L97) 
method code is written incorrectly.  Which is 100% my fault -- but I can't figure out what the "_correct_" 
reactive code should look like.