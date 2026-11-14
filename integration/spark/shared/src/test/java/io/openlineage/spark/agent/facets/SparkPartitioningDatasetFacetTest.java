/*
/* Copyright 2018-2026 contributors to the OpenLineage project
/* SPDX-License-Identifier: Apache-2.0
*/

package io.openlineage.spark.agent.facets;

import static org.assertj.core.api.Assertions.assertThat;

import io.openlineage.spark.agent.Versions;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SparkPartitioningDatasetFacetTest {

  @Test
  void testFacetHoldsPartitionColumns() {
    SparkPartitioningDatasetFacet facet =
        new SparkPartitioningDatasetFacet(Arrays.asList("year", "month"));

    assertThat(facet.getPartitionColumns()).containsExactly("year", "month");
    assertThat(facet.get_producer()).isEqualTo(Versions.OPEN_LINEAGE_PRODUCER_URI);
    assertThat(facet.get_schemaURL().toString())
        .isEqualTo("https://openlineage.io/spec/facets/1-0-0/SparkPartitioningDatasetFacet.json");
    assertThat(facet.getAdditionalProperties()).isEmpty();
  }

  @Test
  void testPartitionColumnsAreCopied() {
    java.util.List<String> source = new java.util.ArrayList<>(Arrays.asList("a"));
    SparkPartitioningDatasetFacet facet = new SparkPartitioningDatasetFacet(source);
    source.add("b");

    assertThat(facet.getPartitionColumns()).containsExactly("a");
  }
}
