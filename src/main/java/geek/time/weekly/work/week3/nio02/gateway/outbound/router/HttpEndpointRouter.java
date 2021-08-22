package geek.time.weekly.work.week3.nio02.gateway.outbound.router;

import java.util.List;

public interface HttpEndpointRouter {
    String route(List<String> endpoints);
}
