# quarkus-oidc-example


## Build

```shell
mvn clean install -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```


## Run

```shell
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

```shell
http://localhost:9000/health
http://localhost:9000/health/live
http://localhost:9000/metrics
```
