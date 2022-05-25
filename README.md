# gardena-smart-system
Java library for interacting with these Gardena/Husqvarna APIs:
* Authentication API
* GARDENA smart systems API

## Installation
Add the repository to your `pom.xml`:
```
<repositories>
  <repository>
    <id>github-jolsum-gardena-smart-systems</id>
    <url>https://maven.pkg.github.com/jolsum/gardena-smart-system</url>
  </repository>
</repositories>
```
And add the latest package as a dependency:
```
<dependency>
  <groupId>jolsum</groupId>
  <artifactId>gardena-smart-system</artifactId>
  <version>0.0.2</version>
</dependency>
```

## Usage
First you need to sign up and create account on the [Husqvarna Group Developer Portal](https://developer.husqvarnagroup.cloud/docs/getting-started), create an application and connect the APIs. Once you have a app id, username and password you are ready to go.

First use the Authentication API to generate a token:
```
PostOAuth2Response token = new AuthenticationAPI().getToken(appKey, username, password);
```

Then use this token to load data from the GARDENA smart systems API:
```
SmartSystemsAPI api = new SmartSystemsAPI(appKey, token);
LocationsResponse locations = api.getLocations();
```

The smart systems api offers pushing data over a websocket. Initially the current state of all devices in transferred, and then it continues to push updates as they happen. Provide a response observer to the method to handle the incoming data.
```
api.getWebSocketData(
    locationId,
    new WebSocketResponseObserver() {

      public void onMessage(DataItem message) {
        // Handle message
      }

      public void onError(Throwable t) {
        // Handle error
      }

      public void onConnect() {
        // Do something
      }

      public void onComplete(int code, String reason, boolean remote) {
        // Do something
      }
    });
```
The call to `getWebSocketData` will block untill the thread is interrupted or the connection is closed.
