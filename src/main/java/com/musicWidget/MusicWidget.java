package com.musicWidget;

import cn.hutool.core.util.StrUtil;
import com.perfree.commons.OptionCacheUtil;
import com.perfree.plugin.proxy.HtmlRenderProxy;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MusicWidget extends HtmlRenderProxy {
    @Override
    public Document editFrontDocument(Document document, HttpServletResponse response, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/login") || request.getRequestURI().startsWith("/register") ) {
            return document;
        }
        document.head().append("<link rel='stylesheet' href='//cdn.jsdelivr.net/npm/aplayer/dist/APlayer.min.css'>");
        document.head().append("<style>.aplayer.aplayer-fixed,.aplayer.aplayer-fixed .aplayer-lrc{z-index: 9999999}</style>");
        String html = "<meting-js server='netease' type='playlist' id='{}' fixed=\"true\" theme='{}' autoplay='{}' order='{}'> </meting-js>";
        String id = OptionCacheUtil.getDefaultValue("MUSIC_WIDGET_ID", "60198");
        String theme = OptionCacheUtil.getDefaultValue("MUSIC_WIDGET_THEME", "#2980b9");
        String order = OptionCacheUtil.getDefaultValue("MUSIC_WIDGET_ORDER", "list");
        String autoPlay = OptionCacheUtil.getDefaultValue("MUSIC_WIDGET_AUTOPLAY", "false");
        html = StrUtil.format(html, id, theme, autoPlay, order);
        document.body().before(html);
        document.body().append(" <script src='//cdn.jsdelivr.net/npm/aplayer/dist/APlayer.min.js'></script>");
        document.body().append(" <script src='//cdn.jsdelivr.net/npm/meting@2/dist/Meting.min.js'></script>");
        return document;
    }
}
