##RUN Local 
```
docker build -t playlistservice2:dev .

docker network create --driver bridge home

docker run --name playlistDBInstance --network home  -e POSTGRES_DB=playlistdb -e POSTGRES_PASSWORD=open -p 5434:5432 -d postgres

docker run --name playlistAppInstance --network home  -e SPRING_PROFILES_ACTIVE=docker -e PORT=8080 -p 9000:8080 -d playlistservice2:dev

```