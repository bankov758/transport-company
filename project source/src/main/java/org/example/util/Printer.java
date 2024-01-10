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

    private static int fileCounter = 0;

    private static final String FILE_PATH = "E:\\Projects\\transport_company\\project source\\src\\main\\resources\\example";

    private static final String FILE_EXT = ".pdf";

    private static final String PRINTED_SUCCESSFULLY = "PDF created successfully at: " + FILE_PATH;

    /**
     * Prints an entity to a PDF document.
     *
     * @param entity     The entity to be added to the document.
     */
    public static <T> void printEntity(T entity) {
        printEntity(null, entity, null);
    }

    /**
     * Prints a list of entities to a PDF document.
     *
     * @param entities   The list of entities to be printed.
     */
    public static <T> void printEntities(List<T> entities) {
        printEntities(null, entities, null);
    }

    /**
     * Prints an entity to a PDF document.
     *
     * @param textBefore Text to be added to the document before the entity. Can be null or empty.
     * @param entity     The entity to be added to the document.
     * @param textAfter  Text to be added to the document after the entity. Can be null or empty.
     */
    public static <T> void printEntity(String textBefore, T entity, String textAfter) {
        fileCounter++;
        try (PdfWriter writer = new PdfWriter(FILE_PATH + fileCounter + FILE_EXT)) {
            try (PdfDocument pdf = new PdfDocument(writer)) {
                try (Document document = new Document(pdf)) {
                    if (textBefore != null && !textBefore.isEmpty()) {
                        document.add(new Paragraph(textBefore));
                    }
                    addEntityToDoc(entity, document);
                    if (textAfter != null && !textAfter.isEmpty()) {
                        document.add(new Paragraph(textAfter));
                    }
                }
            }
            System.out.println(PRINTED_SUCCESSFULLY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints a list of entities to a PDF document.
     *
     * @param textBefore The text to be added before the entities in the document. Can be null or empty.
     * @param entities   The list of entities to be printed.
     * @param textAfter  The text to be added after the entities in the document. Can be null or empty.
     */
    public static <T> void printEntities(String textBefore, List<T> entities, String textAfter) {
        fileCounter++;
        if (entities != null && !entities.isEmpty()) {
            try (PdfWriter writer = new PdfWriter(FILE_PATH + fileCounter + FILE_EXT)) {
                try (PdfDocument pdf = new PdfDocument(writer)) {
                    try (Document document = new Document(pdf)) {
                        if (textBefore != null && !textBefore.isEmpty()) {
                            document.add(new Paragraph(textBefore));
                        }
                        for (T entity : entities) {
                            addEntityToDoc(entity, document);
                        }
                        if (textAfter != null && !textAfter.isEmpty()) {
                            document.add(new Paragraph(textAfter));
                        }
                    }
                }
                System.out.println(PRINTED_SUCCESSFULLY);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Prints a list of Orders to a PDF document.
     *
     * @param orders The list of Order objects to be printed.
     */
    public static void printOrders(List<Order> orders) {
        fileCounter++;
        try (PdfWriter writer = new PdfWriter(FILE_PATH + fileCounter + FILE_EXT)) {
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

    private static <T> void addEntityToDoc(T entity, Document document) {
        if (entity == null){
            return;
        }
        document.add(new Paragraph(entity.toString()
                .replace("{", "{ \n")
                .replace("'", "")
                .replace(",", "")
                .replace("=", ": ")
        ));
    }

}
