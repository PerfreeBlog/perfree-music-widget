package com.musicWidget;

import com.perfree.cache.OptionCacheService;
import com.perfree.plugin.proxy.HtmlRenderProxy;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dromara.hutool.core.text.StrUtil;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;


@Component
public class MusicWidget extends HtmlRenderProxy {

    @Resource
    private OptionCacheService optionCacheService;

    @Override
    public Document editFrontDocument(Document document, HttpServletResponse response, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/register") ) {
            return document;
        }
        document.head().append("<link rel='stylesheet' href='//cdn.jsdelivr.net/npm/aplayer/dist/APlayer.min.css'>");
        document.head().append("<style>.aplayer.aplayer-fixed,.aplayer.aplayer-fixed .aplayer-lrc{z-index: 9999999}</style>");
        String html = "<meting-js server='netease' type='playlist' id='{}' fixed=\"true\" theme='{}' autoplay='{}' order='{}'> </meting-js>";
        String id = optionCacheService.getDefaultValue("MUSIC_WIDGET_ID", "60198");
        String theme = optionCacheService.getDefaultValue("MUSIC_WIDGET_THEME", "#2980b9");
        String order = optionCacheService.getDefaultValue("MUSIC_WIDGET_ORDER", "list");
        String autoPlay = optionCacheService.getDefaultValue("MUSIC_WIDGET_AUTOPLAY", "false");
        html = StrUtil.format(html, id, theme, autoPlay, order);
        document.body().before(html);
        document.body().append(" <script src='//cdn.jsdelivr.net/npm/aplayer/dist/APlayer.min.js'></script>");
        document.body().append(" <script src='//cdn.jsdelivr.net/npm/meting@2/dist/Meting.min.js'></script>");
        return document;
    }
}
