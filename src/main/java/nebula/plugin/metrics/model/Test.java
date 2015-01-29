/*
 * Copyright 2013-2015 the original author or authors.
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

package nebula.plugin.metrics.model;

import com.google.auto.value.AutoValue;
import org.joda.time.DateTime;

/**
 * Value object for tests.
 */
@AutoValue
public abstract class Test {
    public static Test create(String methodName, String className, String suiteName, Result result, long startTime, long elapsedTime) {
        return new AutoValue_Test(methodName, className, suiteName, result, new DateTime(startTime), elapsedTime);
    }

    public abstract String getMethodName();

    public abstract String getClassName();

    public abstract String getSuiteName();

    public abstract Result getResult();

    public abstract DateTime getStartTime();

    public abstract long getElapsedTime();
}
