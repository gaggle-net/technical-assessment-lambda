package org.gaggle.challenge;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeanMedianModeCalculator implements RequestHandler<S3Event, String> {
    private static final String REGION = "us-east-1";

    AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        S3EventNotification.S3Entity s3Instance = s3Event.getRecords().get(0).getS3();
        String bucketName = s3Instance.getBucket().getName();
        String filename = s3Instance.getObject().getKey();

        LambdaLogger logger = context.getLogger();
        logger.log("BucketName: " + bucketName);
        logger.log("fileName: " + filename);
        try {
            S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, filename));
            InputStream objectData = object.getObjectContent();
            String string = IOUtils.toString(objectData, StandardCharsets.UTF_8);
            logger.log( "file content ::: " + string);
            // Process the objectData stream.
            byte[] content = objectData.readAllBytes();
            objectData.close();
            InputStream targetStream = new ByteArrayInputStream(content);
           // List<Integer> newList = targetStream.read();
//                    .map(s -> Integer.parseInt(s))
//                    .collect(Collectors.toList());


        } catch (IOException ioException) {
            return "Error while reading file from S3 ::: " + ioException.getMessage();
        }
        return "Successfully read file from s3 bucket";
    }

    /**
     * mean is just average of all numbers
     * algorithm = sumOfAllNum/numberInSet
     */
    public static Integer calculateMean(List<Integer> numbers) {
        int sumOfAllNum = 0;
        for (int num : numbers) {
            sumOfAllNum += num;

        }
        return sumOfAllNum / numbers.size();

    }

    /**
     * median is the value of the SORTED data set which identifies the middle of the set
     * algorithm = sort set - determine if set is even or odd - you will have 2 numbers if the data set is even
     * median will be middle number (or in the case of even - 2 middle numbers averaged together)
     */
    public static Double calculateMedian(List<Integer> numbers) {
        Collections.sort(numbers);
        if (numbers.size() % 2 == 1)
            return numbers.get((numbers.size() / 2)) * 1.0;
        return 0.5 * (numbers.get(numbers.size() / 2) + numbers.get(numbers.size() / 2 - 1));
    }

    /**
     * mode is the value of the SORTED data set which occurs most often
     * algorithm = count occurrences of each number in set - find the most frequent
     * i.e. {1,2,3,4,1,2,-1,2,7,2} - mode should be 2
     */
    public static Integer calculateMode(List<Integer> numbers) {
        Map<Integer, Integer> map
                = new HashMap<>();

        // To store the maximum frequency
        int max = 0;

        // To store the element with
        // the maximum frequency
        int mode = 0;

        for (int number : numbers) {

            // Updates the frequency of
            // that element
            map.put(number, map.getOrDefault(number, 0) + 1);

            // Checks for maximum Number of occurrence
            if (map.get(number) >= max) {

                // Updates the maximum frequency
                max = map.get(number);

                // Updates the Mode
                mode = number;
            }
        }
        return mode;
    }
    //  original main method - used to get mean, median and mode coding completed and tested
    // I left this so you could see how I worked it out... get coding done - then figure out how to AWS...
    //    public static void main(String[] args) {
    //        int[] data = {1, 2, 3, 4, 3, 5, 6, 10, 5, 2, 2};
    //        Integer mean = calculateMean((List<Integer>) Arrays.stream(data));
    //        Double median = calculateMedian((List<Integer>) Arrays.stream(data));
    //        Integer mode = calculateMode((List<Integer>) Arrays.stream(data));
    //        System.out.println("Mean: " + mean +
    //                " Median: " + median +
    //                " Mode: " + mode);

    //    }

    // again - this is for completeness... can be deleted, but I used this to test out the functionality
//    @Override
//    public Object handleRequest(Object input, com.amazonaws.services.lambda.runtime.Context context) {
//        int [] data = Arrays.stream((int[]) input).toArray();
//
//        StringBuilder s = new StringBuilder("Received message: " + data);
//
//            Integer mean = calculateMean((List<Integer>) Arrays.stream(data));
//            Double median = calculateMedian((List<Integer>) Arrays.stream(data));
//            Integer mode = calculateMode((List<Integer>) Arrays.stream(data));
//
//            return "Mean: " + mean +
//                    " Median: " + median +

//                    " Mode: " + mode;

//        }
//    public void handlerStream(InputStream inputStream, OutputStream outputStream)
//            throws IOException {
//        Integer mean = calculateMean((List<Integer>) inputStream);
//        Double median = calculateMedian((List<Integer>) inputStream);
//        Integer mode = calculateMode((List<Integer>) inputStream);
//        while((inputStream.read()) != -1)
//        {
//            outputStream.write(Integer.parseInt("Mean: " + mean +
//                    " Median: " + median +
//                    " Mode: " + mode));
//        }

//    }
}