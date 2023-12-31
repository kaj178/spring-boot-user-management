package com.kaj.rest.api.service.impl;

import com.kaj.rest.api.model.entities.User;
import com.kaj.rest.api.repository.UserRepository;
import com.kaj.rest.api.service.ExcelExpoter;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporterImpl implements ExcelExpoter {
    private UserRepository repository;

    public ExcelExporterImpl(UserRepository repository) {
        this.repository = repository;
    }

    public void writeExcel(HttpServletResponse response) throws IOException {
        List<User> userList = repository.findAll();
        Workbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet("User data");
        Row headerRow = sheet.createRow(0);

        // Set font and color for header's columns
        XSSFFont headerFont = (XSSFFont) workBook.createFont();
        CellStyle headerCellStyle = workBook.createCellStyle();

        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setFont(headerFont);

        String[] columns = {"ID", "Last name", "First name", "Date of birth", "Email", "Phone number", "Gender"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowIndex = 1;
        for (User user: userList) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(user.getUserID());
            row.createCell(1).setCellValue(user.getUserLastName());
            row.createCell(2).setCellValue(user.getUserFirstName());
            row.createCell(3).setCellValue(user.getUserBirthDay());
            row.createCell(4).setCellValue(user.getUserEmail());
            row.createCell(5).setCellValue(user.getUserPhone());
            row.createCell(6).setCellValue(user.getUserGender());
            rowIndex++;
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
        workBook.write(response.getOutputStream());
        workBook.close();
    }
}
