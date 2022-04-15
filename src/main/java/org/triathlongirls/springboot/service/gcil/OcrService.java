package org.triathlongirls.springboot.service.gcil;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.triathlongirls.springboot.web.dto.OcrDto;

import javax.xml.bind.DatatypeConverter;

@Service
public class OcrService {
    public OcrDto detectDocumentText(String filePath) {
        try {
            List<AnnotateImageRequest> requests = new ArrayList<>();

            String base64String = filePath;
            String[] strings = base64String.split(",");
            byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
            String path = "/Users/inkyeong/Desktop/test.png";
            File file = new File(path);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(data);
            outputStream.close();

            String imageFilePath = "/Users/inkyeong/Desktop/test.png";
            ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));

            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).setModel("builtin/latest").build();
            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);

            try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();
                client.close();

                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        System.out.format("Error: %s%n", res.getError().getMessage());
                        OcrDto ocrDto = OcrDto.builder().filepath("Error").build();
                        return ocrDto;
                    }

                    // For full list of available annotations, see http://g.co/cloud/vision/docs
                    TextAnnotation annotation = res.getFullTextAnnotation();
                    for (Page page : annotation.getPagesList()) {
                        String pageText = "";
                        for (Block block : page.getBlocksList()) {
                            String blockText = "";
                            for (Paragraph para : block.getParagraphsList()) {
                                String paraText = "";
                                for (Word word : para.getWordsList()) {
                                    String wordText = "";
                                    for (Symbol symbol : word.getSymbolsList()) {
                                        wordText = wordText + symbol.getText();
                                        System.out.format(
//                                                "Symbol text: %s (confidence: %f)%n",
                                                symbol.getText(), symbol.getConfidence());
                                    }
                                    System.out.format(
//                                            "Word text: %s (confidence: %f)%n%n", wordText, word.getConfidence());
                                    paraText = String.format("%s %s", paraText, wordText));
                                }
                                // Output Example using Paragraph:
//                                System.out.println("%nParagraph: %n" + paraText);
//                                System.out.format("Paragraph Confidence: %f%n", para.getConfidence());
                                blockText = blockText + paraText;
                            }
                            pageText = pageText + blockText;
                        }
                    }
//                    System.out.println("%nComplete annotation:");
//                    System.out.println(annotation.getText());
                    OcrDto ocrDto = OcrDto.builder().filepath(annotation.getText()).build();
                    return ocrDto;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        OcrDto ocrDto = OcrDto.builder().filepath("Error").build();
        return ocrDto;
    }

}



