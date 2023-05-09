# address-service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Environment Variables To Define
A few environment variables need to be defined in order to run the application.
- DB_USER: Username for database
- DB_PWD: Password for user
- DB_URL: Url for database

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/address-service-1.0.0-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Creating a docker image containing native executable

You can create a native executable using:
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

You can create a docker image using:
```shell script
docker build -f src/main/docker/Dockerfile.native-micro -t quarkus/address-service .
```

## Creating native executable to deploy to AWS

To deploy a native executable, you must build it with GraalVM:
```shell script
./mvnw install -Dnative -DskipTests -Dquarkus.native.container-build=true
```

You can then test the executable locally with sam local:
```shell script
sam local start-api --template target/sam.native.yaml.
```

To deploy to AWS Lambda:
```shell script
sam deploy -t target/sam.native.yaml -g
```

## Related Guides
- Containers https://quarkus.io/guides/building-native-image#creating-a-container
- AWS Lambda Gateway REST API ([guide](https://quarkus.io/guides/amazon-lambda-http)): Build/Run an API Gateway REST API with Lambda integration

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
