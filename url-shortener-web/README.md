### How to build
Run [build.sh]() to build it as a docker image: seanpan/shorten-url-web

### How to run
```
docker run --name shorten-url-web -d -p 80:8080 seanpan/shorten-url-web
``` 
Verify by open [http://localhost]() to go to the short url generation page. 
p.s. Please make sure url-shortener-rest is started. It is recommended to start shorten-url-web by [run.sh](../run.sh)
