# quarkus-oidc-example


## Build

```shell
mvn clean install -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```


## Run

```shell
cd deployment
docker compose up
```

```shell
export access_token=$(\
    curl --insecure -X POST http://localhost:8081/realms/quarkus/protocol/openid-connect/token \
    --user backend-service:secret \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=alice&password=alice&grant_type=password&scope=openid' | jq --raw-output '.access_token' \
)
```

```shell
curl -H "Authorization: Bearer $access_token" -v GET http://localhost:8080/api/admin
curl -H "Authorization: Bearer $access_token" -v GET http://localhost:8080/api/user
```


- [Swagger Live](http://localhost:8080/swagger-ui/)
- [OpenAPI Schema](http://localhost:8080/openapi/)
- [Metrics](http://localhost:9000/metrics/)
- [Health](http://localhost:9000/health/)
- [Health Liveness](http://localhost:9000/health/live/)
