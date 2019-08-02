package com.taotao.manage.controller.bean;

import lombok.Data;

@Data
public class PictureUploadResult {
    
    private Integer error;
    
    private String url;
    
    private String width;
    
    private String height;
}
