package utilities;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PDFParser {
	
	public String pdfParser (String pdfFilePath) {
		
		String pdfContents = "";
		try {
			
			PDDocument pdfFile = PDDocument.load(new File(pdfFilePath));
			pdfFile.getClass();
			
			if (!pdfFile.isEncrypted()) {
				
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper textStripper = new PDFTextStripper();

                String pdfFileInText = textStripper.getText(pdfFile);
                
                //Splitting by blank or white spaces to extract lines
                String lines[] = pdfFileInText.split("\\r\\n\\r\\n");
                for (String line : lines) {

                    pdfContents += line;
                }

			}
			else {
				
				System.out.println("The given pdf file is encrypted and hence could not be parsed, please make sure to give correct access rights !");
			}
		}
		catch (Exception e) {
			
			System.out.println("An error occurred while parsing PDF file !");
			e.printStackTrace();
		}
		
		return pdfContents.trim();
	}

}
