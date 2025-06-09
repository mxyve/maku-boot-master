package net.maku.framework.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

/**
 * XSS 过滤工具类
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public class XssUtils {
    private final static Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    // 富文本中部分样式没显示，被过滤了
    // 解决：自定义 Safelist，允许 span 标签的 style 属性包含指定样式
    private final static Safelist CUSTOM_SAFELIST = Safelist.relaxed()
            .addTags("span") // 允许 span 标签
            // 或限定为 span 标签，并指定允许的样式正则
             .addAttributes("span", "style", "(color|background-color|font-size|font-family):.*")
            ;

    public static String filter(String content) {
        // 使用自定义的 CUSTOM_SAFELIST 进行过滤
        return Jsoup.clean(content, "", CUSTOM_SAFELIST, outputSettings);
    }
}