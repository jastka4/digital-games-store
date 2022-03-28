# Digital Games Store

## Usage
1. Install Docker and run it

2. Clone the repo and go to the project directory

   ```
   $ git clone https://github.com/jastka4/digital-games-store.git
   $ cd digital-games-store
   ```

3. Run Docker Compose:
    ```
    $ docker-compose up
    ```

3. Wait a second for Docker to finish setting up all the containers.

## Architecture

I was not able to prepare everything as I initially wanted due to the lack of time. In the image below you can see a very high-level design of what I was going for.

![High-level design](https://github.com/jastka4/digital-games-store/blob/assets/simplified-architecture-design.png)

Elements:
- **Web App** - would require some front-end work to implement a separate app where users and admins could interact with products; it would act as an interface to the whole system.
- **Storefront App** - allows users to browse, search and buy products.
- **Backoffice App** - allows making changes to the content of the website.
- **RabbitMQ** - queues messages from the Backoffice that contains changes made to products. Can also be used to queue new orders from the Storefront. They can be read from the queue by another microservice or the Backoffice app itself (chosen over Kafka because it is very reliable - we can trade off performance with reliability in this case; in the future, it can easily be used with multiple microservices; multiple servers can work as a cluster).
- **Elasticsearch** - used as a search and analytics engine (chosen because it easily scales horizontally; I've worked with Solr before, and it was kind of hard to configure it properly, so I've decided to try Elasticsearch instead).
- **Database (PostgreSQL)** - stores all products and users (chosen because it is a very stable database and is considered to be the best database engine for large amount of data).
- **Redis** - acts as a cache server for both Storefront and Backoffice apps. In conjunction with Spring Session, it can store user sessions that can easily be accessed by other machines. It makes sessions server agnostic and thanks to it, they can easily be transferred between servers when one machine is down or heavily utilized. Also, Redis can be used as Spring Boot cache and serve frequently requested items. 

Products have two catalogues: *"Staged"* and *"Online"*. All changes are made to the *"Staged"* catalogue products. When ready, admins can sync products and propagate the changes to the *"Online"* catalogue.

![Product catalogues](https://github.com/jastka4/digital-games-store/blob/assets/product-catalogues.png)

### Additional Notes
- All the above-mentioned elements can be run on multiple servers and/or as multiple Docker containers. They can be scaled vertically and horizontally. They would also require some load balancers to distribute the traffic across the servers.

- A good option would be to use Kubernetes with Helm. It would allow for a better and easier scalability. My journey with Kubernetes and Helm has just started, so I decided to not use it here as my knowledge is still lacking, and I would not be able to propose a good solution. 

- SQL databases do not scale horizontally well. One option to handle scaling better would be to use MySQL Cluster. The second option would be to use NoSQL database instead.
