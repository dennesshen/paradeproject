package com.parade.paradeproject.util.dataSendModel;

import java.util.LinkedHashMap;

/*
* @author  Christine Hsieh
*/

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = DataSendModelSerializer.class)
public class DataSendModel {

    private Map<String, Object> maindata;

    private Map<String, List<DataSendModelSecond>> detaildata;

    public DataSendModel(Map<String, Object> maindata) {
        this.maindata = maindata;
    }

    public DataSendModel(Map<String, Object> maindata, List<DataSendModelSecond> singleDetailData) {
        this.maindata = maindata;
        Map<String, List<DataSendModelSecond>> detaildata = new LinkedHashMap<>();
        detaildata.put("detaildata", singleDetailData);
        this.detaildata = detaildata;
    }
    
   

}
