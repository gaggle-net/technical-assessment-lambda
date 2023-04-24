package com.myorg;

import static java.util.Collections.singletonList;
import static software.amazon.awscdk.BundlingOutput.ARCHIVED;

import java.util.Arrays;
import java.util.List;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.CfnOutputProps;
import software.amazon.awscdk.services.apigatewayv2.alpha.AddRoutesOptions;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpApi;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpMethod;
import software.amazon.awscdk.services.apigatewayv2.alpha.PayloadFormatVersion;
import software.amazon.awscdk.services.apigatewayv2.integrations.alpha.HttpLambdaIntegration;
import software.amazon.awscdk.services.apigatewayv2.integrations.alpha.HttpLambdaIntegrationProps;
import software.constructs.Construct;
import software.amazon.awscdk.BundlingOptions;
import software.amazon.awscdk.DockerVolume;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.FunctionProps;
import software.amazon.awscdk.services.lambda.Runtime;
import software.amazon.awscdk.services.logs.RetentionDays;
import software.amazon.awscdk.services.s3.assets.AssetOptions;

public class InfraStack extends Stack {
    public InfraStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public InfraStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        List<String> lambdaFunctionPackagingInstructions = Arrays.asList(
                "/bin/sh",
                "-c",
                "cd stats-functions " +
                        "&& mvn clean install "
                        + "&& cp /asset-input/stats-functions/target/statsfunctions.jar /asset-output/"
        );

        BundlingOptions.Builder builderOptions = BundlingOptions.builder()
                .command(lambdaFunctionPackagingInstructions)
                .image(Runtime.JAVA_8.getBundlingImage())
                .volumes(singletonList(
                        // Mount local .m2 repo to avoid download all the dependencies again inside the container
                        DockerVolume.builder()
                                .hostPath(System.getProperty("user.home") + "/.m2/")
                                .containerPath("/root/.m2/")
                                .build()
                ))
                .user("root")
                .outputType(ARCHIVED);

        Function statsFunctionLambda = new Function(this, "lambda", FunctionProps.builder()
                .functionName("stats-functions")
                .runtime(Runtime.JAVA_8)
                .code(Code.fromAsset("../", AssetOptions.builder().bundling(
                        builderOptions.command(
                                lambdaFunctionPackagingInstructions
                        ).build()
                        ).build()
                ))
                .handler("net.gaggs.assess.App")
                .memorySize(1024)
                .timeout(Duration.seconds(10))
                .logRetention(RetentionDays.ONE_DAY)
                .build());

        HttpApi httpApi = new HttpApi(this, "http-api");

        HttpLambdaIntegration httpLambdaIntegration = new HttpLambdaIntegration(
                "this",
                statsFunctionLambda,
                HttpLambdaIntegrationProps.builder()
                        .payloadFormatVersion(PayloadFormatVersion.VERSION_2_0)
                        .build()
        );

        httpApi.addRoutes(AddRoutesOptions.builder()
                .path("/compute")
                .methods(singletonList(HttpMethod.POST))
                .integration(httpLambdaIntegration)
                .build()
        );

        new CfnOutput(this, "http-api-desc", CfnOutputProps.builder()
                .description("HTTP API URL")
                .value(httpApi.getApiEndpoint())
                .build());

    }
}
