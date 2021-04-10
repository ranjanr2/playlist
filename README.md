##RUN Local 
```
docker run --name playlist-postgres3 -e POSTGRES_DB=playlistdb -e POSTGRES_PASSWORD=open -p 5434:5432 -d postgres
```
