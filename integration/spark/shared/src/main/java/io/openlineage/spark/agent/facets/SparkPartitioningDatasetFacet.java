/*
/* Copyright 2018-2026 contributors to the OpenLineage project
/* SPDX-License-Identifier: Apache-2.0
*/

package io.openlineage.spark.agent.facets;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import io.openlineage.client.OpenLineage;
import io.openlineage.spark.agent.Versions;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

/**
 * Custom {@link OpenLineage.DatasetFacet} that carries the partition column names of a written
 * dataset (e.g. a partitioned file/HDFS table produced by an {@code
 * InsertIntoHadoopFsRelationCommand}).
 */
@Getter
public class SparkPartitioningDatasetFacet implements OpenLineage.DatasetFacet {

  @JsonAnyGetter private final URI _producer;
  @JsonAnyGetter private final URI _schemaURL;
  @JsonAnyGetter private final List<String> partitionColumns;
  @JsonAnySetter private final Map<String, Object> additionalProperties;

  public SparkPartitioningDatasetFacet(List<String> partitionColumns) {
    this.partitionColumns = new ArrayList<>(partitionColumns);
    this._producer = Versions.OPEN_LINEAGE_PRODUCER_URI;
    this._schemaURL =
        URI.create("https://openlineage.io/spec/facets/1-0-0/SparkPartitioningDatasetFacet.json");
    this.additionalProperties = new LinkedHashMap<>();
  }

  @Override
  public URI get_producer() {
    return this._producer;
  }

  @Override
  public URI get_schemaURL() {
    return this._schemaURL;
  }

  @JsonAnyGetter
  @Override
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }
}
