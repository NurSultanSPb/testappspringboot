package kz.springboot.springbootdemoo.util;

import kz.springboot.springbootdemoo.entities.MeridianOptions;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<MeridianOptions> optionsList;

    public ExcelExporter(List<MeridianOptions> optionsList) {
        this.optionsList = optionsList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Результаты измерений");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Страна", style);
        createCell(row, 1, "№ орудия", style);
        createCell(row, 2, "Координата х орудия", style);
        createCell(row, 3, "Разность х (см)", style);
        createCell(row, 4, "Координата у орудия", style);
        createCell(row, 5, "Разность у (см)", style);
        createCell(row, 6, "Цель №", style);
        createCell(row, 7, "Дирекционный угол орудие-первая цель", style);
        createCell(row, 8, "Разность дирекционного угла (в секундах)", style);
        createCell(row,9, "Расстояние орудие-первая цель", style);
        createCell(row,10, "Разность расстояния (см)", style);
        createCell(row,11, "Цель №", style);
        createCell(row,12, "Дирекционный угол орудие-первая цель", style);
        createCell(row,13, "Разность дирекционного угла (в секундах)", style);
        createCell(row, 14, "Расстояние орудие-вторая цель", style);
        createCell(row,15, "Разность расстояния (см)", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
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

        for (MeridianOptions option : optionsList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            //Наполнение строк данными конкретного option
            createCell(row, columnCount++, option.getCountry(), style);
            createCell(row, columnCount++, option.getNumberOfGun(), style);
            createCell(row, columnCount++, option.getX(), style);
            createCell(row, columnCount++, option.getDeltaX(), style);
            createCell(row, columnCount++, option.getY(), style);
            createCell(row, columnCount++, option.getDeltaY(), style);
            createCell(row, columnCount++, option.getNumberOfTargetOne(), style);
            createCell(row, columnCount++, option.getAzimuthOne(), style);
            createCell(row, columnCount++, option.getDeltaAzimuthOne(), style);
            createCell(row, columnCount++, option.getDistanceOne(), style);
            createCell(row, columnCount++, option.getDeltaDistanceOne(), style);
            createCell(row, columnCount++, option.getNumberOfTargetTwo(), style);
            createCell(row, columnCount++, option.getAzimuthTwo(), style);
            createCell(row, columnCount++, option.getDeltaAzimuthTwo(), style);
            createCell(row, columnCount++, option.getDistanceTwo(), style);
            createCell(row, columnCount++, option.getDeltaDistanceTwo(), style);

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

