package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import java.util.List;

@Getter
public class GetApplicationResponse {
    private String total;

  // private List<ApplicationData> data;
   // private ApplicationData data;
   private Object data; // Может быть ApplicationData или List<ApplicationData>
    private String requestId;

    @JsonIgnore
    public ApplicationData getSingleData() {
        if (data instanceof ApplicationData) {
            return (ApplicationData) data;
        }
        return null;
    }

    @JsonIgnore
    public List<ApplicationData> getDataList() {
        if (data instanceof List) {
            return (List<ApplicationData>) data;
        }
        return null;
    }

    // Геттеры и сеттеры
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
