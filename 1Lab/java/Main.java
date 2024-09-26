import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        var fcgiInterface = new FCGIInterface();

        while (fcgiInterface.FCGIaccept() >= 0) {

            final String HTTP_RESPONSE = """
            HTTP/1.1 200 OK
            Content-Type: application/json
            Content-Length: %d
            
            %s
            """;
            final String HTTP_ERROR = """
            HTTP/1.1 400 Bad Request
            Content-Type: application/json
            Content-Length: %d
            
            %s
            """;
            final String RESULT_JSON = """
            {
                "hit": "%s",
                "exec_time": "%s"
            }
            """;
            final String ERROR_JSON = """
            {
                "reason": "%s"
            }
            """;

            var query = System.getProperties().getProperty("QUERY_STRING");

            var request = readRequestBody(query);
            Instant startTime = Instant.now();
            if (Validator.validate(request.get("x_field"),request.get("y_field"),request.get("R_field"))){
                String result = HitChecker.calculatePoint(Double.parseDouble(request.get("x_field")), Double.parseDouble(request.get("y_field")), Double.parseDouble(request.get("R_field")));

                Instant endTime = Instant.now();
                String executionTime = String.valueOf((float) (Duration.between(startTime, endTime).toNanos() / Math.pow(10, 9)));

                var json = String.format(RESULT_JSON, result, executionTime);
                var response = String.format(HTTP_RESPONSE, json.getBytes(StandardCharsets.UTF_8).length + 2, json);
                System.out.println(response);
            }else{
                var json = String.format(ERROR_JSON, "400 Bad Request");
                var response = String.format(HTTP_RESPONSE, json.getBytes(StandardCharsets.UTF_8).length + 2, json);
                System.out.println(response);

            }

        }

    }
    private static Map<String, String> readRequestBody(String request) {
        var queryPairs = new HashMap<String, String>();
        var pairs = request.split("&");
        for (var pair : pairs) {
            var idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8), URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8));
        }
        return queryPairs;
    }
}