package org.example.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class GetApplicationResponse {
    private String total;
   private List<ApplicationData> data;
  // private ApplicationData data;
    private String requestId;

}
