### How to build
Run [build.sh]() to build it as a docker image: seanpan/shorten-url-rest

### How to run
```
docker run --name rest -p 8080:8080 -d \
-e domain="http://localhost/api/r/" \
-e spring.datasource.url="jdbc:mysql://docker.for.mac.host.internal:3306/short_url?useUnicode=true&characterEncoding=utf-8" \
-e spring.datasource.password="asdfasf"  \
seanpan/shorten-url-rest:latest
``` 