# Lambda Function 
This exercise is to create *AWS Lambda that* can return the Mean, Median and Mode of a series of numbers. AWS API Gateway 
integration is done our Stats-functions lambda. Lambda gets triggered by HTTP API call to "/compute" route.

## Initial Code setup

### for stats-functions module
```
mvn archetype:generate \
-DgroupId=net.gaggs.assess \
-DartifactId=stats-functions \
-DarchetypeArtifactId=maven-archetype-quickstart \
-DinteractiveMode=false
```
### for AWS infra module
In infra folder:

```
cdk init sample-app --language java
```

## Unit Tests
for lambda App:
```
cd stats-function
mvn clean test
```

for lambda & Http API infra:
```
cd infra
mvn clean test
```
** Please follow the Readme in "infra" folder for more details

## Build & Deploy
for lambda App:
```
cd stats-function
mvn clean install -DskipTests=true
```

for lambda & Http API infra:
```
cd infra
And follow the Readme to execute maven and cdk step 
```
[Here is Infra Readme](infra/README.md)

## Test
* To Test the Mean, Median and Mode function, following calls can be made:
```
======================
MEAN function Request:
======================
curl -X POST https://7xdr2vmlg2.execute-api.us-east-1.amazonaws.com/compute \
   -H 'Content-Type: application/json' \
   -d '{"statsType":"MEAN","dataset":[2,3,4]}'

MEAN function Response:
---------
{ "value": 3.0 }


======================
MEDIAN function Request:
======================
curl -X POST https://7xdr2vmlg2.execute-api.us-east-1.amazonaws.com/compute \
   -H 'Content-Type: application/json' \
   -d '{"statsType":"MEDIAN","dataset":[10,9,4,22,30,11]}'

MEDIAN function Response:
---------
{ "value": 10.5 }

======================
MODE function Request:
======================
curl -X POST https://7xdr2vmlg2.execute-api.us-east-1.amazonaws.com/compute \
   -H 'Content-Type: application/json' \
   -d '{"statsType":"MODE","dataset":[1,2,3,4,2]}'

MODE function Response:
---------
{ "value": 2.0 }
   
======================
BAD function Request:
======================
curl -X POST https://7xdr2vmlg2.execute-api.us-east-1.amazonaws.com/compute \
   -H 'Content-Type: application/json' \
   -d '{"statsType":"AVERAGE","dataset":[1,2,3,4,2]}'

ERROR function Response:
---------
{ "Error": Unsupported Function: AVERAGE Supported functions are [MEAN, MODE, MEDIAN] }

```

OR,

* We can create test "APIGateway ProxyRequest Event" for our lambda in AWS Console with following http body:
```
{"statsType":"MODE","dataset":[1,2,3,4,2]}
```
