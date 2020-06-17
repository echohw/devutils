package com.example.devutils.utils.office;

import com.example.devutils.dep.Charsets;
import com.example.devutils.dep.WkhtmlOptions;
import com.example.devutils.utils.ProcessInvokeUtils;
import com.example.devutils.utils.io.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AMe on 2020-06-16 16:48.
 */
public class WkhtmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(WkhtmlUtils.class);

    public static void renderToPdf(WkhtmlOptions options) throws IOException, InterruptedException {
        render(options.toPdfExecCmd());
    }

    public static void renderToPdf(WkhtmlOptions options, Consumer<Process> consumer) throws IOException, InterruptedException {
        render(options.toPdfExecCmd(), consumer);
    }

    public static void renderToImg(WkhtmlOptions options) throws IOException, InterruptedException {
        render(options.toImgExecCmd());
    }

    public static void renderToImg(WkhtmlOptions options, Consumer<Process> consumer) throws IOException, InterruptedException {
        render(options.toImgExecCmd(), consumer);
    }

    public static void render(String cmd) throws IOException, InterruptedException {
        render(cmd, process -> {
            try (
                InputStream inputStream = process.getInputStream();
                InputStream errorStream = process.getErrorStream();
            ) {
                String out = FileUtils.readAllAsString(inputStream, Charsets.UTF8_C);
                String err = FileUtils.readAllAsString(errorStream, Charsets.UTF8_C);
                logger.info("OutputStream: {}", out);
                logger.info("ErrorStream: {}", err);
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        });
    }

    public static void render(String cmd, Consumer<Process> consumer) throws IOException, InterruptedException {
        ProcessInvokeUtils.invoke(cmd, consumer);
    }

}
