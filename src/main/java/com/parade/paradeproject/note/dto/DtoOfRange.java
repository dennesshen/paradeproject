package com.parade.paradeproject.note.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoOfRange {

	
	@JsonProperty("anchor_node_parent_identifier")
    private String anchorNodeParentIdentifier;

    @JsonProperty("anchor_node_parent_index")
    private Long anchorNodeParentIndex;

    @JsonProperty("anchor_node_identifier")
    private String anchorNodeIdentifier;

    @JsonProperty("anchor_node_type")
    private Integer anchorNodeType;

    @JsonProperty("anchor_node_index")
    private Long anchorNodeIndex;

    @JsonProperty("anchor_node_offset")
    private Integer anchorNodeOffset;

    @JsonProperty("focus_node_parent_identifier")
    private String focusNodeParentIdentifier;

    @JsonProperty("focus_node_parent_index")
    private Long focusNodeParentIndex;

    @JsonProperty("focus_node_identifier")
    private String focusNodeIdentifier;

    @JsonProperty("focus_node_type")
    private Integer focusNodeType;

    @JsonProperty("focus_node_index")
    private Long focusNodeIndex;

    @JsonProperty("focus_node_offset")
    private Integer focusNodeOffset;
    
}
