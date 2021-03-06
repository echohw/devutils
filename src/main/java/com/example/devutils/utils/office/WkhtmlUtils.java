package com.example.devutils.utils.office;

import com.example.devutils.utils.ProcessInvokeUtils;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * PDF转换工具类
 * Created by AMe on 2020-06-16 16:48.
 */
public class WkhtmlUtils {

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
        ProcessInvokeUtils.invoke(cmd);
    }

    public static void render(String cmd, Consumer<Process> consumer) throws IOException, InterruptedException {
        ProcessInvokeUtils.invoke(cmd, consumer);
    }
}
