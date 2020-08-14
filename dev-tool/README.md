# Dev-Tool

## Docker DeskTop

・バージョン：2.3.0.4

| Services | port | user   | password | DataBase |
| -------- | ---- | ------ | -------- | -------- |
| Postgres | 5432 | docker | docker   | study    |
| MySQL    | 3306 | docker | docker   | study    |
| Redis    | 6379 | -      | -        | -        |



## Postgres

 ・バージョン：postgres:11

## MySQL

・バージョン：mysql:5.7.23

## redis

・バージョン：redis 3.x

https://hub.docker.com/r/library/redis/tags/](https://hub.docker.com/r/library/redis/tags/



## Docker Command

##### DockerでDB作成及びデータ初期化

```shell
cd dev-tool
docker-compose -f docker-compose.yml up -d
```

##### コンテナ停止

```
docker-compose stop
```

##### サービス削除

```
docker-compose down
```

