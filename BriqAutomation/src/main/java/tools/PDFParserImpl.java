package tools;

import utilities.PDFParser;

public class PDFParserImpl {

	public static void main(String[] args) {
		
		PDFParser pdfParser = new PDFParser();
		
		String pdfFilePath = "./resources/bids for transport.pdf";
		String pdfFileContents = pdfParser.pdfParser(pdfFilePath);
		System.out.println("Contents of pdf file as plain string :");
		System.out.println("----- \t Start of content \t ------");
		System.out.println(pdfFileContents);
		System.out.println("----- \t End of content \t ------");
	}

}
