package com.kaj.rest.api.service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public interface ExcelExpoter {
    public void writeExcel(HttpServletResponse response) throws IOException;
}
