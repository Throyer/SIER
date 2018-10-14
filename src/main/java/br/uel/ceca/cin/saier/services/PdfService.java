package br.uel.ceca.cin.saier.services;

import br.uel.ceca.cin.saier.persistence.models.Edificio;
import com.itextpdf.text.BaseColor;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PdfService {

    public ByteArrayInputStream getPDF(List<Edificio> edificios) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(6);

            table.setWidths(new int[]{15, 12, 18, 14, 14, 12});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Nome", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Andares", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Construção", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Bairro", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Rua", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Numero", headFont));
            hcell.setBackgroundColor(BaseColor.GRAY);
            table.addCell(hcell);

            for (Edificio edificio : edificios) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(edificio.getNome()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(edificio.getNumeroAndares() + ""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                String dataConstrucao = "não registrado";
                if (edificio.getDataConstrucao() != null) {
                    dataConstrucao = formatarData(edificio.getDataConstrucao().getTime());
                }
                
                cell = new PdfPCell(new Phrase((dataConstrucao)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(edificio.getCep().getBairro()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(edificio.getCep().getRua()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(edificio.getNumero() + ""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(PdfService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    /**
     * Recebe um Date e retorna uma string.
     *
     * @param dateData
     * @return String no formato dd/MM/yyyy
     */
    private String formatarData(Date dateData) {
        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(dateData);
    }

}
