### How to setup
1. Prepare database:
    1. Install my-sql
        e.g. 
        ```
        docker run --name mysql -e MYSQL_DATABASE=short_url  -e MYSQL_ROOT_PASSWORD=asdfdf -p 3306:3306 -d mysql:8.0.21
        ```
    1. Init database by executing [db-init.sql](url-shortener-rest/doc/db-init.sql)
1. Configure [docker-compose.yaml]() to make sure the following 2 fields are correct:  
    1. spring.datasource.url
    1. spring.datasource.password
    1. domain
1. Start application

        ```
        ./run.sh
        ```
        
1. Open your browser and try accessing http://localhost
    ```
    open http://localhost
    ```
