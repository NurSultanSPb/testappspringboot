package kz.springboot.springbootdemoo.utils;


import kz.springboot.springbootdemoo.entities.Officers;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Officers> officersList;

    public ExcelExporter(List<Officers> officersList) {
        this.officersList = officersList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Сотрудники");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "№ п/п", style);
        createCell(row, 1, "ФИО", style);
        createCell(row, 2, "Дата рождения", style);
        createCell(row, 3, "Должность", style);
        createCell(row, 4, "Звание", style);
        createCell(row, 5, "Подразделение", style);
        createCell(row, 6, "Дата заключения контракта", style);
        createCell(row, 7, "Срок контракта (в годах)", style);
        createCell(row,8, "Дата завершения контракта", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Officers officer : officersList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            Date dateOfBirthDate = null;
            if (officer.getDateOfBirth() != null) {
                dateOfBirthDate = java.util.Date.from(officer.getDateOfBirth().atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
            }

            Date dateOfSignDate = null;
            if (officer.getDateOfSign() != null) {
                dateOfSignDate = java.util.Date.from(officer.getDateOfSign().atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

            createCell(row, columnCount++, officer.getPersonalNumber(), style);
            createCell(row, columnCount++, officer.getSurname() + " " + officer.getName() + " " + officer.getMiddleName(), style);
            createCell(row, columnCount++, dateFormat.format(dateOfBirthDate), style);
            createCell(row, columnCount++, officer.getPosition().getPositionName(), style);
            createCell(row, columnCount++, officer.getRank().getRankName(), style);
            createCell(row, columnCount++, officer.getDepartment().getDepartmentName(), style);
            createCell(row, columnCount++, dateFormat.format(dateOfSignDate), style);
            createCell(row, columnCount++, officer.getContractPeriod(), style);
            createCell(row, columnCount++, dateFormat.format(dateOfSignDate), style);



        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
