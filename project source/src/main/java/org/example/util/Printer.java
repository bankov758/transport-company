package org.example.util;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.entity.Client;
import org.example.entity.Order;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Printer {

    private static final String FILE_PATH = "E:\\Projects\\transport_company\\project source\\src\\main\\resources\\example.pdf";

    private static final String PRINTED_SUCCESSFULLY = "PDF created successfully at: " + FILE_PATH;

    public static <T> void printEntity(T entity) {
        try (PdfWriter writer = new PdfWriter(FILE_PATH)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    addEntityToDoc(entity, document);
                }
            }
            System.out.println(PRINTED_SUCCESSFULLY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void printEntities(List<T> entities) {
        try (PdfWriter writer = new PdfWriter(FILE_PATH)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    for (T entity : entities) {
                        addEntityToDoc(entity, document);
                    }
                }
            }
            System.out.println(PRINTED_SUCCESSFULLY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> void addEntityToDoc(T entity, Document document) {
        document.add(new Paragraph(entity.toString()
                .replace("{", "{ \n")
                .replace("'", "")
                .replace(",", "")
                .replace("=", ": ")
        ));
    }

    public static void printOrders(List<Order> orders) {
        try (PdfWriter writer = new PdfWriter(FILE_PATH)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    for (Order order : orders) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        Paragraph title = new Paragraph("Order #" + order.getId())
                                .setFontSize(18)
                                .setBold()
                                .setTextAlignment(TextAlignment.CENTER);
                        document.add(title);
                        document.add(new Paragraph("Arrival point: " + order.getArrivalPoint()));
                        document.add(new Paragraph("Departure point: " + order.getDeparturePoint()));
                        document.add(new Paragraph("Start time: " + order.getStartTime().format(formatter)));
                        document.add(new Paragraph("End time: " + order.getEndTime().format(formatter)));
                        document.add(new Paragraph("Price: " + order.getPrice()));
                        document.add(new Paragraph("Driver: " + order.getDriver().getFirstName() + " " + order.getDriver().getLastName()));
                        document.add(new Paragraph("Company: " + order.getCompany().getName()));
                        document.add(new Paragraph("Clients: "));
                        for (Client client : order.getClients()) {
                            document.add(new Paragraph("Name: " + client.getFirstName() + " " + client.getLastName()).setFirstLineIndent(20));
                        }
                        document.add(new Paragraph("---------------------------------------------------------------"  +
                                "---------------------------------------------------------------"));
                    }
                }
            }
            System.out.println(PRINTED_SUCCESSFULLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
