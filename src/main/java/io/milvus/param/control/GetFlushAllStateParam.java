/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.milvus.param.control;

import io.milvus.exception.ParamException;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


/**
 * Parameters for <code>getFlushAllState</code> interface.
 */
@Getter
@ToString
public class GetFlushAllStateParam {
    private final String databaseName;
    private final long flushAllTs;

    private GetFlushAllStateParam(@NonNull Builder builder) {
        this.databaseName = builder.databaseName;
        this.flushAllTs = builder.flushAllTs;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder for {@link GetFlushAllStateParam} class.
     */
    public static final class Builder {
        private String databaseName;
        private Long flushAllTs;

        private Builder() {
        }

        /**
         * Sets the database name. database name can be nil.
         *
         * @param databaseName database name
         * @return <code>Builder</code>
         */
        public Builder withDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public Builder withFlushAllTs(@NonNull Long flushAllTs) {
            this.flushAllTs = flushAllTs;
            return this;
        }


        /**
         * Verifies parameters and creates a new {@link GetFlushAllStateParam} instance.
         *
         * @return {@link GetFlushAllStateParam}
         */
        public GetFlushAllStateParam build() throws ParamException {
            return new GetFlushAllStateParam(this);
        }
    }

}
