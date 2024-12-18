package utils;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import table.TableData;

import java.io.IOException;

public class MetricsHandler {

    private static final Counter hits = Counter.build()
            .name("graph_hits_total")
            .help("Total number of hits on the graph.")
            .register();

    private static final Counter misses = Counter.build()
            .name("graph_misses_total")
            .help("Total number of misses on the graph.")
            .register();

    private static final Counter requests = Counter.build()
            .name("request_total")
            .help("Total number of requests from site.")
            .register();

    // Инициализация HTTP сервера для экспорта метрик на порту 9091
    public static void initialize() throws IOException {
        try {
            new HTTPServer(8083); // Порт, по которому Prometheus будет запрашивать метрики
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void checkHitResult(TableData result){
        incrementRequests();
        if (result.isInside()){
            incrementHits();
            return;
        }
        incrementMisses();
    }

    // Метод для увеличения счётчика попаданий
    public static void incrementHits() {
        hits.inc();
    }

    // Метод для увеличения счётчика промахов
    public static void incrementMisses() {
        misses.inc();
    }

    // Метод для увеличения счётчика посещений
    public static void incrementRequests() {
        requests.inc();
    }
}
