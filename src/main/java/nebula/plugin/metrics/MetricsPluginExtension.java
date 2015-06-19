/*
 * Copyright 2015 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package nebula.plugin.metrics;

import ch.qos.logback.classic.Level;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Nebula build metrics plugin extension.
 *
 * @author Danny Thomas
 */
public class MetricsPluginExtension {
    /**
     * The name used when adding this extension to the extension container.
     */
    public static final String METRICS_EXTENSION_NAME = "metrics";
    private static final String INDEX_PREFIX = "build-metrics-";
    public static final String DEFAULT_INDEX_NAME = INDEX_PREFIX + "default";
    private static final Level DEFAULT_LOG_LEVEL = Level.WARN;

    private String hostname = "localhost";
    private int port = 9300;
    private String clusterName = "elasticsearch";
    private String indexName = DEFAULT_INDEX_NAME;
    private Level logLevel = DEFAULT_LOG_LEVEL;
    private DispatcherType dispatcherType = DispatcherType.ES_HTTP;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = checkNotNull(hostname);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = checkNotNull(clusterName);
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = INDEX_PREFIX + checkNotNull(indexName);
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = Level.toLevel(checkNotNull(logLevel), DEFAULT_LOG_LEVEL);
    }

    public DispatcherType getDispatcherType() {
        return dispatcherType;
    }

    public void setDispatcherType(String dispatcherType) {
        this.dispatcherType = DispatcherType.valueOf(dispatcherType.toUpperCase());
    }

    public enum DispatcherType {
        ES_CLIENT,
        ES_HTTP
    }
}
