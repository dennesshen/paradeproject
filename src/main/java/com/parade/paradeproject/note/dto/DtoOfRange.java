package com.parade.paradeproject.note.dto;

import lombok.Data;

@Data
public class DtoOfRange {

    private String anchor_node_parent_identifier;

    private Long anchor_node_parent_index;

    private String anchor_node_identifier;

    private Integer anchor_node_type;

    private Long anchor_node_index;

    private Integer anchor_node_offset;

    private String focus_node_parent_identifier;

    private Long focus_node_parent_index;

    private String focus_node_identifier;

    private Integer focus_node_type;

    private Long focus_node_index;

    private Integer focus_node_offset;
    
}
