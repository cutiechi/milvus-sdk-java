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
import io.milvus.param.ParamUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Parameters for <code>getMetric</code> interface.
 */
@Getter
@ToString
public class GetFlushStateParam {
    private final String databaseName;
    private final String collectionName;
    private final List<Long> segmentIDs;
    private final Long flushTs;

    private GetFlushStateParam(@NonNull Builder builder) {
        this.databaseName = builder.databaseName;
        this.collectionName = builder.collectionName;
        this.segmentIDs = builder.segmentIDs;
        this.flushTs = builder.flushTs;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Builder for {@link GetFlushStateParam} class.
     */
    public static final class Builder {
        private String databaseName;
        private String collectionName;
        private final List<Long> segmentIDs = new ArrayList<>(); // deprecated
        private Long flushTs = 0L;

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

        /**
         * Specify segments
         *
         * @param segmentIDs segments id list
         * @return <code>Builder</code>
         */
        @Deprecated
        public Builder withSegmentIDs(@NonNull List<Long> segmentIDs) {
            this.segmentIDs.addAll(segmentIDs);
            return this;
        }

        /**
         * Specify a segment
         *
         * @param segmentID segment id
         * @return <code>Builder</code>
         */
        @Deprecated
        public Builder addSegmentID(@NonNull Long segmentID) {
            this.segmentIDs.add(segmentID);
            return this;
        }

        /**
         * Sets the collection name. Collection name cannot be empty or null.
         *
         * @param collectionName collection name
         * @return <code>Builder</code>
         */
        public Builder withCollectionName(@NonNull String collectionName) {
            this.collectionName = collectionName;
            return this;
        }

        /**
         * Input a time stamp of a flush action, get its flush state
         *
         * @param flushTs a time stamp returned by the flush() response
         * @return <code>Builder</code>
         */
        public Builder withFlushTs(@NonNull Long flushTs) {
            this.flushTs = flushTs;
            return this;
        }

        /**
         * Verifies parameters and creates a new {@link GetFlushStateParam} instance.
         *
         * @return {@link GetFlushStateParam}
         */
        public GetFlushStateParam build() throws ParamException {
            ParamUtils.CheckNullEmptyString(collectionName, "Collection name");

            return new GetFlushStateParam(this);
        }
    }

}
