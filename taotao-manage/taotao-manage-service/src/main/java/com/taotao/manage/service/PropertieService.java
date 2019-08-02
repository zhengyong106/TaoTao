package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertieService {
    @Value("${image.store.path}")
    public String IMAGE_STORE_PATH;
    @Value("${image.base.url}")
    public String IMAGE_BASE_URL;
}
