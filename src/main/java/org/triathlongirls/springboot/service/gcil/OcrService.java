package org.triathlongirls.springboot.service.gcil;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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

            Path FILE_ROOT = Paths.get("./media/word.png").toAbsolutePath().normalize();

            File file = new File(String.valueOf(FILE_ROOT));
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(data);
            outputStream.close();

            ByteString imgBytes = ByteString.readFrom(new FileInputStream(String.valueOf(FILE_ROOT)));

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
                                    }
                                }
                                blockText = blockText + paraText;
                            }
                            pageText = pageText + blockText;
                        }
                    }
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



