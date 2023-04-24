package net.gaggs.assess;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import net.gaggs.assess.model.StatsFuncRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    static Logger logger = LoggerFactory.getLogger(App.class);
    static Gson gson = new Gson();

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        logger.info(input.getBody());

        String output = null;
        int httpResponseCode = 200;
        try {
            StatsFuncRequest bodyInput = gson.fromJson(input.getBody(), StatsFuncRequest.class);
            double result = StatsFunctionFactory.calculate(bodyInput.getStatsType(), bodyInput.getDataset());
            output = String.format("{ \"value\": %s }", result);
        } catch (Exception e) {
            httpResponseCode = 500;
            output = String.format("{ \"Error\": %s }", e.getMessage());
        }

        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Content-Type", "application/json");
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(responseHeaders);

        return response
                .withStatusCode(httpResponseCode)
                .withBody(output);
    }
}