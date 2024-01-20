# RideShare

## TODO

- write readme
- fix postgresql deploy
- jwt set expiration, move key in environment variables
- doar dataview my rides (ca la uber, apare cursa canceled) sau si dataview cu bookings
- wireframes, redo requirements, flowcharts in excalidraw 
- change count(bc.booking_id) in queries to count(b.adults + b.children)
- bookings with approval
- classes and methods javadoc
- flowcharts
- rest exceptions handling
- validation
- rest documentation
- testing
- spring profiles
- jobs
- mails
- spring security
- actuator
- internationalization
- maps integration
- see fmi document
- remove hardcoded properties in jooq plugin pom.xml
- transactions
- cross-origin only from client not accessible to everyone

## Deployment

### Docker

docker run -it --network host -p 8080:8080 rideshare:0.0.1-SNAPSHOT
