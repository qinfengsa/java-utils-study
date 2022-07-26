package org.qinfengsa.poi.word;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangheng
 * @date 2022/04/26 17:54
 */
public class OperateDoc {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperateDoc.class);

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("D:\\rasp\\vuln-springboot2-1.0.3_源代码安全检测报告.doc");
            OutputStream os = new FileOutputStream("D:\\rasp\\test.doc")) {

            //            WordExtractor we = new WordExtractor(is);
            //            for (String text : we.getParagraphText()) {
            //                LOGGER.info("test:{}", text);
            //            }
            XWPFDocument doc = new XWPFDocument(is);

            /*range.replaceText("FossEye", "");
            doc.write(os);*/
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
