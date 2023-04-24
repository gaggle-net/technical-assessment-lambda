# CDK Java based infrastructure project!

The `cdk.json` file tells the CDK Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to build and run tests.

IMP: Run the following command in "stats-functions" folder before running mvn and cdk commands here

`mvn clean install`

## Build & Test
This will build the infrastructure project and Run Stack Unit test. This step setups the infra for CDK commands

`mvn package`

** Imp that you have installed node version > 16.0.1 or follow the version in ".node-version" file in the project.

## For AWS build and Deploy :: lambda-httpapi-stack
Execute steps in following order:
* First step up the AWS Access key, Secret key and region
```
export AWS_ACCESS_KEY_ID=xxxx
export AWS_SECRET_ACCESS_KEY=xxxxx
export AWS_DEFAULT_REGION=us-east-1
```
* `cdk synth`       emits the synthesized CloudFormation template

* `cdk diff`        compare deployed stack with current state & list what all resources getting created/modified/deleted

* `cdk deploy`      deploy this stack to your default AWS account/region


## Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation
 * `cdk destroy`     destroy the stack & related resources
