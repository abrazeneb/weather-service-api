# Weather-service-api

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

[java 11][]: Java 11 is the minimum java version required.
 

Environments dev,swagger will be enabled by default when the project is started.
The swagger documentation can be accessed on
[http://[ip address]:[port]/swagger-ui/index.html]

```
Orika mapper
```
As a practice it would be a bad idea to directly expose the response model from third party systems, [https://api.openweathermap.org], in this case,
so that the response can be customized, enriched before it's returned to the client. As a result, I have used Orika mapper to map the response to another DTO.


```
OpenFeign Client
```

Spring Cloud OpenFeign has been used 