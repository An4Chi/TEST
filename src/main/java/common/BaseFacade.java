package common;

import common.exceptions.AutomationInterruptedException;
import common.exceptions.FacadeException;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Data
@Component
public class BaseFacade {
    private String baseUrl;
    private String token;
    String[] headers;
    protected HttpClient httpClient;
    private static final int CONNECTION_TIMEOUT = 15;
    private static final String THREAD_INTERRUPTED_LOG_ERROR = "Thread is waiting, sleeping or interrupted";
    @Resource
    private final BaseFacadeEntity baseFacadeConfiguration;
    @Resource
    private final ObjectMapperConfiguration objectMapperConfiguration;

    @Autowired
    public BaseFacade(BaseFacadeEntity baseFacadeConfiguration, ObjectMapperConfiguration objectMapperConfiguration) {
        System.out.println("HERE Base dacade");
        this.baseFacadeConfiguration = baseFacadeConfiguration;
        this.objectMapperConfiguration = objectMapperConfiguration;
        httpClient = getHttpClient();
        this.baseUrl = this.baseFacadeConfiguration.getBaseUrl();
        this.token = "";
        headers = new String[]
                {
                        "Content-Type", "application/json", "Authorization", "Token" + token
                };
    }

    private URI buildUri(String endPoint) {
        return URI.create(String.format("%s%s", baseUrl, endPoint));
    }

    public <T> T get(String endPoint, Class<T> entityClass) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(buildUri(endPoint))
                    .GET().headers(headers)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200 && response.statusCode() != 204) {
                throw new FacadeException(response.statusCode(), response.body());
            }
            return objectMapperConfiguration.getObjectMapper().readValue(response.body(), entityClass);

        } catch (ThreadDeath exception) {
            log.error(THREAD_INTERRUPTED_LOG_ERROR, exception);
            throw exception;
        } catch (InterruptedException | IOException e) {
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public HttpResponse<String> getEmptyBody(String endPoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(buildUri(endPoint))
                    .GET().headers(headers)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200 && response.statusCode() != 204) {
                throw new FacadeException(response.statusCode(), response.body());
            }
            return response;
        } catch (ThreadDeath exception) {
            log.error(THREAD_INTERRUPTED_LOG_ERROR, exception);
            throw exception;
        } catch (InterruptedException | IOException e) {
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public <T> T post(String endPoint, String body, Class<T> entityClass) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(buildUri(endPoint))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .headers(headers)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new FacadeException(response.statusCode(), response.body());
            }
            return objectMapperConfiguration.getObjectMapper().readValue(response.body(), entityClass);

        } catch (ThreadDeath exception) {
            log.error(THREAD_INTERRUPTED_LOG_ERROR, exception);
            throw exception;
        } catch (InterruptedException | IOException e) {
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public <T> T update(String endPoint, String body, Class<T> entityClass) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(buildUri(endPoint))
                    .PUT(HttpRequest.BodyPublishers.ofString(body))
                    .headers(headers)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new FacadeException(response.statusCode(), response.body());
            }
            return objectMapperConfiguration.getObjectMapper().readValue(response.body(), entityClass);
        } catch (ThreadDeath exception) {
            log.error(THREAD_INTERRUPTED_LOG_ERROR, exception);
            throw exception;
        } catch (InterruptedException | IOException e) {
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    public <T> T delete(String endPoint, Class<T> entityClass) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(buildUri(endPoint))
                    .DELETE().headers(headers)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new FacadeException(response.statusCode(), response.body());
            }
            return objectMapperConfiguration.getObjectMapper().readValue(response.body(), entityClass);

        } catch (ThreadDeath exception) {
            log.error(THREAD_INTERRUPTED_LOG_ERROR, exception);
            throw exception;
        } catch (InterruptedException | IOException e) {
            throw new AutomationInterruptedException(e.getMessage());
        }
    }

    private static HttpClient getHttpClient() {
        try {
            SSLContext sslContext = SSLContextBuilder
                    .create()
                    .loadTrustMaterial(null, new TrustAllStrategy())
                    .build();

            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .connectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT))
                    .build();
        } catch (Exception exception) {
            log.error("Something is Wrong!", exception);
            return null;
        }
    }

    public void setHeaders(Pair<String, String> header) {
        List<String> headerList = new ArrayList<>(Arrays.asList(headers));
        headerList.add(header.getKey());
        headerList.add(header.getValue());
        this.headers = headerList.toArray(new String[0]);
    }

    public String[] getHeaders() {
        return this.headers;
    }

    public void setTokenHeader(String tokenHeader) {
        this.setToken(tokenHeader);
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals("Token")) {
                headers[i] = "Token " + tokenHeader;
            }
        }
    }
}
