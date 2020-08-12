## Java3.0 Study


## 環境構築
### DockerでDB作成及びデータ初期化
 ```shell script
 cd dev-tool
 docker-compose -f docker-compose.yml up -d
 ```

## 目録

### ・hystrix-demo 

　　Spring Cloud Hystrix 

　　・リクエストを合併 ← 性能改善

　　・マイクロサービス API通信遮断