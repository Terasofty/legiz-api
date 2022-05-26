## Legiz Api with Docker

Starter REST API with JWT Security

## Conventional Build
### Dependencies
* Java OpenJDK 17
* Postgres 12.9+

This API uses environment variables for local development, same for staging and deployment phase

**This must be configured before running the app**

| Name             | Description                                                                                               |
|------------------|-----------------------------------------------------------------------------------------------------------|
| **SPRING_ENV**   | This sets the current environment `dev, prod`                                                             |
| **DATABASE_URL** | Database URL for connection eg: `postgresql://<host>:<port>/<dbname>?user=<username>&password=<password>` |
 | **JWT_SECRET**   | Secret for jwt encoding                                                                                   |


## Docker Build

Just run `docker compose up`

**For a secret generation run**
```shell
openssl rand -hex 32
```

### Usage

Make a `POST` request to `/login` with this JSON format
```json
{
 "username": "hyper",
 "password:": "12345"
}
```
You will receive a JWT Acess Token
