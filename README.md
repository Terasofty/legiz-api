## Spring API + JWT Auth Template

Starter REST API with JWT Security

### Dependencies
* Java OpenJDK 17
* Postgres 12.9+

This API uses environment variables for local development, same for staging and deployment phase

**This must be configured before running the app**

| Name                  | Description                                   |
|-----------------------|-----------------------------------------------|
| **SPRING_ENV**        | This sets the current environment `dev, prod` |
| **DATABASE_URL**      | Database URL for connection                   |
| **DATABASE_USERNAME** | Database user eg. `postgres`                  |
| **DATABASE_PASSWORD** | Secret for database access                    |
 | **JWT_SECRET**        | Secret for jwt encoding                       |


**For a secret generation run**
```shell
openssl rand -hex 32
```

### Usage

Make a `POST` request to `/login` with this JSON format
```json
{
 "username": ...,
 "password:" ...
}
```
You will receive a JWT Acess Token